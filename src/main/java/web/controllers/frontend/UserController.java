package web.controllers.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.items.User;
import appl.logic.service.OrderService;
import appl.logic.service.UserService;
import exceptions.controller.ControllerOvertaxedException;
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

	@Autowired
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	private int getUserId() {
		return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
	}

	/**
	 * Handles a GET request for rendering the page of a single user.
	 * 
	 * @return the name of the view
	 * @throws ControllerOvertaxedException
	 *             if the logged in user can not be retrieved
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String userGet(Model m) throws ControllerOvertaxedException {
		try {
			m.addAttribute("lastOrders", orderService.getOrdersByUserid(getUserId()));
		} catch (DatabaseException e) {
			throw new ControllerOvertaxedException(
					"An unknown error occured which should not happen. This can not be handled by the controller.");
		}
		return "user";
	}
}
