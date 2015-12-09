package web;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.logic.service.impl.QueryCreatorImpl;
import exceptions.CategoryNotFoundException;

/**
 * Controller for the route to the categories.
 */
@Controller
public class CategoriesController {

	@Autowired
	private QueryCreatorImpl query;

	public void setQuery(QueryCreatorImpl query) {
		this.query = query;
	}

	@RequestMapping(value = "/kategorie/{category}", method = RequestMethod.GET)
	public String categories(@PathVariable("category") String category, Model m) throws CategoryNotFoundException {
		if (checkIfExistingCategory(category)) {
			m.addAttribute("name", getCorrectCategoryName(category));
			return "categories";
		} else {
			throw new CategoryNotFoundException("Die Kategorie " + category + " wurde nicht gefunden.");
		}
	}

	private boolean checkIfExistingCategory(String category) {
		return query.getCategories().stream().map(s -> s.toUpperCase()).collect(Collectors.toList())
				.contains(category.toUpperCase());
	}

	private String getCorrectCategoryName(String category) throws CategoryNotFoundException {
		for (String c : query.getCategories()) {
			if (category.toUpperCase().equals(c.toUpperCase())) {
				return c;
			}
		}
		throw new CategoryNotFoundException("Die Kategorie " + category + " wurde nicht gefunden.");
	}
}
