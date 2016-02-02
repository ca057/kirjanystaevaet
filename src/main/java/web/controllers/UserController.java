package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/meinkonto")
public class UserController {

	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String userGet() {
		System.out.println("GET user: I got called!");
		return "user";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String userPost() {
		System.out.println("POST user: I got called!");
		return "user";
	}
}
