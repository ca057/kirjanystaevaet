package web.controllers.backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import appl.data.items.Author;
import appl.data.items.Book;
import appl.enums.SearchMode;
import appl.enums.Searchfields;
import appl.logic.service.BookService;
import exceptions.data.AuthorMayExistException;
import exceptions.data.DatabaseException;
import exceptions.data.ErrorMessageHelper;
import web.controllers.UploadHelper;
import web.jsonwrappers.AuthorJSONWrapper;
import web.jsonwrappers.BookJSONWrapper;

/**
 * 
 * This controller handles all requests for managing the stock of the shop.
 * Categories, authors and books can be added, deleted and edited.
 * 
 * @author Christian
 * @author Johannes
 * 
 */
@Controller
public class BackendStockController {

	@Autowired
	private UploadHelper upload;

	@Autowired
	private BookService bookService;

	private Predicate<String> stringIsNotNullAndEmpty = s -> s != null && !s.isEmpty();
	private Predicate<List> listIsNotNullAndEmpty = l -> l != null && !l.isEmpty();

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
			m.addAttribute("books", bookService.getAllBooks(SearchMode.ALL));
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
			bookService.deleteCategory(Integer.parseInt(id));

		} catch (DatabaseException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}
		return "redirect:/backend/bestand";
	}

	/**
	 * Responsible for adding a new {@link Author} when data send as JSON.
	 * 
	 * @param req
	 *            the data as {@link AuthorJSONWrapper}
	 * @return a {@link ResponseEntity} with a {@link HttpStatus} code
	 *         indicating success or failure
	 */
	@RequestMapping(value = "/backend/bestand/autorinnen/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<AuthorJSONWrapper> addAuthor(@RequestBody final AuthorJSONWrapper req) {
		if (req == null) {
			return new ResponseEntity<AuthorJSONWrapper>(HttpStatus.BAD_REQUEST);
		}
		try {
			bookService.insertAuthor(req.getNameF(), req.getNameL(), req.isNewAuthor());
		} catch (AuthorMayExistException e) {
			System.out.println("Author DB Exception");
			e.printStackTrace();
			return new ResponseEntity<AuthorJSONWrapper>(req, HttpStatus.CONFLICT);
		} catch (DatabaseException e) {
			System.out.println("General DB Exception");
			e.printStackTrace();
			return new ResponseEntity<AuthorJSONWrapper>(req, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<AuthorJSONWrapper>(req, HttpStatus.OK);
	}

	/**
	 * Responsible for deleting an {@link Author}, id of author must be sent as
	 * request parameter.
	 * 
	 * @param id
	 *            the id of the {@link Author} to delete
	 * @return a redirect to the URL responsible for displaying the view for the
	 *         data management
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
	 * Takes a set of request parameters and decides on the basis of the last
	 * URL part which action to perform.
	 * 
	 * @param action
	 * @param categories
	 * @param title
	 * @param isbn
	 * @param description
	 * @param price
	 * @param publisher
	 * @param day
	 * @param month
	 * @param year
	 * @param edition
	 * @param pages
	 * @param stock
	 * @param authors
	 * @param file
	 * @param request
	 * @return a redirect to the URL responsible for displaying the view for the
	 *         stock management
	 */
	@RequestMapping(value = "/backend/bestand/buecher/{action}", method = RequestMethod.POST)
	public String addOrEditBook(@PathVariable("action") String action,
			@RequestParam(value = "categories", required = false) List<String> categories,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "isbn", required = false) String isbn,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "publisher", required = false) String publisher,
			@RequestParam(value = "day", required = false) String day,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "edition", required = false) String edition,
			@RequestParam(value = "pages", required = false) String pages,
			@RequestParam(value = "stock", required = false) String stock,
			@RequestParam(value = "authors", required = false) List<String> authors,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
		if (!stringIsNotNullAndEmpty.test(action)) {
			System.err.println("Action: " + action);
			throw new IllegalArgumentException("The passed URL parameter is null and cannot be resolved to an action.");
		}
		switch (action) {
		case "add":
			return addBook(categories, title, description, price, isbn, publisher, day, month, year, edition, pages,
					stock, authors, file, request);
		case "edit":
			return editBook(categories, title, description, price, isbn, publisher, day, month, year, edition, pages,
					authors, file, request);
		default:
			return "redirect:/backend/bestand?error";
		}
	}

	/**
	 * Private method responsible for adding a new book. All parameters are
	 * required.
	 * 
	 * @param categories
	 * @param title
	 * @param description
	 * @param price
	 * @param isbn
	 * @param publisher
	 * @param day
	 * @param month
	 * @param year
	 * @param edition
	 * @param pages
	 * @param stock
	 * @param authors
	 * @param file
	 * @param request
	 * @return the string for the redirect to the URL responsible for displaying
	 *         the view for the stock management
	 */
	private String addBook(List<String> categories, String title, String description, String price, String isbn,
			String publisher, String day, String month, String year, String edition, String pages, String stock,
			List<String> authors, MultipartFile file, HttpServletRequest request) {
		if (listIsNotNullAndEmpty.test(categories) || stringIsNotNullAndEmpty.test(title)
				|| stringIsNotNullAndEmpty.test(description) || stringIsNotNullAndEmpty.test(price)
				|| stringIsNotNullAndEmpty.test(isbn) || stringIsNotNullAndEmpty.test(publisher)
				|| stringIsNotNullAndEmpty.test(day) || stringIsNotNullAndEmpty.test(month)
				|| stringIsNotNullAndEmpty.test(year) || stringIsNotNullAndEmpty.test(edition)
				|| stringIsNotNullAndEmpty.test(pages) || stringIsNotNullAndEmpty.test(stock)
				|| listIsNotNullAndEmpty.test(authors) || file == null || file.isEmpty()) {
			throw new IllegalArgumentException("One of the passed values for adding a book is null or empty.");
		}
		if (!file.getContentType().contains("image")) {
			throw new IllegalArgumentException("The uploaded file is not an image");
		}
		Map<Searchfields, String> book = new HashMap<Searchfields, String>();
		book.put(Searchfields.title, title);
		book.put(Searchfields.isbn, isbn);
		book.put(Searchfields.description, description);
		book.put(Searchfields.price, price);
		book.put(Searchfields.publisher, publisher);
		book.put(Searchfields.pubdate, formatPubdate(day, month, year));
		book.put(Searchfields.edition, edition);
		book.put(Searchfields.pages, pages);
		book.put(Searchfields.stock, stock);

		Set<Integer> authorIds = new HashSet<Integer>(1);
		authors.stream().forEach(id -> authorIds.add(Integer.parseInt(id)));

		Set<Integer> categoryIds = new HashSet<Integer>(1);
		categories.stream().forEach(id -> categoryIds.add(Integer.parseInt(id)));

		try {
			bookService.insertBook(book, authorIds, categoryIds);
			if (!upload.saveBookCover(isbn, file.getOriginalFilename().split("\\.")[1], file.getBytes(),
					request.getSession())) {
				return "redirect:/backend/bestand?error&msg=" + ErrorMessageHelper.couldNotBeSaved("Picture");
			}
		} catch (DatabaseException | IOException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}

		return "redirect:/backend/bestand";
	}

	/**
	 * Private method for editing a book. If a parameter is {@code null} or an
	 * empty {@link String} it will be ignored.
	 * 
	 * @param categories
	 * @param title
	 * @param description
	 * @param price
	 * @param isbn
	 * @param publisher
	 * @param day
	 * @param month
	 * @param year
	 * @param edition
	 * @param pages
	 * @param authors
	 * @param file
	 * @param request
	 * @return the string for the redirect to the URL responsible for displaying
	 *         the view for the stock management
	 */
	private String editBook(List<String> categories, String title, String description, String price, String isbn,
			String publisher, String day, String month, String year, String edition, String pages, List<String> authors,
			MultipartFile file, HttpServletRequest request) {
		Map<Searchfields, String> data = new HashMap<Searchfields, String>();
		try {
			Book book = bookService.getBookByIsbn(isbn, SearchMode.ALL);

			Set<Integer> authorIds = new HashSet<Integer>();
			if (listIsNotNullAndEmpty.test(authors)) {
				authors.stream().forEach(id -> authorIds.add(Integer.parseInt(id)));
			} else {
				book.getAuthors().stream().forEach(author -> authorIds.add(author.getAuthorId()));
			}

			Set<Integer> categoryIds = new HashSet<Integer>();
			if (listIsNotNullAndEmpty.test(categories)) {
				categories.stream().forEach(id -> categoryIds.add(Integer.parseInt(id)));
			} else {
				book.getCategories().stream().forEach(c -> categoryIds.add(c.getCategoryID()));
			}

			if (stringIsNotNullAndEmpty.test(title)) {
				data.put(Searchfields.title, title);
			}
			if (stringIsNotNullAndEmpty.test(description)) {
				data.put(Searchfields.description, description);
			}
			if (stringIsNotNullAndEmpty.test(price)) {
				data.put(Searchfields.price, price);
			}
			if (stringIsNotNullAndEmpty.test(publisher)) {
				data.put(Searchfields.publisher, publisher);
			}
			if (stringIsNotNullAndEmpty.test(day) && stringIsNotNullAndEmpty.test(month)
					&& stringIsNotNullAndEmpty.test(year)) {
				data.put(Searchfields.pubdate, formatPubdate(day, month, year));
			}
			if (stringIsNotNullAndEmpty.test(edition)) {
				data.put(Searchfields.edition, edition);
			}
			if (stringIsNotNullAndEmpty.test(pages)) {
				data.put(Searchfields.pages, pages);
			}
			if (file.getContentType().contains("image")) {
				if (!upload.saveBookCover(isbn, file.getOriginalFilename().split("\\.")[1], file.getBytes(),
						request.getSession())) {
					return "redirect:/backend/bestand?error&msg=" + ErrorMessageHelper.couldNotBeSaved("Picture");
				}
			}

			System.out.println("data: " + data.toString());
			bookService.updateBook(isbn, data, authorIds, categoryIds);
		} catch (DatabaseException | IOException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}

		return "redirect:/backend/bestand";
	}

	/**
	 * Responsible for adjusting the stock of a {@link Book}.
	 * 
	 * @param isbn
	 *            the ISBN of the {@link Book} where the stock will get updated
	 * @param stock
	 *            the value for incrementing or decrementing the stock, will be
	 *            parsed to an {@code int}
	 * @return a redirect to the URL responsible for displaying the view for the
	 *         stock management
	 */
	@RequestMapping(value = "/backend/bestand/buecher/stock", method = RequestMethod.POST)
	public String editStock(@RequestParam(value = "isbn", required = true) String isbn,
			@RequestParam(value = "stock", required = true) String stock) {
		try {
			bookService.updateStock(isbn, Integer.parseInt(stock));
		} catch (NumberFormatException | DatabaseException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}
		return "redirect:/backend/bestand";
	}

	/**
	 * Responsible for deleting a {@link Book}.
	 * 
	 * @param isbn
	 *            the ISBN of the {@link Book} to delete
	 * @param request
	 * @return a redirect to the URL responsible for displaying the view for the
	 *         stock management
	 */
	@RequestMapping(value = "/backend/bestand/buecher/delete", method = RequestMethod.POST)
	public String deleteBook(@RequestParam(value = "isbn") String isbn, HttpServletRequest request) {
		if (isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException(
					"The passed ISBN for the book to delete is either null or an empty string.");
		}
		try {
			bookService.deleteBook(isbn);
			deleteImage(
					new File(request.getSession().getServletContext().getRealPath(
							File.separator + "resources" + File.separator + "img" + File.separator + "cover")).toPath(),
					isbn);
			deleteImage(
					new File(request.getSession().getServletContext().getRealPath(
							File.separator + "uploaded" + File.separator + "img" + File.separator + "cover")).toPath(),
					isbn);
		} catch (DatabaseException | IOException e) {
			return "redirect:/backend/bestand?error&msg=" + e.getMessage();
		}
		return "redirect:/backend/bestand";
	}

	/**
	 * Returns the data of book identified by the ISBN passed as request
	 * parameter, for example for auto filling the input fields when editing a
	 * book.
	 * 
	 * @param isbn
	 *            the ISBN of the requested {@link Book}
	 * @return the data of the requested {@link Book} as {@link BookJSONWrapper}
	 */
	@RequestMapping(value = "/backend/bestand/buecher/data", method = RequestMethod.GET)
	public ResponseEntity<BookJSONWrapper> getBookByISBN(@RequestParam(value = "isbn", required = true) String isbn) {
		if (isbn == null || isbn.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			BookJSONWrapper wrapper = new BookJSONWrapper(bookService.getBookByIsbn(isbn, SearchMode.ALL));
			System.out.println("WRAPPER: " + wrapper);
			return new ResponseEntity<BookJSONWrapper>(wrapper, HttpStatus.OK);
		} catch (DatabaseException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Method for deleting a given image.
	 * 
	 * @param path
	 * @param title
	 * @throws IOException
	 */
	private void deleteImage(Path path, String title) throws IOException {
		Files.walk(path).parallel().filter(tmpPath -> tmpPath.toString().contains(title))
				.forEach(tmpPath -> tmpPath.toFile().delete());
	}

	/**
	 * Format the publication date as it is stored in the database. Example
	 * return value: 'August 15, 2016'
	 * 
	 * @param day
	 *            the day
	 * @param month
	 *            the month
	 * @param year
	 *            the year
	 * @return the correct concatenated string
	 */
	private String formatPubdate(String day, String month, String year) {
		return month.trim() + " " + Integer.parseInt(day) + ", " + year;
	}
}
