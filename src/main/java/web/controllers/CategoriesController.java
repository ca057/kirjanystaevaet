package web.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.logic.service.BookService;
import exceptions.controller.CategoryNotFoundException;
import exceptions.data.DatabaseException;

/**
 * Controller for the route to the categories.
 */
@Controller
public class CategoriesController {

	@Autowired
	private BookService service;

	/**
	 * Setter injection for the {@link CategoryService} bean.
	 * 
	 * @param service
	 */
	public void setService(BookService service) {
		this.service = service;
	}

	@RequestMapping(value = "/kategorien", method = RequestMethod.GET)
	public String getCategoryOverview(Model m) {
		try {
			m.addAttribute("allCategories", service.getAllCategoryNames());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "categories";
	}

	@RequestMapping(value = "/kategorie/{category}", method = RequestMethod.GET)
	public String getCategory(@PathVariable("category") String category, Model m) {
		try {
			m.addAttribute("name", getCorrectCategoryName(category));
			return "categories";
		} catch (CategoryNotFoundException e) {
			return "redirect:/kategorien";
		}
	}

	/**
	 * Checks if the passed category is existing, upper/lower cases are ignored.
	 * 
	 * @param category
	 * @return {@code true} if the category exists, {@code false} otherwise
	 */
	private boolean checkIfExistingCategory(String category) {
		if (service == null) {
			throw new IllegalArgumentException("Service is null");
		}
		try {
			return service.getAllCategoryNames().stream().map(s -> s.toUpperCase()).collect(Collectors.toList())
					.contains(category.toUpperCase());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false; // Eingefügt von MAdeleine, was wollt ihr wirklich machen?
		}
	}

	/**
	 * Returns the correct formatted category name for displaying it in the
	 * views.
	 * 
	 * @param category
	 * @return the correct formatted category as {@code String}
	 * @throws CategoryNotFoundException
	 *             if the given category does not exist
	 */
	private String getCorrectCategoryName(String category) throws CategoryNotFoundException {
		if (checkIfExistingCategory(category)) {
			try {
				for (String c : service.getAllCategoryNames()) {
					if (category.toUpperCase().equals(c.toUpperCase())) {
						return c;
					}
				}
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		throw new CategoryNotFoundException("Die Kategorie " + category + " wurde nicht gefunden.");
	}
}
