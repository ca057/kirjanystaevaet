package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Book;
import appl.enums.SearchMode;
import appl.enums.Searchfields;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;

@Transactional
public interface BookDAO {

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map, SearchMode mode);

	public List<Book> getAllBooks();
	public List<Book> getAllBooks(SearchMode mode);

	
	public Book getBookByIsbn(String isbn) throws EntityDoesNotExistException;

	// public String insertBook(Book book)throws IsbnAlreadyExistsException;
	public String insertBook(Book book) throws DatabaseException;

	public void deleteBook(String isbn);

	public void updateBook(Book book);


	public void decrementStock(String isbn, int decrement) throws DatabaseException;

	public void setStockToNegative(String isbn);

	/**
	 * Returns a {@link List} with the most visited {@link Book}s sorted
	 * descending by their visit count. The number of books can be limited by
	 * the passed range.
	 * 
	 * @param range
	 *            the amount of books the returned map should contain
	 * @return the map with the amount of books passed as range sorted
	 *         descending by their visit count
	 */
	public List<Book> getMostVisitedBooks(int range);

	/**
	 * Returns a {@link List} with the least visited {@link Book}s sorted
	 * ascending by their visit count. The number of books can be limited by the
	 * passed range.
	 * 
	 * @param range
	 *            the amount of books the returned map should contain
	 * @return the map with the amount of books passed as range sorted ascending
	 *         by their visit count
	 */
	public List<Book> getLeastVisitedBooks(int range);

}
