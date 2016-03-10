package appl.logic.service;
/**
 * @author Madeleine
 */
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import appl.data.items.Book;
import appl.data.items.OrderItem;
import appl.data.items.Orderx;
import exceptions.data.DatabaseException;

public interface OrderService {
	

	/**
	 * 
	 * @param isbnsNumberOf isbn as key, the number of odered books as value
	 * @param userId
	 * @param cal the date
	 * @return
	 * @throws DatabaseException
	 */

	public int createOrder(Map<String, Integer> isbnsNumberOf, int userId, Calendar cal) throws DatabaseException;
	
	/**
	 * returns {@link List} with all {@link Orderx}s
	 * @return
	 * @throws DatabaseException
	 */
	public List<Orderx> getAllOrders() throws DatabaseException;
	/**
	 * returns {@link List} with all {@link OrderItem} Might be used for statistics
	 * @return
	 * @throws DatabaseException
	 */
	public List<OrderItem> getAllOrderItems() throws DatabaseException;
	/**
	 * @param userId
	 * @return {@link Map} with all {@Orderx}s of a {@link User}
	 * @throws DatabaseException
	 */
	public Set<Orderx> getOrdersByUserid(int userId) throws DatabaseException;
	/**
	 * Bestsellers determined by number of orders
	 * @param range to determine how many {@link Book} should be returned
	 * @return {@link LinkedHashMap} with {@link Book} as key and {@link int} number of orders as value. Datatype guarantuees sorted order of {@link Book}s
	 * @throws DatabaseException
	 */
	public LinkedHashMap<Book, Integer> getBestsellers(int range) throws DatabaseException;
	/**
	 * 
	 * @param range
	 * @return 	 * @return {@link LinkedHashMap} with {@link Book} as key and {@link int} number of orders as value. Datatype guarantuees sorted order of {@link Book}s
	 * @throws DatabaseException
	 */
	public LinkedHashMap<Book, Integer> getShelfWarmers(int range) throws DatabaseException;
	/**
	 * Calculates the overall price of an order
	 * @param orderId
	 * @return
	 * @throws DatabaseException
	 */
	public double getPriceOfOrder(int orderId) throws DatabaseException;
}
