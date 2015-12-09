package appl.logic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import appl.data.items.Book;

@Service
public interface BookService {

	public List<Book> getAllBooks();

	public List<String> getBooksByCategory(String category);

	public Book getBookByIsbn(int isbn);

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(String title, String author, String year, String isbn, String category);

}
