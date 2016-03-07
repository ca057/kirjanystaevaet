package appl.logic.service;
/**
 * @author Madeleine, Johannes
 */
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import appl.enums.SearchMode;
import appl.enums.Searchfields;
import exceptions.data.AuthorMayExistException;
import exceptions.data.DatabaseException;

public interface BookService {
	// Category Methoden

	// Abfragen

	public Category getCategoryByExactName(String name) throws DatabaseException;

	public Category getCategoryById(int id) throws DatabaseException;

	public List<String> getAllCategoryNames() throws DatabaseException;

	public List<Category> getAllCategories() throws DatabaseException;

	// Insert
	/**
	 * 
	 * @param name
	 * @return Die neu erzeugte ID
	 * @throws DatabaseException
	 *             E.G. Thrown if one tries to insert an Category that already
	 *             exists
	 */
	public int insertCategory(String name) throws DatabaseException;

	// Update

	// Delete

	public void deleteCategory(String name) throws DatabaseException;

	// Author Methoden
	// Abfragen

	public List<Author> getAuthorByIsbn(String isbn) throws DatabaseException;

	public List<Author> getAuthorByExactName(String NameF, String NameL) throws DatabaseException;

	public Author getAuthorById(int id) throws DatabaseException;

	public List<Author> getAllAuthors() throws DatabaseException;

	// Insert
	/**
	 * 
	 * @param nameF
	 * @param nameL
	 * @param newAuthor
	 *            Wird auf TRUE gesetzt, wenn man auf jeden Fall einen neuen
	 *            Autoren in die Datenbank einfügen will, auch wenn es schon
	 *            einen mit diesem Namen gibt. In diesem Fall kann die Exception
	 *            ignoriert werden, weil sie nie geworfen werden wird
	 * @return Die ID des neu erstellten Eintrags
	 * @throws AuthorMayExistException
	 *             Wenn newAuthor = false und wenn es schon mindestens einen
	 *             Autoren mit Exakt dem angegeben Vor- und Nachnamen gibt
	 */
	public int insertAuthor(String nameF, String nameL, boolean newAuthor) throws AuthorMayExistException;

	// Update

	// Delete
	/**
	 * 
	 * @param id
	 * @throws DatabaseException
	 *             If Author does not exist, or if a general DB-Error Happens
	 */
	void deleteAuthor(int id) throws DatabaseException;

	// Book Methoden

	// Abfragen
	public List<Book> getAllBooks() throws DatabaseException;
	public List<Book> getAllBooks(SearchMode mode) throws DatabaseException;


	public List<Book> getBooksByCategory(String category) throws DatabaseException;
	public List<Book> getBooksByCategory(String category, SearchMode mode) throws DatabaseException;


	public Book getBookByIsbn(String isbn) throws DatabaseException;

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map) throws DatabaseException;
	public List<Book> getBooksByMetdata(Map<Searchfields, String> map, SearchMode mode) throws DatabaseException;
	// Insert
	/**
	 * 
	 * @param map
	 *            contains information about simple fields
	 * @param authorIds
	 *            may only contain ids of existing authors
	 * @param categoryIds
	 *            may only contain ids of existing categories
	 * @throws DatabaseException
	 *             thrown when categories or authors do not exist in the
	 *             Database, also thrown in case of general Database Errors.
	 *             Errormessage gives more Details.
	 */
	// public void insertBook(Map<Searchfields, String> map, Set<Integer>
	// authorIds, Set<Integer> categoryIds)throws IsbnAlreadyExistsException ;
	public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds)
			throws DatabaseException;

	// Update
	
	public void updateBook(String isbn, Map<Searchfields, String> data) throws DatabaseException;
	
	public void deleteCategoryOfBook(String isbn, int categoryId) throws DatabaseException;
	
	public void addCategoryToBook(String isbn, int categoryId) throws DatabaseException;


	// Update Stock
	/**
	 * 
	 * @param isbn
	 * @param additional
	 * @return the new Stock
	 * @throws DatabaseException
	 */
	public int updateStock(String isbn, int additional) throws DatabaseException;

	// Delete
	public void deleteBook(String isbn) throws DatabaseException;

	/**
	 * Returns the number of times the page of a specific {@link Book} was
	 * visited.
	 * 
	 * @param isbn
	 *            the ISBN number of the book
	 * @return the number of visits
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public int getVisitCount(String isbn) throws DatabaseException;

	/**
	 * Updates the number of visits of a specific book.
	 * 
	 * @param isbn
	 *            the ISBN number of the book
	 * @param additional
	 *            the number to increase the visitCount with
	 * @return the new value of the {@code visitCount}
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public int increaseVisitCount(String isbn, int additional) throws DatabaseException;

	/**
	 * Returns a {@link SortedMap} with the most visited {@link Book}s sorted
	 * descending by their visit count. The number of books can be limited by
	 * the passed range.
	 * 
	 * @param range
	 *            the amount of books the returned map should contain
	 * @return the map with the amount of books passed as range sorted
	 *         descending by their visit count
	 */
	public SortedMap<Book, Integer> getMostVisitedBooks(int range);

	/**
	 * Returns a {@link SortedMap} with the least visited {@link Book}s sorted
	 * ascending by their visit count. The number of books can be limited by the
	 * passed range.
	 * 
	 * @param range
	 *            the amount of books the returned map should contain
	 * @return the map with the amount of books passed as range sorted ascending
	 *         by their visit count
	 */
	public SortedMap<Book, Integer> getLeastVisitedBooks(int range);

}
