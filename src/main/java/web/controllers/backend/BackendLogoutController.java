package web.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/backend/logout")
public class BackendLogoutController {

	@RequestMapping(method = RequestMethod.GET)
	public String logoutGet() {
		System.out.println("BackendLogout GET was called");
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String logoutPost() {
		System.out.println("BackendLogout POST was called");
		return "redirect:/";
	}

}
