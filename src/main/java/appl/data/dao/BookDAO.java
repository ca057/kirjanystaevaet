package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.items.Book;
import exceptions.data.IsbnAlreadyExistsException;

@Transactional
public interface BookDAO {

	public List<Book> getBooksByOpenSearch(String searchTerm);

	public List<Book> getBooksByMetadata(Map<Searchfields, String> map);

	//public String insertBook(Book book)throws IsbnAlreadyExistsException;
	public String insertBook(Book book);


	public void deleteBook(String isbn);

	public void updateBook(Book book, Map<Searchfields, String> map);

	public List<Book> getAllBooks();

}
