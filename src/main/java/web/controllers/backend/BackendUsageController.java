package web.controllers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.logic.service.BookService;
import exceptions.data.DatabaseException;

@Controller
@RequestMapping(value = "/backend/nutzungsstatistiken")
public class BackendUsageController {

	private BookService bookService;

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getUsage(Model m) {
		try {
			m.addAttribute("amountOfBooks", bookService.getAllBooks().size());
		} catch (DatabaseException ignore) {
		}
		try {
			m.addAttribute("amountOfCategories", bookService.getAllCategoryNames().size());
		} catch (DatabaseException ignore) {
		}
		try {
			m.addAttribute("amountOfAuthors", bookService.getAllAuthors().size());
		} catch (DatabaseException ignore) {
		}
		return "backend/usage";
	}
}
