package appl.logic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.data.items.Book;
import appl.enums.UserRoles;
import appl.logic.service.BookService;
import appl.logic.service.DataKraken;
import appl.logic.service.OrderService;
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

	private OrderService orderService;

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
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
			map.put("amountOfUsers", userService.getNumberOfAccounts());
			map.put("amountOfUsersUSER", userService.getNumberOfAccounts(UserRoles.USER));
			map.put("amountOfUsersADMIN", userService.getNumberOfAccounts(UserRoles.ADMIN));
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
		computeMostAndLeastVisitedBooks(map);
	}

	private void computeTopSellersAndShelfWarmers(Map<String, Object> map) {
		try {
			List<Entry<String, Integer>> topSellers = orderService.getBestsellers();
			SortedMap<Book, Integer> fiveMost = new TreeMap<>();
			for (int i = 0; i < (topSellers.size() > 5 ? 5 : topSellers.size()); i++) {
				fiveMost.put(bookService.getBookByIsbn(topSellers.get(i).getKey()), topSellers.get(i).getValue());
			}
			map.put("topSellers", fiveMost);
			// TODO add shelfWarmers
			map.put("shelfWarmers", fiveMost);
		} catch (DatabaseException ignore) {
		}
	}

	private void computeMostAndLeastVisitedBooks(Map<String, Object> map) {
		map.put("mostVisitedBooks", bookService.getMostVisitedBooks(5));
		map.put("leastVisitedBooks", bookService.getLeastVisitedBooks(5));
	}

}
