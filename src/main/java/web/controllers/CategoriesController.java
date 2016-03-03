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
	private BookService bookService;

	/**
	 * Setter injection for the {@link CategoryService} bean.
	 * 
	 * @param bookService
	 */
	public void setService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "/kategorien", method = RequestMethod.GET)
	public String getCategoryOverview(Model m) {
		try {
			m.addAttribute("allCategories", bookService.getAllCategoryNames());
		} catch (DatabaseException ignore) {
			// if the view doesn't have a list with the category names, it will
			// render an error message
		}
		return "categories";
	}

	@RequestMapping(value = "/kategorie/{category}", method = RequestMethod.GET)
	public String getCategory(@PathVariable("category") String category, Model m) {
		try {
			m.addAttribute("name", getCorrectCategoryName(category));
			// TODO change method for querying book
			m.addAttribute("books", bookService.getBooksByCategory(category).stream().filter(b -> b.getStock() > 0)
					.collect(Collectors.toList()));
			return "categories";
		} catch (CategoryNotFoundException | DatabaseException e) {
			return "redirect:/kategorien";
		}
	}

	/**
	 * Checks if the passed category is existing, upper/lower cases are ignored.
	 * 
	 * @param category
	 * @return {@code true} if the category exists, {@code false} otherwise or
	 *         if an error occurred
	 */
	private boolean isExistingCategory(String category) {
		try {
			return bookService.getAllCategoryNames().stream().map(s -> s.toUpperCase()).collect(Collectors.toList())
					.contains(category.toUpperCase());
		} catch (DatabaseException e) {
			return false;
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
	 * @throws DatabaseException
	 *             if an error occurs while querying the category names
	 */
	private String getCorrectCategoryName(String category) throws CategoryNotFoundException, DatabaseException {
		if (isExistingCategory(category)) {
			for (String c : bookService.getAllCategoryNames()) {
				if (category.toUpperCase().equals(c.toUpperCase())) {
					return c;
				}
			}
		}
		throw new CategoryNotFoundException("Die Kategorie " + category + " wurde nicht gefunden.");
	}
}
