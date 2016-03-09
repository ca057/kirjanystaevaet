package web.controllers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.logic.service.OrderService;
import exceptions.data.DatabaseException;

@Controller
public class BackendOrderController {

	@Autowired
	private OrderService orderService;

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
