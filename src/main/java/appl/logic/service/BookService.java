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
import exceptions.data.EntityDoesNotExistException;
import exceptions.data.IsbnAlreadyExistsException;
import exceptions.data.PrimaryKeyViolationException;

public interface BookService {
	//Category Methoden
	
	// Abfragen
	
	public Category getCategoryByExactName (String name) throws EntityDoesNotExistException;
	
	public Category getCategoryById(int id) throws EntityDoesNotExistException;
	
	public List<String> getAllCategoryNames();
	
	public List<Category> getAllCategories();

	// Insert
	public void insertCategory(String name);
	
	// Update
	
	
	
	// Delete
	
	
	
	
	// Author Methoden
	//Abfragen

	/**
	 * 
	 * @param nameF
	 * @param nameL
	 * @param newAuthor Wird auf TRUE gesetzt, wenn man auf jeden Fall einen neuen Autoren in die Datenbank einfügen will, auch wenn es schon einen mit diesem Namen gibt. In diesem Fall kann die Exception ignoriert werden, weil sie nie geworfen werden wird
	 * @return Die ID des neu erstellten Eintrags
	 * @throws AuthorMayExistException Wenn newAuthor = false und wenn es schon mindestens einen Autoren mit Exakt dem angegeben Vor- und Nachnamen gibt
	 */
	
	public List<Author> getAuthorByExactName(String NameF, String NameL);
	
	public Author getAuthorById (int id) throws EntityDoesNotExistException;
	
	public List<Author> getAllAuthors();
	
	// Insert
	
	public int insertAuthor(String nameF, String nameL, boolean newAuthor) throws AuthorMayExistException;

	// Update
	
	// Delete
	
	// Book Methoden
	
	
	// Abfragen
	public List<Book> getAllBooks();

	public List<Book> getBooksByCategory(String category);

	public Book getBookByIsbn(String isbn);

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);
	


	// Insert
	/**
	 * 
	 * @param map
	 * @param authorIds
	 * @param categoryNames Es dürfen nur Kategories verwendet werden, die es schon gibt. Will man eine Category angeben, die es noch nicht gibt, muss diese Vorher extra angelegt werden
	 * @throws PrimaryKeyViolationException 
	 * @throws EntityDoesNotExistException Wird geworfen, wenn AuthorIds und CategoryIds übrgeben werden, die nicht existieren
	 * @throws DatabaseException Bei allgemeinen Datenbankfehlern
	 */
	//public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds)throws IsbnAlreadyExistsException ;
	public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds) throws PrimaryKeyViolationException, EntityDoesNotExistException, DatabaseException;
	// Update
	// Delete
	
}
