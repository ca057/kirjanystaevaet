package appl.databasemanagement;

import java.util.List;
import java.util.Map;

import appl.items.Book;
import appl.items.Category;

public interface BookDAO {

	public List<Category> getCategories();

	public List<Book> getBooksByCategory(String category);

	public Book getBookByIsbn(int isbn);

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);

	public boolean insertBook(Book book);

	public boolean deleteBook(Book book);

	public boolean updateBook(Book book, Map<Searchfields, String> map);

}
