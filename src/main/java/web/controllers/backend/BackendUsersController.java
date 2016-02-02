package web.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/backend/kundinnen")
public class BackendUsersController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getUsers() {
		return "backend/users";
	}
}
