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

	private List<Book> processSearchTerms(String all, String title, String authorFirst, String authorLast, String isbn,
			String year, String category) {
		if (all != null && !all.isEmpty()) {
			queryTerm = all;
			return bookService.getBooksByOpenSearch(all);
		} else {
			String searchTitle = "";
			String searchAuthorFirst = "";
			String searchAuthorLast = "";
			String searchYear = "";
			String searchIsbn = "";
			String searchCategory = "";
			Map<Searchfields, String> searchMap = new HashMap<Searchfields, String>();
			if (title != null && !title.isEmpty()) {
				searchTitle = title;
				searchMap.put(Searchfields.title, searchTitle);
				queryTerm += " Titel: " + title;
			}
			if (authorFirst != null && !authorFirst.isEmpty()) {
				searchAuthorFirst = authorFirst;
				searchMap.put(Searchfields.nameF, searchAuthorFirst);
				queryTerm += " Vorname: " + authorFirst;
			}
			if (authorLast != null && !authorLast.isEmpty()) {
				searchAuthorLast = authorLast;
				searchMap.put(Searchfields.nameL, searchAuthorLast);
				queryTerm += " Nachname: " + authorLast;
			}
			if (isbn != null && !isbn.isEmpty()) {
				searchIsbn = isbn;
				searchMap.put(Searchfields.isbn, searchIsbn);
				queryTerm += " ISBN: " + isbn;
			}
			if (year != null && !year.isEmpty()) {
				searchYear = year;
				searchMap.put(Searchfields.pubdate, searchYear);
				queryTerm += " Jahr: " + year;
			}
			if (category != null && !category.isEmpty()) {
				searchCategory = category;
				searchMap.put(Searchfields.categoryName, searchCategory);
				queryTerm += " Kategorie: " + category;
			}
			return bookService.getBooksByMetadata(searchMap);
		}
	}
}
