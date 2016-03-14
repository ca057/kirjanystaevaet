package appl.data.dao.impl;

/**
 * @author Madeleine
 */
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.OrderItemDAO;
import appl.data.items.OrderItem;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO {

	public OrderItemDAOImpl() {
		// TODO Auto-generated constructor stub
	}

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
		Criteria cr = s.createCriteria(OrderItem.class);
		cr.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return cr;
	}

	@Override
	public void update(OrderItem orderItem) {
		getSession().update(orderItem);

	}

	@Override
	public int insert(OrderItem orderItem) {
		int id = (int) getSession().save(orderItem);
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItem> getAllOrderItems() {
		return setupAndGetCriteria().list();
	}

}
