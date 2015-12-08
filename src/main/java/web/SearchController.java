package web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.items.Book;
import appl.queryManagement.QueryCreatorImpl;

@Controller
public class SearchController {

	private String queryTerm = "";

	@Autowired
	private QueryCreatorImpl query;

	public void setQuery(QueryCreatorImpl query) {
		this.query = query;
	}

	@RequestMapping(value = "/suche", method = RequestMethod.GET)
	public String query(@RequestParam(value = "all", required = false) String all,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "isbn", required = false) String isbn,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "category", required = false) String category, Model m) {
		m.addAttribute("results", processSearchTerms(all, title, author, isbn, year, category));
		m.addAttribute("query", queryTerm);
		return "search";
	}

	private List<Book> processSearchTerms(String all, String title, String author, String isbn, String year,
			String category) {
		if (all != null && !all.isEmpty()) {
			queryTerm = all;
			return query.getBooksByOpenSearch(all);
		} else {
			String searchTitle = "";
			String searchAuthor = "";
			String searchYear = "";
			String searchIsbn = "";
			String searchCategory = "";
			if (title != null && !title.isEmpty()) {
				searchTitle = title;
				queryTerm += "Titel: " + title;
			}
			if (author != null && !author.isEmpty()) {
				searchAuthor = author;
				queryTerm += "; Autor: " + author;
			}
			if (isbn != null && !isbn.isEmpty()) {
				searchIsbn = isbn;
				queryTerm += "; ISBN: " + isbn;
			}
			if (year != null && !year.isEmpty()) {
				searchYear = year;
				queryTerm += "; Jahr: " + year;
			}
			if (category != null && !category.isEmpty()) {
				searchCategory = category;
				queryTerm += "; Kategorie: " + category;
			}
			return query.getBooksByMetadata(searchTitle, searchAuthor, searchYear, searchIsbn, searchCategory);
		}
	}
}
