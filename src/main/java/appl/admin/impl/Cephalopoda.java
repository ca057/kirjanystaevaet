package appl.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.admin.DataKraken;
import appl.data.items.User;
import appl.logic.service.BookService;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;

/**
 * 
 * @author ca
 *
 */
@Component
public class Cephalopoda implements DataKraken {

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

	@Override
	public Map<String, ?> attack() {
		Map<String, Object> m = new HashMap<>();
		try {
			m.put("amountOfBooks", bookService.getAllBooks().size());
		} catch (DatabaseException ignore) {
		}
		try {
			m.put("amountOfCategories", bookService.getAllCategoryNames().size());
		} catch (DatabaseException ignore) {
		}
		try {
			m.put("amountOfAuthors", bookService.getAllAuthors().size());
		} catch (DatabaseException ignore) {
		}
		try {
			List<User> users = userService.getUsers();
			m.put("amountOfUsers", users.size());
			m.put("amountOfUsersUSER", (int) users.stream().filter(u -> "USER".equals(u.getRole())).count());
			m.put("amountOfUsersADMIN", (int) users.stream().filter(u -> "ADMIN".equals(u.getRole())).count());
		} catch (DatabaseException ignore) {
		}
		return m;
	}

}
