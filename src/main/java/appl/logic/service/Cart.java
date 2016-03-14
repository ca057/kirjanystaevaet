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

	// Item aus Warenkorb entfernen
	void deleteBook(String isbn);

	// post order
	void postOrder(Book book);

	boolean isEmpty();

	void deleteContent();
	// Soll prüfen, ob Bücher da sind und entsprechende Meldung rausgeben.

}