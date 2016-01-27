package appl.data.items;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
public class Cart {

	private List<Book> books = new ArrayList<Book>();

	public void addBook(Book book) {
		// TODO implement me
	}

	public List<Book> getBooks() {
		return books;
	}

	public double getPrice() {
		return books.stream().parallel().mapToDouble(book -> book.getPrice()).sum();
	}

	// Warenbestand prüfen

	// Item aus Warenkorb entfernen

	// "Zur Kasse"
}
