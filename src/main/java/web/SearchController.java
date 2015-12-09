package web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.data.items.Author;
import appl.data.items.Book;
import appl.logic.service.CategoryService;

@Controller
public class SearchController {

	private String queryTerm = "";

	@Autowired
	private CategoryService service;

	public void setService(CategoryService service) {
		this.service = service;
	}

	@RequestMapping(value = "/suche", method = RequestMethod.GET)
	public String query(@RequestParam(value = "all", required = false) String all,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "isbn", required = false) String isbn,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "category", required = false) String category, Model m) {
		queryTerm = "";
		// m.addAttribute("results", processSearchTerms(all, title, author,
		// isbn, year, category));
		m.addAttribute("results", returnDummyBooks());
		m.addAttribute("query", queryTerm);
		return "search";
	}

	private List<Book> processSearchTerms(String all, String title, String author, String isbn, String year,
			String category) {
		if (all != null && !all.isEmpty()) {
			queryTerm = all;
			// return service.getBooksByOpenSearch(all);
		} else {
			String searchTitle = "";
			String searchAuthor = "";
			String searchYear = "";
			String searchIsbn = "";
			String searchCategory = "";
			if (title != null && !title.isEmpty()) {
				searchTitle = title;
				queryTerm += " Titel: " + title;
			}
			if (author != null && !author.isEmpty()) {
				searchAuthor = author;
				queryTerm += " Autor: " + author;
			}
			if (isbn != null && !isbn.isEmpty()) {
				searchIsbn = isbn;
				queryTerm += " ISBN: " + isbn;
			}
			if (year != null && !year.isEmpty()) {
				searchYear = year;
				queryTerm += " Jahr: " + year;
			}
			if (category != null && !category.isEmpty()) {
				searchCategory = category;
				queryTerm += " Kategorie: " + category;
			}
			// return service.getBooksByMetadata(searchTitle, searchAuthor,
			// searchYear, searchIsbn, searchCategory);
		}
		// FIXME !!!!!
		return null;
	}

	private List<Book> returnDummyBooks() {
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 10; i++) {
			Book tmp = new Book();
			Author author = new Author();
			author.setNameF("Chabo");
			author.setNameL("Babo");
			Set<Author> set = new HashSet<>();
			set.add(author);
			tmp.setAuthors(set);
			tmp.setDescription("Lorem ipsum text");
			tmp.setEdition("v" + i);
			tmp.setIsbn(i + "45674567");
			tmp.setPages(666);
			tmp.setPrice(i + 0.99);
			tmp.setTitle("Der Titel des wunderbaren Buches " + i);
			books.add(tmp);
		}
		return books;
	}
}
