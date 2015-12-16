package appl.data.dao.impl;
import java.util.List;

import org.hibernate.HibernateException;
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
	public boolean insertOrder(Order order) {
		try {
			return sessionFactory.getCurrentSession().save(order) != null;
		} catch (HibernateException e) {
			return false;
		}
		
		
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateOrder() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
