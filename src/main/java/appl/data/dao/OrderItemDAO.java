package appl.data.dao;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.OrderItem;

@Transactional
public interface OrderItemDAO {
	
	public void update(OrderItem archiveItem);

	int insert(OrderItem archiveItem);

}
