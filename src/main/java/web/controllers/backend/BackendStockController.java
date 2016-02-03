package web.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BackendStockController {

	@RequestMapping(value = "/backend/bestand", method = RequestMethod.GET)
	public String getStock() {
		return "backend/stock";
	}

	@RequestMapping(value = "/backend/bestand/kategorien/{action}", method = RequestMethod.POST)
	public String manageCategories(@PathVariable("action") String action, Model m) {
		if (action == null || action.isEmpty()) {
			throw new IllegalArgumentException("The given path variable is null or empty and cannot be resolved to any action.");
		}
		System.err.println("Category action: " + action);
		return "backend/stock";
	}
}
