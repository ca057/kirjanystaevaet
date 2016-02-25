package appl.logic.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import appl.data.enums.Searchfields;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import exceptions.data.AuthorMayExistException;
import exceptions.data.DatabaseException;

public interface BookService {
	//Category Methoden
	
	// Abfragen
	
	public Category getCategoryByExactName (String name) throws DatabaseException;
	
	public Category getCategoryById(int id) throws DatabaseException;
	
	
	// Im Controller noch nicht behandelt
	//public List<String> getAllCategoryNames() throws DatabaseException;
	public List<String> getAllCategoryNames();
	
	public List<Category> getAllCategories() throws DatabaseException;

	// Insert
	/**
	 * 
	 * @param name
	 * @return Die neu erzeugte ID
	 * @throws DatabaseException E.G. Thrown if one tries to insert an Category that already exists 
	 */
	public int insertCategory(String name) throws DatabaseException;
	
	// Update
	
	
	
	// Delete
	
	public void deleteCategory(String name) throws DatabaseException;
	
	
	// Author Methoden
	//Abfragen


	
	public List<Author> getAuthorByExactName(String NameF, String NameL) throws DatabaseException;
	
	public Author getAuthorById (int id) throws DatabaseException;
	
	public List<Author> getAllAuthors() throws DatabaseException;
	
	// Insert
	/**
	 * 
	 * @param nameF
	 * @param nameL
	 * @param newAuthor Wird auf TRUE gesetzt, wenn man auf jeden Fall einen neuen Autoren in die Datenbank einfügen will, auch wenn es schon einen mit diesem Namen gibt. In diesem Fall kann die Exception ignoriert werden, weil sie nie geworfen werden wird
	 * @return Die ID des neu erstellten Eintrags
	 * @throws AuthorMayExistException Wenn newAuthor = false und wenn es schon mindestens einen Autoren mit Exakt dem angegeben Vor- und Nachnamen gibt
	 */
	public int insertAuthor(String nameF, String nameL, boolean newAuthor) throws AuthorMayExistException;

	// Update
	
	// Delete
	//TODO Was soll hier angegeben werden?
	/**
	 * 
	 * @param id
	 * @throws DatabaseException If Author does not exist, or if a general DB-Error Happens
	 */
	void deleteAuthor(int id) throws DatabaseException;

	// Book Methoden
	
	
	// Abfragen
	public List<Book> getAllBooks() throws DatabaseException;

	public List<Book> getBooksByCategory(String category) throws DatabaseException;

	public Book getBookByIsbn(String isbn) throws DatabaseException;

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);
	


	// Insert
	/**
	 * 
	 * @param map contains information about simple fields
	 * @param authorIds may only contain ids of existing authors
	 * @param categoryIds may only contain ids of existing categories
	 * @throws DatabaseException thrown when categories or authors do not exist in the Database, also thrown in case of general Database Errors. Errormessage gives more Details.
	 */
	//public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds)throws IsbnAlreadyExistsException ;
	public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds) throws DatabaseException;
	// Update
	// Delete
	public void deleteBook(String isbn) throws DatabaseException;

	
}
