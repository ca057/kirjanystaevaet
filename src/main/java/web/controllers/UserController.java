package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller responsible for displaying the page of a single user.
 * 
 * @author Christian
 * @author Ludwig
 *
 */
@Controller
@RequestMapping(value = "/meinkonto")
public class UserController {

	/**
	 * Handles a GET request for rendering the page of a single user.
	 * 
	 * @return the name of the view
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String userGet() {
		return "user";
	}
}
