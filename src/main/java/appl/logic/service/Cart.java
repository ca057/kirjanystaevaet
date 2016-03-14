package appl.logic.service;

import java.util.Map;

import appl.data.items.Book;
import exceptions.data.DatabaseException;

/**
 * @author Ludwig
 *
 */
public interface Cart {

	void addBook(Book book);

	Map<String, Integer> getBooks();

	String getPrice() throws DatabaseException;

	void deleteBook(String isbn);

	void postOrder(Book book);

	boolean isEmpty();

	void deleteContent();

}