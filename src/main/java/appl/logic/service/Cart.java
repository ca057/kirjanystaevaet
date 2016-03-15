package appl.logic.service;

import java.util.Map;

import appl.data.items.Book;
import exceptions.data.DatabaseException;

/**
 * @author Ludwig
 *
 */
public interface Cart {

	/**
	 * Adds a book to the map books (see CartImpl.java)
	 * 
	 * @param book
	 */
	void addBook(Book book);

	/**
	 * Returns the map books in order to make the items visible in the view (see
	 * CartImpl.java, CartController.java).
	 * 
	 * @return
	 */
	Map<String, Integer> getBooks();

	/**
	 * Calculates the sum of the prices of the singles items stored in the cart.
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	String getPrice() throws DatabaseException;

	/**
	 * Removes a single item from the cart.
	 * 
	 * @param isbn
	 */
	void deleteBook(String isbn);

	/**
	 * Returns Boolean in order to check whether the cart is empty before an
	 * order is conducted.
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * Removes all items from the cart after an order was conducted.
	 */
	void deleteContent();

}