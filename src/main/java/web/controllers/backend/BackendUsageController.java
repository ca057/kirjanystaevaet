package web.controllers.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.items.User;
import appl.logic.service.BookService;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;

@Controller
@RequestMapping(value = "/backend/nutzungsstatistiken")
public class BackendUsageController {

	private BookService bookService;

	private UserService userService;

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
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
		try {
			List<User> users = userService.getUsers();
			m.addAttribute("amountOfUsers", users.size());
			m.addAttribute("amountOfUsersUSER", users.stream().filter(u -> "USER".equals(u.getRole())).count());
			m.addAttribute("amountOfUsersADMIN", users.stream().filter(u -> "ADMIN".equals(u.getRole())).count());
		} catch (DatabaseException ignore) {
		}
		return "backend/usage";
	}
}
