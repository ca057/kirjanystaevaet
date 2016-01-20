package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/logout")
public class LogoutController {

	@RequestMapping(method = RequestMethod.GET)
	public String logoutGet() {
		System.out.println("Logout GET was called");
		return "redirect:/?logout";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String logoutPost() {
		System.out.println("Logout POST was called");
		return "redirect:/?logout";
	}
}
