package appl.logic.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import appl.data.enums.Searchfields;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import exceptions.data.AuthorMayExistException;

public interface BookService {
	//Category Methoden
	
	public Category getCategoryByExactName (String name);
	
	public void insertCategory(String name);
	
	public List<String> getAllCategoryNames();
	
	
	
	// Author Methoden

	/**
	 * 
	 * @param nameF
	 * @param nameL
	 * @param newAuthor Wird auf TRUE gesetzt, wenn man auf jeden Fall einen neuen Autoren in die Datenbank einfügen will, auch wenn es schon einen mit diesem Namen gibt. In diesem Fall kann die Exception ignoriert werden, weil sie nie geworfen werden wird
	 * @return Die ID des neu erstellten Eintrags
	 * @throws AuthorMayExistException Wenn es schon mindestens einen Autoren mit Exakt dem angegeben Vor- und Nachnamen gibt
	 */
	public int insertAuthor(String nameF, String nameL, boolean newAuthor) throws AuthorMayExistException;
	
	public List<Author> getAuthorByExactName(String NameF, String NameL);
	
	public Author getAuthorById (int id);
	
	public List<Author> getAllAuthors();
	
	// Book Methoden
	
	public List<Book> getAllBooks();

	public List<Book> getBooksByCategory(String category);

	public Book getBookByIsbn(String isbn);

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);
	


	/**
	 * 
	 * @param map
	 * @param authorIds
	 * @param categoryNames Es dürfen nur Kategories verwendet werden, die es schon gibt. Will man eine Category angeben, die es noch nicht gibt, muss diese Vorher extra angelegt werden
	 */
	public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds) ;

}
