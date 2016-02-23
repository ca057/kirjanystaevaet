package web.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BackendUsersController {

	@RequestMapping(value = "/backend/kundinnen", method = RequestMethod.GET)
	public String getUsers() {
		return "backend/users";
	}

	@RequestMapping(value = "/backend/kundinnen/add", method = RequestMethod.POST)
	public String addUser() {

		return "redirect:/backend/users";
	}
}
