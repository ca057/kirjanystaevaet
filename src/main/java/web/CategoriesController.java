package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the route to the categories.
 */
@Controller
public class CategoriesController {

	@RequestMapping(value = "/kategorie/{category}", method = RequestMethod.GET)
	public String categories(@PathVariable("category") String category, Model m) {
		m.addAttribute("name", category);
		return "categories";
	}
}
