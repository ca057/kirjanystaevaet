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
		consumeBasicData(m);
		consumeComplexData(m);
		return m;
	}

	/**
	 * Gets the basic data from the services and adds it to the map. If there is
	 * an exception while retrieving one part of the data, it will be ignored.
	 * The view handle displaying the missing dataset.
	 * 
	 * @param map
	 *            the map the data will be added to
	 */
	private void consumeBasicData(Map<String, Object> map) {
		try {
			map.put("amountOfBooks", bookService.getAllBooks().size());
		} catch (DatabaseException ignore) {
		}
		try {
			map.put("amountOfCategories", bookService.getAllCategoryNames().size());
		} catch (DatabaseException ignore) {
		}
		try {
			map.put("amountOfAuthors", bookService.getAllAuthors().size());
		} catch (DatabaseException ignore) {
		}
		try {
			List<User> users = userService.getUsers();
			map.put("amountOfUsers", users.size());
			map.put("amountOfUsersUSER", (int) users.stream().filter(u -> "USER".equals(u.getRole())).count());
			map.put("amountOfUsersADMIN", (int) users.stream().filter(u -> "ADMIN".equals(u.getRole())).count());
		} catch (DatabaseException ignore) {
		}
	}

	/**
	 * Compute top sellers and shelf warmers. Get users which order more than
	 * others. Find users which did not ordered anything.
	 * 
	 * @param m
	 */
	private void consumeComplexData(Map<String, Object> map) {
		computeTopSellersAndShelfWarmers(map);
		computeMostAndLeastActiveUsers(map);
		computeMostAndLeastVisitedBooks(map);
	}

	private void computeTopSellersAndShelfWarmers(Map<String, Object> map) {

	}

	private void computeMostAndLeastActiveUsers(Map<String, Object> map) {
		// TODO Auto-generated method stub

	}

	private void computeMostAndLeastVisitedBooks(Map<String, Object> map) {
		// TODO Auto-generated method stub

	}

}
