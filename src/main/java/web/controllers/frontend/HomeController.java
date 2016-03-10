package web.controllers.frontend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.items.Book;
import appl.enums.SearchMode;
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
			List<Book> allBooks = bookService.getAllBooks(SearchMode.AVAILABLE);
			List<Book> recBooks = new ArrayList<Book>();
			while (recBooks.size() < 3) {

				Book bookTemp = allBooks.get((int) (Math.random() * allBooks.size()));
				if (!recBooks.contains(bookTemp)) {
					recBooks.add(bookTemp);

				}
			}
			m.addAttribute("recommendations", recBooks);
			System.out.print("Find me" + recBooks);
		} catch (DatabaseException ignore) {
		}

		return "homepage";
	}
}
