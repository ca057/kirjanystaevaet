package web.controllers.api;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import appl.data.items.Book;
import appl.enums.SearchMode;
import appl.logic.service.BookService;
import exceptions.data.DatabaseException;
import web.jsonwrappers.ApiBookJSONWrapper;

/**
 * Controller responsible for the Rest-API which can be used via the sub-URL
 * '/api/v1/...'.
 * 
 * @author Christian
 *
 */
@RestController
public class RestApiController {

	private Predicate<String> limitIsValid = l -> l != null && !l.isEmpty() && Integer.parseInt(l) >= 0;
	private Predicate<String> isNotNullAndEmpty = s -> s != null && !s.isEmpty();
	private Function<List<Book>, List<ApiBookJSONWrapper>> convertList = books -> books.stream()
			.map(b -> new ApiBookJSONWrapper(b)).collect(Collectors.toList());

	@Autowired
	private BookService bookService;

	/**
	 * Depending on the passed parameter in the URL, this method decides which
	 * data needs to be requested from the underlying services. The request
	 * parameters are passed to private methods which perform the final request.
	 * 
	 * @param param
	 *            the requested entity, e.g. 'books'
	 * @param isbn
	 *            if a book with a specified ISBN is requested
	 * @param category
	 *            if all books of a specified category are requested
	 * @param limit
	 *            will get parsed to an {@code int}, used to limit the number of
	 *            datasets returned
	 * @return a {@link ResponseEntity} with the data if present and a
	 *         {@link HttpStatus} code indicating success or failure
	 */
	@RequestMapping(value = "/api/v1/{param}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> getBooks(@PathVariable("param") String param,
			@RequestParam(value = "isbn", required = false) String isbn,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "limit", required = false) String limit) {
		if (param == null || param.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if ("books".equals(param)) {
			if (isNotNullAndEmpty.test(category)) {
				return getBooksByCategory(category, limit);
			} else if (isNotNullAndEmpty.test(isbn)) {
				return getBookByIsbn(isbn);
			} else {
				return getAllBooks(limit);
			}
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Responsible for getting all books by a specified category.
	 * 
	 * @param category
	 * @param limit
	 * @return
	 */
	private ResponseEntity<List<ApiBookJSONWrapper>> getBooksByCategory(String category, String limit) {
		try {
			if (!bookService.isExistingCategory(category)) {
				return new ResponseEntity<List<ApiBookJSONWrapper>>(HttpStatus.BAD_REQUEST);
			}
			List<Book> books;
			if (limitIsValid.test(limit)) {
				books = bookService.getBooksByCategory(category, SearchMode.AVAILABLE, Integer.parseInt(limit));
			} else {
				books = bookService.getBooksByCategory(category, SearchMode.AVAILABLE);
			}
			return new ResponseEntity<List<ApiBookJSONWrapper>>(convertList.apply(books), HttpStatus.OK);
		} catch (DatabaseException e) {
			return new ResponseEntity<List<ApiBookJSONWrapper>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Responsible for requesting a single book specified by its ISBN.
	 * 
	 * @param isbn
	 * @return
	 */
	private ResponseEntity<ApiBookJSONWrapper> getBookByIsbn(String isbn) {
		try {
			return new ResponseEntity<ApiBookJSONWrapper>(
					new ApiBookJSONWrapper(bookService.getBookByIsbn(isbn, SearchMode.AVAILABLE)), HttpStatus.OK);
		} catch (DatabaseException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Responsible for requesting all books.
	 * 
	 * @param limit
	 * @return
	 */
	private ResponseEntity<List<ApiBookJSONWrapper>> getAllBooks(String limit) {
		try {
			List<Book> books;
			if (limitIsValid.test(limit)) {
				books = bookService.getAllBooks(SearchMode.AVAILABLE, Integer.parseInt(limit));
			} else {
				books = bookService.getAllBooks(SearchMode.AVAILABLE);
			}
			return new ResponseEntity<List<ApiBookJSONWrapper>>(convertList.apply(books), HttpStatus.OK);
		} catch (DatabaseException e) {
			return new ResponseEntity<List<ApiBookJSONWrapper>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
