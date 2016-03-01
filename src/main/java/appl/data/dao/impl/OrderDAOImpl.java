package appl.data.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.data.dao.OrderDAO;
import appl.data.items.Orderx;
import exceptions.data.DatabaseException;
import exceptions.data.ErrorMessageHelper;

@Component
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	private Criteria setupAndGetCriteria() {
		if (sessionFactory == null) {
			throw new RuntimeException("[Error] SessionFactory is null");
		}
		Session s = getSession();
		Criteria cr = s.createCriteria(Orderx.class);
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return cr;
		
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
		return setupAndGetCriteria().list();
	}

	@Override
	public Orderx getOrderByOrderId(int id) throws DatabaseException {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq("orderId", id));
		Object result = cr.uniqueResult();
		if ( result != null){
			Orderx order = (Orderx) result;
			return order;
		} else {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Order"));
		}
	
	}

}
