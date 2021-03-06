package web.controllers.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the route to the login screen.
 * 
 * @author Christian
 * @author Ludwig
 * 
 */
@Controller
@RequestMapping(path = "/login")
public class LoginController {

	/**
	 * Handles a GET request for displaying the login screen.
	 * 
	 * @return the name of the view associated with the login screen
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String loginGet() {
		return "login";
	}

}
