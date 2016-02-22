package web.controllers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.logic.service.BookService;
import exceptions.data.AuthorMayExistException;

/**
 * 
 * This controller handles all requests for managing the stock of the shop.
 * Categories, authors and books can be added, deleted and edited.
 * 
 */
@Controller
public class BackendStockController {

	private BookService service;

	@Autowired
	private void setBookService(BookService service) {
		this.service = service;
	}

	/**
	 * Handles a simple GET request and returns the name of the backend view for
	 * managing the stock.
	 * 
	 * @return the name of the backend view
	 */
	@RequestMapping(value = "/backend/bestand", method = RequestMethod.GET)
	public String getStock() {
		return "backend/stock";
	}

	/**
	 * Adds a new category with the name passed as request parameter and returns
	 * a redirect to the basic stock management url. POST request is needed.
	 * 
	 * @param name
	 *            the name as {@code String} of the new category
	 * @return the name of the backend view
	 */
	@RequestMapping(value = "/backend/bestand/kategorien/add", method = RequestMethod.POST)
	public String addCategory(@RequestParam(value = "name") String name) {
		if (name == null) {
			throw new IllegalArgumentException("The passed name of the category is null and can not be added.");
		}
		// TODO handle empty string
		service.insertCategory(name);
		return "redirect:/backend/bestand";
	}

	/**
	 * Deletes the passed category as request parameters and returns a redirect
	 * to the basic stock management url. DELETE request is needed.
	 * 
	 * @param name
	 *            the name as {@code String} of the category to delete
	 * @return the name of the backend view
	 */
	@RequestMapping(value = "/backend/bestand/kategorien/delete", method = RequestMethod.DELETE)
	public String deleteCategory(@RequestParam(value = "name") String name) {
		if (name == null) {
			throw new IllegalArgumentException("The passed name of the category is null and can not be deleted.");
		}
		// TODO handle empty string
		// TODO delete category
		return "redirect:/backend/bestand";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/autorinnen/add", method = RequestMethod.POST)
	public String addAuthor() {
		// TODO implement me
		try {
			service.insertAuthor("", "", true);
		} catch (AuthorMayExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/backend/bestand";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/autorinnen/delete", method = RequestMethod.DELETE)
	public String deleteAuthor() {
		// TODO implement me
		return "redirect:/backend/bestand";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/buecher/add", method = RequestMethod.POST)
	public String addBook() {
		// TODO implement me
		return "redirect:/backend/bestand";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/buecher/edit", method = RequestMethod.POST)
	public String editBook() {
		// TODO implement me
		return "redirect:/backend/bestand?deleted";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/buecher/delete", method = RequestMethod.DELETE)
	public String deleteBook() {
		// TODO implement me
		return "redirect:/backend/bestand";
	}

}
