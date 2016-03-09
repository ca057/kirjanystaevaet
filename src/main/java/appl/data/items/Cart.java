package appl.data.items;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import appl.logic.service.BookService;
import exceptions.data.DatabaseException;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
@Component
public class Cart {

	@Autowired
	private BookService bookService;
	private Map<String, Integer> books = new HashMap<String, Integer>();

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

	public Map<String, Integer> getBooks() {
		return books;
	}
	// private List<Book> books = new ArrayList<Book>();
	//
	// public void addBook(Book book) {
	// if (book == null) {
	// throw new IllegalArgumentException("added book is null");
	// }
	// books.add(book);
	// // TODO implement me
	// }
	//
	// public List<Book> getBooks() {
	// return books;
	// }

	// TODO: adjust getPrice!!!!
	public double getPrice() throws DatabaseException {
		Set<String> keys = books.keySet();
		for (String s : keys) {
			return bookService.getBookByIsbn(s).getPrice().sum();

		}
		return 0;
	}

	// Item aus Warenkorb entfernen
	public void deleteBook(String isbn) {
		if (isbn == null) {
			throw new IllegalArgumentException("book to remove is null");
		}
		books.remove(isbn);
	}

	// post order
	public void postOrder(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("all books are null");
		}

	}

	public boolean isEmpty() {
		return books.isEmpty();
	}

	public void deleteContent() {
		books.clear();
	}
	// Warenbestand prüfen Order-Service muss Liste mit Büchern gegeben werden.
	// Soll prüfen, ob Bücher da sind und entsprechende Meldung rausgeben.

}
