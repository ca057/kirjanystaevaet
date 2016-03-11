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

	/**
	 * Gets Category object by its exact name, case-insensitive
	 * @param name
	 * @return
	 * @throws DatabaseException
	 */
	public Category getCategoryByExactName(String name) throws DatabaseException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	public Category getCategoryById(int id) throws DatabaseException;

	/**
	 * @return {@link List} of all exisiting Categorynames
	 * @throws DatabaseException
	 */
	public List<String> getAllCategoryNames() throws DatabaseException;

	/**
	 * @return {@link List} of all Categories as Object
	 * @throws DatabaseException
	 */
	public List<Category> getAllCategories() throws DatabaseException;
	
	/**
	 * Gets the exact name of a category in a case-insensitive Search
	 * @param name
	 * @return
	 * @throws DatabaseException
	 */
	public String getCategoryName(String name) throws DatabaseException;

	/**
	 * Search is case insensitive here
	 * @param category
	 * @return
	 * @throws DatabaseException
	 */
	public boolean isExistingCategory(String category) throws DatabaseException;
	
	// Insert
	/**
	 * 
	 * @param name
	 * @return the newly generated Id
	 * @throws DatabaseException
	 *             E.G. Thrown if one tries to insert an Category that already
	 *             exists
	 */
	public int insertCategory(String name) throws DatabaseException;

	// Update
	/**
	 * Used to update a categoryName
	 * @param categoryId
	 * @param newCategoryName
	 * @throws DatabaseException
	 */
	public void updateCategory(int categoryId, String newCategoryName) throws DatabaseException;

	// Delete

	/**
	 * Deletes category physically, only possible if no Book of this category is stored in the Database
	 * @param id
	 * @throws DatabaseException
	 */
	public void deleteCategory(int id) throws DatabaseException;

	// Author Methoden
	// Abfragen

	/**
	 * @param isbn
	 * @return
	 * @throws DatabaseException
	 */
	public List<Author> getAuthorByIsbn(String isbn) throws DatabaseException;

	/**
	 * case-sensitive
	 * @param NameF
	 * @param NameL
	 * @return
	 * @throws DatabaseException
	 */
	public List<Author> getAuthorByExactName(String NameF, String NameL) throws DatabaseException;

	/**
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	public Author getAuthorById(int id) throws DatabaseException;

	/**
	 * @return
	 * @throws DatabaseException
	 */
	public List<Author> getAllAuthors() throws DatabaseException;

	// Insert
	/**
	 * 
	 * @param nameF
	 * @param nameL
	 * @param newAuthor set to true, if author should be isnerted in any case, also if author with that name already exists
	 * @return newly generated Id oh author
	 * @throws AuthorMayExistException only thrown when newAuthor == false and an author with exact that name already exists
	 */
	public int insertAuthor(String nameF, String nameL, boolean newAuthor) throws AuthorMayExistException;

	// Update
	
	/**
	 * @param id
	 * @param newData {@link Map} with {@link Searchfields} as key to determine which field to update and {@link String} as value with the new data
	 * @throws DatabaseException
	 */
	public void updateAuthor(int id, Map<Searchfields, String> newData) throws DatabaseException;

	// Delete
	/**
	 * @param id
	 * @throws DatabaseException
	 */
	public void deleteAuthor(int id) throws DatabaseException;

	// Book Methoden

	// Abfragen
	/**
	 * @param mode
	 * @return
	 * @throws DatabaseException
	 */
	public List<Book> getAllBooks(SearchMode mode) throws DatabaseException;
	/**
	 * Used for API
	 * @param mode
	 * @param range
	 * @return
	 * @throws DatabaseException
	 */
	public List<Book> getAllBooks(SearchMode mode, int range) throws DatabaseException;

	/**
	 * Used for API
	 * @param category
	 * @param mode
	 * @param range
	 * @return
	 * @throws DatabaseException
	 */
	public List<Book> getBooksByCategory(String category, SearchMode mode, int range) throws DatabaseException;
	/**
	 * @param category
	 * @param mode
	 * @return
	 * @throws DatabaseException
	 */
	public List<Book> getBooksByCategory(String category, SearchMode mode) throws DatabaseException;

	/**
	 * @param isbn
	 * @param mode
	 * @return
	 * @throws DatabaseException
	 */
	public Book getBookByIsbn(String isbn, SearchMode mode) throws DatabaseException;

	/**
	 * Not implemented, project can be extended by that
	 * @param searchTerm
	 * @return
	 */
	public List<Book> getBooksByOpenSearch(String searchTerm);

	/**
	 * Returns {@link List} of {@link Book} fulfilling the searchterms
	 * @param map {@link Map} with {@link Searchfields} as key to define which field to be searched and {@link String} as value with the searchterms
	 * @param mode
	 * @return
	 * @throws DatabaseException
	 */
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
	public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds)
			throws DatabaseException;

	// Update
	/**
	 * 
	 * @param isbn
	 * @param data {@link Map} with {@link Searchfields} as key to determine the fields to be updated and {@link String} as value with the new data
	 * @param authorIds
	 * @param categoryIds
	 * @throws DatabaseException thrown e.g. if one tries to update isbn
	 */
	public void updateBook(String isbn, Map<Searchfields, String> data, Set<Integer> authorIds, Set<Integer> categoryIds) throws DatabaseException;
	
	/**
	 * used to delete a {@link Category} from a {@link Book}
	 * @param isbn
	 * @param categoryId
	 * @throws DatabaseException
	 */
	public void deleteCategoryOfBook(String isbn, int categoryId) throws DatabaseException;
	/**
	 * used to add a {@link Category} to a {@link Book}
	 * @param isbn
	 * @param categoryId
	 * @throws DatabaseException
	 */
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
	/**
	 * Book is not deleted physically from database, but stock is set to -1
	 * @param isbn
	 * @throws DatabaseException
	 */
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
