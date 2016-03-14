package web.controllers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.logic.service.OrderService;
import exceptions.data.DatabaseException;

/**
 * Controller responsible for displaying the orders.
 * 
 * @author Christian
 *
 */
@Controller
public class BackendOrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * Returns the view which is responsible for displaying the orders. All
	 * orders will be added as an attriubte to the {@link Model}.
	 * 
	 * @param m
	 *            the {@link Model} for the view
	 * @return the name of the view
	 */
	@RequestMapping(value = "/backend/bestellungen", method = RequestMethod.GET)
	public String getBackend(Model m) {
		try {
			m.addAttribute("orders", orderService.getAllOrders());
		} catch (DatabaseException e) {
			m.addAttribute("error", e.getMessage());
		}
		return "backend/orders";
	}
}
