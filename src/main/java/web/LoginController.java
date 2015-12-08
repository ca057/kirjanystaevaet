package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for the route to home.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String login(@RequestParam(value = "name") String name, @RequestParam(value = "pwd") String pwd) {
		System.out.println("Name: [" + name + "] -- Passwort: [" + pwd + "]");
		return "redirect:/meinkonto";
	}

	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String login() {
		return "login";
	}
}
