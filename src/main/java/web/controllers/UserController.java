package web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.items.User;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;

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

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private Optional<User> getUser() throws DatabaseException {
		return userService
				.findByID(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
	}

	/**
	 * Handles a GET request for rendering the page of a single user.
	 * 
	 * @return the name of the view
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String userGet(Model m) {
		try {
			User user = getUser().get();
			System.out.println(user.getOrders());
			m.addAttribute("lastOrders", user.getOrders());
		} catch (DatabaseException e) {
			// TODO throw Controller is blablabla-Exception
		}
		return "user";
	}
}
