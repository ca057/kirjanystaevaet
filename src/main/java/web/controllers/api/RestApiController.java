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
 * Controller manages the API.
 * 
 * @author Christian
 *
 */
@Controller
public class RestApiController {

	private BookService bookService;

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "/api/v1/books", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<BookJSONWrapper>> getAllBooks(
			@RequestParam(value = "limit", required = false) String limit) {
		try {
			List<BookJSONWrapper> allBooks = bookService.getAllBooks(SearchMode.AVAILABLE).stream()
					.limit((limit == null || limit.isEmpty()) ? Long.MAX_VALUE : Long.parseLong(limit))
					.map(b -> new BookJSONWrapper(b)).collect(Collectors.toList());
			return new ResponseEntity<List<BookJSONWrapper>>(allBooks, HttpStatus.OK);
		} catch (DatabaseException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/api/v1/books/{param}", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<?> getBooksByParamter(@PathVariable("param") String param,
			@RequestParam(value = "limit", required = false) String limit) {
		if (param == null || param.isEmpty()) {
			throw new IllegalArgumentException("The passed parameter is null or an empty string.");
		}
		try {
			if (bookService.getAllCategoryNames().stream().map(s -> s.toUpperCase()).collect(Collectors.toList())
					.contains(param.toUpperCase())) {
				return getBooksByCategory(param, limit);
			} else {
				return getBookByIsbn(param);
			}
		} catch (DatabaseException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private ResponseEntity<List<BookJSONWrapper>> getBooksByCategory(String category, String limit)
			throws DatabaseException {
		if (category == null || category.isEmpty()) {
			throw new IllegalArgumentException("The passed category is null or an empty string.");
		}
		return new ResponseEntity<List<BookJSONWrapper>>(bookService.getBooksByCategory(category, SearchMode.AVAILABLE)
				.stream().limit((limit == null || limit.isEmpty()) ? Long.MAX_VALUE : Long.parseLong(limit))
				.map(b -> new BookJSONWrapper(b)).collect(Collectors.toList()), HttpStatus.OK);
	}

	private ResponseEntity<BookJSONWrapper> getBookByIsbn(String isbn) throws DatabaseException {
		if (isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException("The passed category is null or an empty string.");
		}
		Book book = bookService.getBookByIsbn(isbn);
		if (book.getStock() > 0) {
			return new ResponseEntity<BookJSONWrapper>(new BookJSONWrapper(book), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
