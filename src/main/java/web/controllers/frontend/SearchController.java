package web.controllers.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.data.items.Book;
import appl.enums.SearchMode;
import appl.enums.Searchfields;
import appl.logic.service.BookService;
import exceptions.data.DatabaseException;

/**
 * Handles the search.
 * 
 * @author Christian
 * @author Ludwig
 *
 */
@Controller
public class SearchController {

	private Predicate<String> stringIsNotNullAndEmpty = s -> s != null && !s.isEmpty();

	private String queryTerm = "";

	@Autowired
	private BookService bookService;

	/**
	 * Handles a GET request with the search terms as parameters. The results
	 * will be added to the {@link Model} as list, the search terms will be
	 * concatenated to a string and also added to the model. If an error occurs
	 * while performing the search, a message will be added as attribute to the
	 * model.
	 * 
	 * @param title
	 *            when searching for the title
	 * @param authorFirst
	 *            the first name of the author
	 * @param authorLast
	 *            the last name of the author
	 * @param isbn
	 *            the isbn of the book
	 * @param year
	 *            the year of publishing
	 * @param category
	 *            the category the book belongs to
	 * @param m
	 *            the {@link Model} for the view
	 * @return the name of the view responsible for rendering the search and the
	 *         results
	 */
	@RequestMapping(value = "/suche", method = RequestMethod.GET)
	public String query(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "authorFirst", required = false) String authorFirst,
			@RequestParam(value = "authorLast", required = false) String authorLast,
			@RequestParam(value = "isbn", required = false) String isbn,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "category", required = false) String category, Model m) {
		try {
			queryTerm = "";
			m.addAttribute("results", processSearchTerms(title, authorFirst, authorLast, isbn, year, category));
			m.addAttribute("query", queryTerm);
		} catch (DatabaseException ignore) {
			m.addAttribute("error", "Bei der Suche ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut.");
		}
		return "search";
	}

	/**
	 * Processes the given list of search terms. Checks each term, if it is not
	 * null or empty and proceeds with the trimmed {@code String}.
	 * 
	 * @param title
	 * @param authorFirst
	 * @param authorLast
	 * @param isbn
	 * @param year
	 * @param category
	 * @return the result of the open or specific search
	 * @throws DatabaseException
	 *             if an error occurs while getting the results from the
	 *             database
	 */
	private List<Book> processSearchTerms(String title, String authorFirst, String authorLast, String isbn, String year,
			String category) throws DatabaseException {
		Map<Searchfields, String> searchMap = new HashMap<Searchfields, String>();
		if (stringIsNotNullAndEmpty.test(title)) {
			searchMap.put(Searchfields.title, title.trim());
			queryTerm += " Titel: " + title.trim();
		}
		if (stringIsNotNullAndEmpty.test(authorFirst)) {
			searchMap.put(Searchfields.nameF, authorFirst.trim());
			queryTerm += " Vorname: " + authorFirst.trim();
		}
		if (stringIsNotNullAndEmpty.test(authorLast)) {
			searchMap.put(Searchfields.nameL, authorLast.trim());
			queryTerm += " Nachname: " + authorLast;
		}
		if (stringIsNotNullAndEmpty.test(isbn)) {
			searchMap.put(Searchfields.isbn, isbn.trim());
			queryTerm += " ISBN: " + isbn;
		}
		if (stringIsNotNullAndEmpty.test(year)) {
			searchMap.put(Searchfields.pubdate, year.trim());
			queryTerm += " Jahr: " + year;
		}
		if (stringIsNotNullAndEmpty.test(category)) {
			searchMap.put(Searchfields.categoryName, category.trim());
			queryTerm += " Kategorie: " + category;
		}
		return bookService.getBooksByMetdata(searchMap, SearchMode.AVAILABLE);
	}
}
