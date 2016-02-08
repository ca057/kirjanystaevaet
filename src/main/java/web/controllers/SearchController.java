package web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.data.enums.Searchfields;
import appl.data.items.Book;
import appl.logic.service.BookService;

@Controller
public class SearchController {

	private String queryTerm = "";

	@Autowired
	private BookService bookService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "/suche", method = RequestMethod.GET)
	public String query(@RequestParam(value = "all", required = false) String all,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "authorFirst", required = false) String authorFirst,
			@RequestParam(value = "authorLast", required = false) String authorLast,
			@RequestParam(value = "isbn", required = false) String isbn,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "category", required = false) String category, Model m) {
		queryTerm = "";
		m.addAttribute("results", processSearchTerms(all, title, authorFirst, authorLast, isbn, year, category));
		m.addAttribute("query", queryTerm);
		return "search";
	}

	/**
	 * Processes the given list of search terms. Checks each term, if it is not
	 * null or empty and proceeds with the trimmed {@code String}. If the
	 * {@code all} is passed with an non-empty string, an open search is started
	 * and all other terms are ignored. Otherwise a specific search is started
	 * with the passed parameters except {@code all}.
	 * 
	 * @param all
	 * @param title
	 * @param authorFirst
	 * @param authorLast
	 * @param isbn
	 * @param year
	 * @param category
	 * @return the result of the open or specific search
	 */
	private List<Book> processSearchTerms(String all, String title, String authorFirst, String authorLast, String isbn,
			String year, String category) {
		if (all != null && !all.isEmpty()) {
			queryTerm = all.trim();
			return bookService.getBooksByOpenSearch(all.trim());
		} else {
			Map<Searchfields, String> searchMap = new HashMap<Searchfields, String>();
			if (title != null && !title.isEmpty()) {
				searchMap.put(Searchfields.title, title.trim());
				queryTerm += " Titel: " + title.trim();
			}
			if (authorFirst != null && !authorFirst.isEmpty()) {
				searchMap.put(Searchfields.nameF, authorFirst.trim());
				queryTerm += " Vorname: " + authorFirst.trim();
			}
			if (authorLast != null && !authorLast.isEmpty()) {
				searchMap.put(Searchfields.nameL, authorLast.trim());
				queryTerm += " Nachname: " + authorLast;
			}
			if (isbn != null && !isbn.isEmpty()) {
				searchMap.put(Searchfields.isbn, isbn.trim());
				queryTerm += " ISBN: " + isbn;
			}
			if (year != null && !year.isEmpty()) {
				searchMap.put(Searchfields.pubdate, year.trim());
				queryTerm += " Jahr: " + year;
			}
			if (category != null && !category.isEmpty()) {
				searchMap.put(Searchfields.categoryName, category.trim());
				queryTerm += " Kategorie: " + category;
			}
			return bookService.getBooksByMetadata(searchMap);
		}
	}
}
