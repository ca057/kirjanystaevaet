package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/registrierung")
public class RegisterController {

	public static class USER {
		// TODO use inside class?
	}

	@RequestMapping(method = RequestMethod.GET)
	public String registerGet() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerPost() {
		// TODO implement AJAX logic here
		return "register";
	}

}
