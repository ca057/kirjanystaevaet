package web.controllers.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.enums.SearchMode;
import appl.logic.service.BookService;
import exceptions.data.DatabaseException;
import exceptions.web.ControllerOvertaxedException;
import web.controllers.ControllerHelper;

/**
 * Controller for the route to the categories.
 */
@Controller
public class CategoriesController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ControllerHelper helper;

	/**
	 * Adds a list with all existing categories to the model. If an exception
	 * occurs while querying the category names, the exception is ignored. In
	 * this case, no list is added to the model and the view will render an
	 * error message.
	 * 
	 * @param m
	 *            the model for the view
	 * @return the name of the view responsible for displaying the categories
	 */
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

	/**
	 * If the URL contains a category name as path variable, this method tries
	 * to resolve it to an existing one. If an existing category is found, its
	 * correct name and all its books are added to the model.
	 * 
	 * If an exception occurs, this method will return a redirect to the
	 * category overview.
	 * 
	 * @param category
	 *            the name of the category, upper or lower case does not matter
	 * @param m
	 *            the model for the view
	 * @return the name of the view responsible for displaying a category and
	 *         its books or a redirect to the category overview
	 */
	@RequestMapping(value = "/kategorie/{category}", method = RequestMethod.GET)
	public String getCategory(@PathVariable("category") String category, Model m) {
		try {
			m.addAttribute("name", helper.getCorrectCategoryName(category));
			m.addAttribute("books", bookService.getBooksByCategory(category, SearchMode.AVAILABLE));
			return "categories";
		} catch (ControllerOvertaxedException | DatabaseException e) {
			return "redirect:/kategorien";
		}
	}
}
