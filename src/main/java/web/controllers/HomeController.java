package web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.items.Book;
import appl.logic.service.BookService;
import exceptions.data.DatabaseException;

/**
 * Controller for the route to home.
 * 
 * @author Christian
 * @author Ludwig
 * 
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

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

	/**
	 * Returns the view to display the homepage. A random book of the day is
	 * added to the model. If an exception occurs, it will be ignored.
	 * 
	 * @param m
	 *            the model for the view
	 * @return the name of the view associated with the homepage
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String homepage(Model m) {
		try {
			List<Book> books = bookService.getAllBooks();
			m.addAttribute("bookOfTheDay", books.get((int) (Math.random() * books.size())));
		} catch (DatabaseException ignore) {
		}
		return "homepage";
	}
}
