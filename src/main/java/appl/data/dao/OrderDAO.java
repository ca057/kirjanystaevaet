package appl.data.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Book;
import appl.data.items.Orderx;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;

@Transactional
public interface OrderDAO {
	public int insertOrder(Orderx order) throws DatabaseException;

	public List<Orderx> getOrdersByUserId(int userId);
	
	public List<Orderx> getAllOrders();
	
	public Orderx getOrderByOrderId(int id) throws DatabaseException;
	
	public void updateOrder(Orderx order);
	
	public int createOrder(Map<String, Integer> isbnsNumberOf, int userId, Calendar cal) throws EntityDoesNotExistException, DatabaseException;
	
	public List<Book> getOrderedBook(int userId);


}
