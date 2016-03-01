package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Orderx;
import exceptions.data.DatabaseException;

@Transactional
public interface OrderDAO {
	public int insertOrder(Orderx order) throws DatabaseException;

	public List<Orderx> getOrdersByUserId(int userId);
	
	public List<Orderx> getAllOrders();
	
	public Orderx getOrderByOrderId(int id) throws DatabaseException;

	//public void updateOrder(); // TODO Wie sieht Signatur genau aus?

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