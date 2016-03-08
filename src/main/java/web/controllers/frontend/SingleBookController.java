package web.controllers.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.items.Book;
import appl.logic.service.BookService;
import exceptions.data.DatabaseException;

/**
 * Controller used for displaying a single book by passing a ISBN number as path
 * variable.
 * 
 * @author Christian
 * @author Ludwig
 *
 */
@Controller
public class SingleBookController {

	private BookService bookService;

	@Autowired
	public void setService(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * The URL should always contain an ISBN-number, so a book can be displayed.
	 * If no ISBN is passed, this is treated as an error, a error message is
	 * passed to the model for informing the user.
	 * 
	 * @param m
	 *            the model
	 * @return the name of the view associated with displaying a single book
	 */
	@RequestMapping(value = "/buch", method = RequestMethod.GET)
	public String getBook(Model m) {
		m.addAttribute("error", "Die URL enthält keine ISBN, daher kann hier kein Buch angezeigt werden. "
				+ "Möglicherweise ist der Ursprungslink fehlerhaft.");
		return "book";
	}

	/**
	 * If a ISBN is found in the URL as path variable, the book with this ISBN
	 * is added to model for displaying its information.
	 * 
	 * If an error occurs, an error message is passed to the {@code error}
	 * attribute of the model; if the book is out of stock, an info message is
	 * passed to the {@code info} attribute of the model.
	 * 
	 * @param isbn
	 *            the isbn of the book to display
	 * @param m
	 *            the model for the view
	 * @return the name of the view responsible for displaying a single book
	 */
	@RequestMapping(value = "/buch/{isbn}", method = RequestMethod.GET)
	public String getBookByIsbn(@PathVariable("isbn") String isbn, Model m) {
		if (isbn != null && !isbn.isEmpty()) {
			try {
				Book book = bookService.getBookByIsbn(isbn);
				if (book.getStock() > 0) {
					bookService.increaseVisitCount(isbn, 1);
					m.addAttribute("book", book);
				} else {
					m.addAttribute("info", "Das Buch steht derzeit nicht zum Verkauf!");
				}
			} catch (DatabaseException e) {
				m.addAttribute("error",
						"Ein Fehler ist aufgetreten. Versuchen Sie es zu einem späteren Zeitpunkt noch einmal.");
			}
		}
		return "book";
	}
}
