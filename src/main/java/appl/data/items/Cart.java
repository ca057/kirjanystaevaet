package appl.data.items;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

//@Scope("session")
@Component
public class Cart {

	private List<Book> books = new ArrayList<Book>();

	public void addBook(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("added book is null");
		}
		books.add(book);
		// TODO implement me
	}

	public List<Book> getBooks() {
		return books;
	}

	public double getPrice() {
		return books.stream().parallel().mapToDouble(book -> book.getPrice()).sum();
	}

	// Item aus Warenkorb entfernen
	public void deleteBook(Book book) {
		if (book == null) {
			throw new IllegalArgumentException("added book is null");
		}
		books.remove(book);
	}

	// Warenbestand prüfen

	// "Zur Kasse"
}
