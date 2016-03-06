package appl.data.items;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

//@Scope("session")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
@Component
public class Cart {

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
	// public double getPrice() {
	// return books.stream().parallel().mapToDouble(book ->
	// book.getPrice()).sum();
	// }

	// Item aus Warenkorb entfernen
	public void deleteBook(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("book to remove is null");
		}
		books.remove(book);
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
