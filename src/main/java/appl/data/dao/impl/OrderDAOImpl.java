package appl.data.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import appl.data.dao.OrderDAO;
import appl.data.items.Order;

@Component
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void insertOrder(Order order) {
		// TODO implement this!
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrder() {
		// TODO Auto-generated method stub
	}

}
