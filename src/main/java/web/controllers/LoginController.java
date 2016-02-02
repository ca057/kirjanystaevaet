package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the route to home.
 */
@Controller
@RequestMapping(path = "/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String loginGet() {
		System.out.println(this.getClass().getSimpleName().toString() + ": loginGet was called.");
		return "login";
	}

	// @RequestMapping(method = RequestMethod.POST, produces =
	// "text/plain;charset=UTF-8")
	// public String loginPost() {
	// System.out.println(this.getClass().getSimpleName().toString() + ":
	// loginPost was called.");
	// return "redirect:/meinkonto";
	// }
}
