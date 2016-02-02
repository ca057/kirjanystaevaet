package web.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/backend/bestand")
public class BackendStockController {

	@RequestMapping(method = RequestMethod.GET)
	public String getBestand() {
		return "backend/stock";
	}
}
