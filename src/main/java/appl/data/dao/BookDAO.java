package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.items.Book;

@Transactional
public interface BookDAO {

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);

	public void insertBook(Book book);

	public void deleteBook(Book book);

	public void updateBook(Book book, Map<Searchfields, String> map);

	public List<Book> getAllBooks();

}
