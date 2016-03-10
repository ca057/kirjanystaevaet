package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Book;
import appl.enums.SearchMode;
import appl.enums.Searchfields;
import appl.logic.service.OrderService;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;

@Transactional
public interface BookDAO {
	/**
	 * Is not implemented, can be done in the future to extend the project
	 * @param searchTerm
	 * @return
	 */

	public List<Book> getBooksByOpenSearch(String searchTerm);
	/**
	 * returns a {@link List} with Books fulfilling the conditions given in the map and the {@link SearchMode}
	 * @param map
	 * @param mode
	 * @return
	 */

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map, SearchMode mode);

	/**
	 * returns a {@link List} according to the {@link SearchMode}
	 * @param mode
	 * @return
	 */
	public List<Book> getAllBooks(SearchMode mode);

	/**
	 * returns a {@link Book} according to the isbn
	 * @param isbn
	 * @return
	 * @throws EntityDoesNotExistException if book does not exist in Database
	 */
	public Book getBookByIsbn(String isbn) throws EntityDoesNotExistException;
	/**
	 * returns the id of {@link Book} which is the isbn
	 * @param book
	 * @return
	 * @throws DatabaseException
	 */

	public String insertBook(Book book) throws DatabaseException;
	/**
	 * physically deletes a {@link Book}
	 * @param isbn
	 */

	public void deleteBook(String isbn);
	/**
	 * 
	 * @param book must contain all the data to be updated
	 */

	public void updateBook(Book book);


	/**
	 * decrements {@link stock} of {@link Book} by the given value. Mainly used by {@link OrderService}
	 * @param isbn
	 * @param decrement
	 * @throws DatabaseException
	 */
	public void decrementStock(String isbn, int decrement) throws DatabaseException;
	/**
	 * @param isbn
	 */

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
