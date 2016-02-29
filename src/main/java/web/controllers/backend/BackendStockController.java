package web.controllers.backend;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.data.enums.Searchfields;
import appl.logic.service.BookService;
import exceptions.data.AuthorMayExistException;
import exceptions.data.DatabaseException;
import web.jsonwrappers.AuthorJSONWrapper;

/**
 * 
 * This controller handles all requests for managing the stock of the shop.
 * Categories, authors and books can be added, deleted and edited.
 * 
 */
@Controller
public class BackendStockController {

	private BookService bookService;

	@Autowired
	private void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * Handles a simple GET request and returns the name of the backend view for
	 * managing the stock.
	 * 
	 * @return the name of the backend view
	 */
	@RequestMapping(value = "/backend/bestand", method = RequestMethod.GET)
	public String getStock(Model m) {
		try {
			m.addAttribute("categories", bookService.getAllCategories());
			m.addAttribute("authors", bookService.getAllAuthors());
			m.addAttribute("books", bookService.getAllBooks());
		} catch (DatabaseException e) {
			m.addAttribute("errormsg", e.getMessage());
			return "backend/stock?error";
		}
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
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("The passed name of the category is null and can not be added.");
		}
		try {
			bookService.insertCategory(name);
		} catch (DatabaseException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}
		return "redirect:/backend/bestand";
	}

	/**
	 * Deletes the passed category as request parameters and returns a redirect
	 * to the basic stock management url. POST request is needed.
	 * 
	 * @param id
	 *            the name as {@code String} of the category to delete
	 * @return the name of the backend view
	 */
	@RequestMapping(value = "/backend/bestand/kategorien/delete", method = RequestMethod.POST)
	public String deleteCategory(@RequestParam(value = "id") String id) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException(
					"The passed id of the category is null or empty and can not be deleted.");
		}
		try {
			bookService.deleteCategory(bookService.getCategoryById(Integer.parseInt(id)).getCategoryName());
		} catch (DatabaseException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}
		return "redirect:/backend/bestand";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/autorinnen/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<AuthorJSONWrapper> addAuthor(@RequestBody final AuthorJSONWrapper req) {
		if (req == null) {
			return new ResponseEntity<AuthorJSONWrapper>(HttpStatus.BAD_REQUEST);
		}
		try {
			bookService.insertAuthor(req.getNameF(), req.getNameL(), req.isNewAuthor());
		} catch (AuthorMayExistException e) {
			return new ResponseEntity<AuthorJSONWrapper>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<AuthorJSONWrapper>(HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/autorinnen/delete", method = RequestMethod.POST)
	public String deleteAuthor(@RequestParam(value = "author") String id) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("The passed id of the author is null or empty.");
		}
		try {
			bookService.deleteAuthor(Integer.parseInt(id));
		} catch (NumberFormatException | DatabaseException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}
		return "redirect:/backend/bestand";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/buecher/add", method = RequestMethod.POST)
	public String addBook(@RequestParam(value = "categories", required = true) List<String> categories,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "isbn", required = true) String isbn,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "price", required = true) String price,
			@RequestParam(value = "publisher", required = true) String publisher,
			@RequestParam(value = "day", required = true) String day,
			@RequestParam(value = "month", required = true) String month,
			@RequestParam(value = "year", required = true) String year,
			@RequestParam(value = "edition", required = true) String edition,
			@RequestParam(value = "pages", required = true) String pages,
			@RequestParam(value = "stock", required = true) String stock,
			@RequestParam(value = "authors", required = true) List<String> authors) {
		if (categories == null || categories.isEmpty() || title == null || title.isEmpty() || isbn == null
				|| isbn.isEmpty() || description == null || description.isEmpty() || price == null || price.isEmpty()
				|| publisher == null || publisher.isEmpty() || day == null || day.isEmpty() || month == null
				|| month.isEmpty() || year == null || year.isEmpty() || edition == null || edition.isEmpty()
				|| pages == null || pages.isEmpty() || authors == null || authors.isEmpty() || stock == null
				|| stock.isEmpty()) {
			// TODO check if pages, categories and authors only contains
			// numerical values
			throw new IllegalArgumentException("One of the passed values for adding a book is null or empty.");
		}
		Map<Searchfields, String> book = new HashMap<Searchfields, String>();
		book.put(Searchfields.title, title);
		book.put(Searchfields.isbn, isbn);
		book.put(Searchfields.description, description);
		book.put(Searchfields.price, price);
		book.put(Searchfields.publisher, publisher);
		// date as string like Monat als String + " " + Tag als Zahl ohne
		// führende Null + ", " + Jahr als Zahl
		book.put(Searchfields.pubdate, month.trim() + " " + Integer.parseInt(day) + ", " + year);
		book.put(Searchfields.edition, edition);
		book.put(Searchfields.pages, pages);
		// TODO add stock to book

		Set<Integer> authorIds = new HashSet<Integer>(1);
		authors.stream().forEach(id -> authorIds.add(Integer.parseInt(id)));

		Set<Integer> categoryIds = new HashSet<Integer>(1);
		categories.stream().forEach(id -> categoryIds.add(Integer.parseInt(id)));

		try {
			bookService.insertBook(book, authorIds, categoryIds);
		} catch (DatabaseException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}

		return "redirect:/backend/bestand";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/buecher/edit", method = RequestMethod.POST)
	public String editBook() {
		// TODO implement me
		return "redirect:/backend/bestand";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/backend/bestand/buecher/delete", method = RequestMethod.POST)
	public String deleteBook(@RequestParam(value = "isbn") String isbn) {
		if (isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException(
					"The passed ISBN for the book to delete is either null or an empty string.");
		}
		try {
			bookService.deleteBook(isbn);
		} catch (DatabaseException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}
		return "redirect:/backend/bestand";
	}
}
