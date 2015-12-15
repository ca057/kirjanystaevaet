package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the route to home.
 */
@Controller
public class ContactController {

	@RequestMapping(value = "/kontakt", method = RequestMethod.GET)
	public String homepage() {
		return "contact";
	}
}
