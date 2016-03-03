package appl.data.dao.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.data.dao.BookDAO;
import appl.data.dao.OrderDAO;
import appl.data.dao.OrderItemDAO;
import appl.data.items.Book;
import appl.data.items.OrderItem;
import appl.data.items.Orderx;
import appl.data.items.User;
import appl.logic.service.BookService;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;
import exceptions.data.ErrorMessageHelper;

@Component
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	OrderItemDAO orderItemDao;
	@Autowired
	BookDAO bookDao;
	@Autowired
	BookService dataService;
	@Autowired
	UserService userService;
	
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

	@Override
	public void updateOrder(Orderx order) {
		getSession().update(order);

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

	@Override
	public int createOrder(Map<String, Integer> isbnsNumberOf, int userId, Calendar cal) throws EntityDoesNotExistException, DatabaseException {
		Orderx order = new Orderx(cal);
		try {
			int orderId = insertOrder(order);
			for (String isbn : isbnsNumberOf.keySet()){
			
				Book book = bookDao.getBookByIsbn(isbn);
				OrderItem orderItem = new OrderItem(book, book.getPrice(), isbnsNumberOf.get(isbn), order);
					
				// OrderItem im Buch setzen
				book.getOrderItems().add(orderItem);
				// OrderItem in der Order setzen
				order.getOrderItems().add(orderItem);
				
				// OrderItems speichern
				orderItemDao.insert(orderItem);
				
				// Stock in Books updaten
				
				bookDao.decrementStock(isbn, isbnsNumberOf.get(isbn));
				
			}
			
			// Verbindung zwischen User und Order herstellen
			User user = userService.findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.entityDoesNotExist("User")));
			user.getOrders().add(order);
			order.setUser(user);
			updateOrder(order);
			
			return orderId;

		} catch (HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	

}
