package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateResolver;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.groovy.GroovyMarkupViewResolver;

import springboot.data.entities.User;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;




import groovy.util.GroovyScriptEngine;

@Component("mailService")
public class MailServiceImpl implements MailService {

//    @Autowired
//    MailSender mailSender;

	@Autowired
	GroovyMarkupViewResolver groovyMarkupViewResolver;

    private String renderTemplate(String templateName, Map<String, Object> map) {
        StringWriter writer = new StringWriter();
        try {
			View view = groovyMarkupViewResolver.resolveViewName(templateName, null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        Template template = velocityEngine.getTemplate(templateName);
//        VelocityContext context = new VelocityContext(map);
//        template.merge(context, writer);
        return writer.toString();
    }

    @Override
    public void sendRegistrationConfirmation(User user) {
        Assert.notNull(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", user.getUsername());
//        map.put("token", user.getEmailConfirmationToken());

        String template = renderTemplate("mail/registrationConfirmation.vm", map);

        send(user.getUsername(), "Confirm your email", template);
    }

    @Override
    public void sendPasswordResetUrl(User user) {
        Assert.notNull(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", user.getUsername());
//        map.put("token", user.getPasswordResetToken());

        String template = renderTemplate("mail/passwordReset.vm", map);

        send(user.getUsername(), "Reset your password", template);
    }

    private void send(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kostas868@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
//        mailSender.send(message);
    }
}