package appl.logic.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import appl.data.items.Orderx;
import exceptions.data.DatabaseException;

public interface OrderService {
	
	/**
	 * 
	 * @param isbns
	 * @param userId
	 * @param cal
	 * @return The generated orderId
	 * @throws DatabaseException
	 */

	public int createOrder(Map<String, Integer> isbnsNumberOf, int userId, Calendar cal) throws DatabaseException;
	
	public List<Orderx> getAllOrders() throws DatabaseException;
	public Set<Orderx> getOrdersByUserid(int userId) throws DatabaseException;
}
