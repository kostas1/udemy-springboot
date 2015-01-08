package springboot.services;

import groovy.text.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.groovy.GroovyMarkupConfigurer;

import springboot.data.entities.User;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    MailSender mailSender;

    @Autowired
    GroovyMarkupConfigurer groovyMarkupConfigurer;


    private String renderTemplate(String templateName, Map<String, Object> map) {
        StringWriter writer = new StringWriter();
        try {
            Template template = groovyMarkupConfigurer.getTemplateEngine().createTemplateByPath(templateName);
            template.make(map).writeTo(writer);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    @Override
    public void sendRegistrationConfirmation(User user) {
        Assert.notNull(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", user.getUsername());
        map.put("token", user.getEmailConfirmationToken());

        String template = renderTemplate("mail/registrationConfirmation.groovy", map);

        send(user.getUsername(), "Confirm your email", template);
    }

    private void send(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kostas868@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}