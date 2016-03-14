package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.OrderItem;

/**
 * @author Madeleine
 *
 */
@Transactional
public interface OrderItemDAO {

	/**
	 * @return
	 */
	public List<OrderItem> getAllOrderItems();

	/**
	 * @param orderItem
	 */
	public void update(OrderItem orderItem);

	/**
	 * @param orderItem
	 * @return
	 */
	public int insert(OrderItem orderItem);

}
