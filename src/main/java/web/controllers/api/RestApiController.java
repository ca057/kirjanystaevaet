package web.controllers.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.data.items.Book;
import appl.enums.SearchMode;
import appl.logic.service.BookService;
import exceptions.data.DatabaseException;
import web.jsonwrappers.BookJSONWrapper;

/**
 * Controller manages the API, all results are returned as JSON. Only available
 * books (stock > 0) will be returned.
 * 
 * @author Christian
 *
 */
@Controller
public class RestApiController {

	@Autowired
	private BookService bookService;

	/**
	 * Handles a request for retrieving a list with all books. An optional limit
	 * for the results can be passed which will get parsed to a {@code long}.
	 * 
	 * @param limit
	 *            an optional value to limit the number of {@link Book}s
	 *            returned
	 * @return a JSON object with all books and HTTP status 200, no results and
	 *         status 400 if the limit could not be parsed or is less than 0 or
	 *         status 500 if an error while requesting data occurs
	 */
	@RequestMapping(value = "/api/v1/books", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<BookJSONWrapper>> getAllBooks(
			@RequestParam(value = "limit", required = false) String limit) {
		if (limit != null && Long.parseLong(limit) < 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			List<BookJSONWrapper> allBooks = bookService.getAllBooks(SearchMode.AVAILABLE).stream()
					.limit((limit == null || limit.isEmpty()) ? Long.MAX_VALUE : Long.parseLong(limit))
					.map(b -> new BookJSONWrapper(b)).collect(Collectors.toList());
			return new ResponseEntity<List<BookJSONWrapper>>(allBooks, HttpStatus.OK);
		} catch (DatabaseException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Handles a more specific request. At the moment two different modes are
	 * possible: If the last part of the URL is a valid category name, all books
	 * of this category will get returned, otherwise the parameter will be
	 * treated as an ISBN number.
	 * 
	 * @param param
	 *            a path variable as parameter to specify the request
	 * @param limit
	 *            an optional value to limit the number of {@link Book}s
	 *            returned
	 * @return a JSON object with all books fitting the parameter and HTTP
	 *         status 200, status 204 and no result if no books fitting the
	 *         request were found, no results and status 400 if the limit could
	 *         not be parsed or is less than 0 or status 500 if an error while
	 *         requesting data occurs
	 */
	@RequestMapping(value = "/api/v1/books/{param}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> getBooksByParamter(@PathVariable("param") String param,
			@RequestParam(value = "limit", required = false) String limit) {
		if (param == null || param.isEmpty() || (limit != null && Long.parseLong(limit) < 0)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			if (bookService.isExistingCategory(param)) {
				return getBooksByCategory(param, limit);
			} else {
				return getBookByIsbn(param);
			}
		} catch (DatabaseException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * A method for requesting all books of a specified category from the
	 * underlying service.
	 * 
	 * @param category
	 *            the name category which is requested
	 * @param limit
	 *            an optional limit
	 * @return all books of this category and status 200, if the parameters are
	 *         not valid no results are returned and an status of 400
	 * @throws DatabaseException
	 */
	private ResponseEntity<List<BookJSONWrapper>> getBooksByCategory(String category, String limit)
			throws DatabaseException {
		if (category == null || category.isEmpty() || (limit != null && Long.parseLong(limit) < 0)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BookJSONWrapper>>(bookService.getBooksByCategory(category, SearchMode.AVAILABLE)
				.stream().limit((limit == null || limit.isEmpty()) ? Long.MAX_VALUE : Long.parseLong(limit))
				.map(b -> new BookJSONWrapper(b)).collect(Collectors.toList()), HttpStatus.OK);
	}

	/**
	 * A method for requesting a single book by its ISBN from the underlying
	 * service.
	 * 
	 * @param isbn
	 *            the requested ISBN
	 * @return the book as JSON and status 200, if the book is not available
	 *         status 204, if the parameters are not valid no results and an
	 *         status of 400 are returned
	 * @throws DatabaseException
	 */
	private ResponseEntity<BookJSONWrapper> getBookByIsbn(String isbn) {
		if (isbn == null || isbn.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			Book book = bookService.getBookByIsbn(isbn, SearchMode.AVAILABLE);
			return new ResponseEntity<BookJSONWrapper>(new BookJSONWrapper(book), HttpStatus.OK);
		} catch (DatabaseException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
