package web.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the route to home.
 */
@Controller
@RequestMapping(value = "/backend/login")
public class BackendLoginController {

	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String login() {
		return "backend/login";
	}
}
