package appl.logic.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public List<Orderx> getAllOrders() throws DatabaseException;
	public List<OrderItem> getAllOrderItems() throws DatabaseException;
	public Set<Orderx> getOrdersByUserid(int userId) throws DatabaseException;
}
