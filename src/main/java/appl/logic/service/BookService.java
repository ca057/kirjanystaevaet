package appl.logic.service;

import java.util.List;
import java.util.Map;

import appl.data.enums.Searchfields;
import appl.data.items.Book;
import exceptions.data.AuthorMayExistException;

public interface BookService {

	public List<Book> getAllBooks();

	public List<Book> getBooksByCategory(String category);

	public Book getBookByIsbn(String isbn);

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);
	
	/**
	 * 
	 * @param map
	 * @param newAuthor boolean, wenn true wird auf jeden Fall ein neuer Autor erstellt, selbst wenn es schon einen Autoren mit exakt diesem Namen gibt.
	 * @throws AuthorMayExistException wenn newAuthor = false, aber schon mindestens ein Autor mit exakt diesem Namen existiert
	 */
	public void insertBook(Map<Searchfields, String> map, boolean newAuthor) throws AuthorMayExistException;

}
