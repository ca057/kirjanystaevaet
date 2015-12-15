package appl.data.dao;

import java.util.List;
import java.util.Map;

import appl.data.enums.Searchfields;
import appl.data.items.Book;

public interface BookDAO {

	public List<Book> getBooksByCategory(String categoryName);

	public Book getBookByIsbn(int isbn);

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);

	public boolean insertBook(Book book);

	public boolean deleteBook(Book book);

	public boolean updateBook(Book book, Map<Searchfields, String> map);

	public Book executeQuery(String query);

}