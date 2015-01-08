package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springboot.data.entities.User;
import springboot.services.DataService;
import springboot.services.MailService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    DataService dataService;

    @Autowired
    MailService mailService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "users/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "username", required = true)String username,
                           @RequestParam(value = "password", required = true)String password,
                           RedirectAttributes redirectAttributes) {

        if (dataService.findUserByUsername(username) != null) {
            redirectAttributes.addFlashAttribute("warning", "Given user already exists");
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("password", password);
            return "redirect:/users/register";
        }

        User user = new User();
        user.setUsername(username);
        user.setPlainPassword(password);
        user.setState(User.UserState.EmailNotConfirmed);
        user.generateEmailConfirmationToken();
        dataService.save(user);
        mailService.sendRegistrationConfirmation(user);
        redirectAttributes.addFlashAttribute("info", "User registration confirmation request has been sent to your email address");
        return "redirect:/";
    }

    @RequestMapping(value = "confirmEmail/{email}/{token}")
    public String confirmEmail(@PathVariable("token") String token,
                               @PathVariable("email") String email,
                               RedirectAttributes map) {

        if (token == null || token.trim().isEmpty()) {
            return "redirect:/";
        } else if (email == null || email.trim().isEmpty()) {
            return "redirect:/";
        }

        User user = dataService.findUserByUsername(email);
        if (        user == null
                ||  user.getState() != User.UserState.EmailNotConfirmed
                ||  !user.getEmailConfirmationToken().equals(token)) {
            return "redirect:/";
        } else {
            user.setState(User.UserState.EmailConfirmed);
            dataService.save(user);
            map.addFlashAttribute("info", "Email confirmed. You can log in now.");
            return "redirect:/";
        }
    }
}
