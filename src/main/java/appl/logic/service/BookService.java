package appl.logic.service;

import java.util.List;
import java.util.Map;

import appl.data.enums.Searchfields;
import appl.data.items.Book;

public interface BookService {

	public List<Book> getAllBooks();

	public List<Book> getBooksByCategory(String category);

	public Book getBookByIsbn(String isbn);

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);

}
