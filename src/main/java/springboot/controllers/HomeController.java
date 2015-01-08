package springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(ModelMap map) {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/loginSuccess")
	public String loginSuccessful() {
		return "loginSuccess";
	}

	@RequestMapping("/loginFailure")
	public String loginFailure() {
		return "loginFailure";
	}
	
	@RequestMapping("/logoutSuccess")
	public String logoutSuccess() {
		return "logoutSuccess";
	}
}
