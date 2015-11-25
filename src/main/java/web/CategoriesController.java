package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.queryManagement.queryCreator;
import appl.queryManagement.queryCreatorImpl;
import exceptions.CategoryNotFoundException;

/**
 * Controller for the route to the categories.
 */
@Controller
public class CategoriesController {

	@RequestMapping(value = "/kategorie/{category}", method = RequestMethod.GET)
	public String categories(@PathVariable("category") String category, Model m) throws CategoryNotFoundException {
		if (checkIfExistingCategory(category)) {
			m.addAttribute("name", category);
			return "categories";
		} else {
			throw new CategoryNotFoundException("Die Kategorie " + category + " wurde nicht gefunden.");
		}
	}

	private boolean checkIfExistingCategory(String category) {
		queryCreator query = new queryCreatorImpl();
		return query.getCategories().contains(category);
	}
}
