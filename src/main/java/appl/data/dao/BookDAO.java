package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.items.Book;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;

@Transactional
public interface BookDAO {

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);
	
	public Book getBookByIsbn(String isbn) throws EntityDoesNotExistException;

	//public String insertBook(Book book)throws IsbnAlreadyExistsException;
	public String insertBook(Book book) throws DatabaseException;


	public void deleteBook(String isbn);

	public void updateBook(Book book);

	public List<Book> getAllBooks();
	
	public void decrementStock(String isbn, int decrement) throws DatabaseException;
	public void setStockToNegative(String isbn);

}
