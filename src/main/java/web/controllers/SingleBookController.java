package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.items.Book;
import appl.logic.service.BookService;
import exceptions.data.DatabaseException;

@Controller
@RequestMapping(value = "/buch/{isbn}")
public class SingleBookController {

	@Autowired
	private BookService bookService;

	public void setService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getBook(@PathVariable("isbn") String isbn, Model m) {
		if (isbn != null && !isbn.isEmpty()) {
			try {
				Book book = bookService.getBookByIsbn(isbn);
				m.addAttribute("book", book);
			} catch (DatabaseException e) {
				return "book?error";
			}
		}
		return "book";
	}
}
