package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.OrderItem;

@Transactional
public interface OrderItemDAO {
	
	public List<OrderItem> getAllOrderItems();
	
	public void update(OrderItem archiveItem);

	public int insert(OrderItem archiveItem);

}
