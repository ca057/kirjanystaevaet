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
	 * 
	 * @param searchTerm
	 * @return
	 */

	public List<Book> getBooksByOpenSearch(String searchTerm);

	/**
	 * returns a {@link List} with Books fulfilling the conditions given in the
	 * map and the {@link SearchMode}
	 * 
	 * @param map
	 * @param mode
	 * @return
	 */

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map, SearchMode mode);

	/**
	 * returns a {@link List} according to the {@link SearchMode}
	 * 
	 * @param mode
	 * @return
	 */
	public List<Book> getAllBooks(SearchMode mode);

	/**
	 * returns a {@link Book} according to the isbn
	 * 
	 * @param isbn
	 * @return
	 * @throws EntityDoesNotExistException
	 *             if book does not exist in Database
	 */
	public Book getBookByIsbn(String isbn) throws EntityDoesNotExistException;

	/**
	 * returns the id of {@link Book} which is the isbn
	 * 
	 * @param book
	 * @return
	 * @throws DatabaseException
	 */

	public String insertBook(Book book) throws DatabaseException;

	/**
	 * physically deletes a {@link Book}
	 * 
	 * @param isbn
	 */

	public void deleteBook(String isbn);

	/**
	 * 
	 * @param book
	 *            must contain all the data to be updated
	 */

	public void updateBook(Book book);

	/**
	 * decrements {@link stock} of {@link Book} by the given value. Mainly used
	 * by {@link OrderService}
	 * 
	 * @param isbn
	 * @param decrement
	 * @throws DatabaseException
	 */
	public void decrementStock(String isbn, int decrement) throws DatabaseException;

	/**
	 * @param isbn
	 */

	public void setStockToNegative(String isbn);
}
