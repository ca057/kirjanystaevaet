package web;

import java.util.ArrayList;
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
		String queryTerm = "Keine Suchanfrage gestellt.";
		List<Book> results = new ArrayList();
		if (all != null && !all.isEmpty()) {
			results = query.getBooksByOpenSearch(all);
			queryTerm = all;
		} else {
			String searchTitle = "";
			String searchAuthor = "";
			String searchYear = "";
			String searchIsbn = "";
			String searchCategory = "";
			if (title != null && !title.isEmpty()) {
				searchTitle = title;
			}
			if (author != null && !author.isEmpty()) {
				searchAuthor = author;
			}
			if (isbn != null && !isbn.isEmpty()) {
				searchIsbn = isbn;
			}
			if (year != null && !year.isEmpty()) {
				searchYear = year;
			}
			if (category != null && !category.isEmpty()) {
				searchCategory = category;
			}
			results = query.getBooksByMetadata(searchTitle, searchAuthor, searchYear, searchIsbn, searchCategory);
		}
		m.addAttribute("query", queryTerm);
		m.addAllAttributes(results);
		return "search";
	}
}
