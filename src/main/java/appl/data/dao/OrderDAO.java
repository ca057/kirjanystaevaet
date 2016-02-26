package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.OrderX;
import exceptions.data.DatabaseException;

@Transactional
public interface OrderDAO {
	public int insertOrder(OrderX order) throws DatabaseException;

	public List<OrderX> getOrdersByUserId(int userId);

	public void updateOrder(); // TODO Wie sieht Signatur genau aus?

}
/*
 * public List<Book> getBooksByCategory(String categoryName);
 * 
 * public Book getBookByIsbn(int isbn);
 * 
 * public List<Book> getBooksByOpenSearch(String searchTerm);
 * 
 * public List<Book> getBooksByMetadata(Map<Searchfields, String> map);
 * 
 * public boolean insertBook(Book book);
 * 
 * public boolean deleteBook(Book book);
 * 
 * public boolean updateBook(Book book, Map<Searchfields, String> map);
 * 
 * public Book executeQuery(String query);
 */