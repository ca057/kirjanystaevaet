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
	
	public void updateOrder(Orderx order);


}
