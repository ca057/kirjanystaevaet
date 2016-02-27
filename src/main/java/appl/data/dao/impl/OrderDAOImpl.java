package appl.data.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.data.dao.OrderDAO;
import appl.data.items.Orderx;
import exceptions.data.DatabaseException;

@Component
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int insertOrder(Orderx order) throws DatabaseException {
		int id = (int) getSession().save(order);
		return id;
			
		
		
	}

	@Override
	public List<Orderx> getOrdersByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public void updateOrder() {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Orderx> getAllOrders() {
		return getSession().createCriteria(Orderx.class).list();
	}

}
