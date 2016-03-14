package appl.logic.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.enums.SearchMode;
import appl.enums.UserRoles;
import appl.logic.service.BookService;
import appl.logic.service.DataKraken;
import appl.logic.service.OrderService;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;

/**
 * Implementation of a {@link DataKraken}. Collects all the data from the
 * different services and processes them for displaying them in the views.
 * 
 * @author Christian
 *
 */
@Component
public class Cephalopoda implements DataKraken {

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Override
	public Map<String, ?> attack() throws DatabaseException {
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
			map.put("amountOfBooks", bookService.getAllBooks(SearchMode.SELL).size());
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
	 * Wraps the computation of more complex data. In the current implementation
	 * it collects datasets and adds them to passed map. Could be used to
	 * interrelate the different datasets.
	 * 
	 * @param m
	 *            the map the processed data should be added to
	 */
	private void consumeComplexData(Map<String, Object> map) throws DatabaseException {
		getTopSellersAndShelfWarmers(map);
		getMostAndLeastVisitedBooks(map);
	}

	private void getTopSellersAndShelfWarmers(Map<String, Object> map) {
		try {
			map.put("topSellers", orderService.getBestsellers(5));
			map.put("shelfWarmers", orderService.getShelfWarmers(5));
		} catch (DatabaseException ignore) {
		}
	}

	private void getMostAndLeastVisitedBooks(Map<String, Object> map) throws DatabaseException {
		map.put("mostVisitedBooks", bookService.getMostVisitedBooks(5));
		map.put("leastVisitedBooks", bookService.getLeastVisitedBooks(5));
	}

}
