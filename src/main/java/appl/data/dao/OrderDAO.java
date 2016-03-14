package appl.data.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Book;
import appl.data.items.Orderx;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;

/**
 * @author Madeleine
 *
 */
@Transactional
public interface OrderDAO {
	/**
	 * Physically saves order in database
	 * 
	 * @param order
	 * @return
	 * @throws DatabaseException
	 */
	public int insertOrder(Orderx order) throws DatabaseException;

	/**
	 * 
	 * @param userId
	 * @return {@link List} of all orders of a user
	 */
	public List<Orderx> getOrdersByUserId(int userId);

	/**
	 * 
	 * @return
	 */
	public List<Orderx> getAllOrders();

	/**
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	public Orderx getOrderByOrderId(int id) throws DatabaseException;

	/**
	 * @param order
	 *            contains data to be stored
	 */
	public void updateOrder(Orderx order);

	/**
	 * Creates an Order, also takes care of stock and does all the linking
	 * between the Entities
	 * 
	 * @param isbnsNumberOf
	 *            {@link Map} that holds isbns of books to be ordered and their
	 *            number as value
	 * @param userId
	 * @param cal
	 * @return
	 * @throws EntityDoesNotExistException
	 * @throws DatabaseException
	 */
	public int createOrder(Map<String, Integer> isbnsNumberOf, int userId, Calendar cal)
			throws EntityDoesNotExistException, DatabaseException;

	/**
	 * @return {@link List} of {@link Book} that have not been ordered yet
	 */
	public List<Book> getBooksWithoutOrderItem();

}
