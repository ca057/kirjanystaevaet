package appl.logic.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import appl.data.enums.Searchfields;
import appl.data.items.Book;

@Service
public interface BookService {

	public List<Book> getAllBooks();

	public List<String> getBooksByCategory(String category);

	public Book getBookByIsbn(String isbn);

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);

}
