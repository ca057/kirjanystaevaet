package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.items.Book;

public interface BookDAO {

	@Transactional
	public List<Book> getBooksByCategory(String categoryName);

	@Transactional
	public Book getBookByIsbn(int isbn);

	@Transactional
	public List<Book> getBooksByOpenSearch(String searchTerm);

	@Transactional
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);

	@Transactional
	public boolean insertBook(Book book);

	@Transactional
	public boolean deleteBook(Book book);

	@Transactional
	public boolean updateBook(Book book, Map<Searchfields, String> map);

	public Book executeQuery(String query);

}
