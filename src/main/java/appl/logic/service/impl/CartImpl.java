package appl.logic.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import appl.data.items.Book;
import appl.enums.SearchMode;
import appl.logic.service.BookService;
import appl.logic.service.Cart;
import exceptions.data.DatabaseException;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
@Service
public class CartImpl implements Cart {

	@Autowired
	private BookService bookService;
	private Map<String, Integer> books = new HashMap<String, Integer>();

	/* (non-Javadoc)
	 * @see appl.logic.service.impl.CartInterface#addBook(appl.data.items.Book)
	 */
	@Override
	public void addBook(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("added book is null");
		}
		if (books.containsKey(book.getIsbn())) {
			books.put(book.getIsbn(), books.get(book.getIsbn()) + 1);
		} else {
			books.put(book.getIsbn(), 1);
		}
	}

	/* (non-Javadoc)
	 * @see appl.logic.service.impl.CartInterface#getBooks()
	 */
	@Override
	public Map<String, Integer> getBooks() {
		return books;
	}

	/* (non-Javadoc)
	 * @see appl.logic.service.impl.CartInterface#getPrice()
	 */
	@Override
	public String getPrice() throws DatabaseException {
		DecimalFormat df = new DecimalFormat("0.00");
		Set<String> keys = books.keySet();
		double sum = 0;
		for (String s : keys) {
			double singlePrice = bookService.getBookByIsbn(s, SearchMode.ALL).getPrice();
			sum += singlePrice;
		}
		return df.format(sum);
	}

	// Item aus Warenkorb entfernen
	/* (non-Javadoc)
	 * @see appl.logic.service.impl.CartInterface#deleteBook(java.lang.String)
	 */
	@Override
	public void deleteBook(String isbn) {
		if (isbn == null || isbn.isEmpty()) {
			throw new IllegalArgumentException("book to remove is null");
		}
		books.remove(isbn);
	}

	// post order
	/* (non-Javadoc)
	 * @see appl.logic.service.impl.CartInterface#postOrder(appl.data.items.Book)
	 */
	@Override
	public void postOrder(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("all books are null");
		}

	}

	/* (non-Javadoc)
	 * @see appl.logic.service.impl.CartInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return books.isEmpty();
	}

	/* (non-Javadoc)
	 * @see appl.logic.service.impl.CartInterface#deleteContent()
	 */
	@Override
	public void deleteContent() {
		books.clear();
	}
	// Soll prüfen, ob Bücher da sind und entsprechende Meldung rausgeben.
}
