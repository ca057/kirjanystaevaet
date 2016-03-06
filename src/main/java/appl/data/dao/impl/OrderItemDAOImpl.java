package appl.data.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return cr;
		//return cr.createAlias("books", "b").createAlias("authors", "a");
		//return cr.createAlias("books", "b"); // Category hat keinen Author -> kein Alias dafür angeben
	}
	@Override
	public void update(OrderItem archiveItem) {
		getSession().update(archiveItem);
		
	}
	@Override
	public int insert(OrderItem archiveItem){
		int id = (int) getSession().save(archiveItem);
		return id;
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		return setupAndGetCriteria().list();		
	}

}
