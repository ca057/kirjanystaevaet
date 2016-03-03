package appl.logic.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.dao.BookDAO;
import appl.data.dao.OrderDAO;
import appl.data.dao.OrderItemDAO;
import appl.data.items.OrderItem;
import appl.data.items.Orderx;
import appl.data.items.User;
import appl.logic.service.BookService;
import appl.logic.service.OrderService;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;
import exceptions.data.ErrorMessageHelper;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	BookService dataService;
	@Autowired
	UserService userService;
	@Autowired
	OrderItemDAO orderItemDao;
	@Autowired 
	OrderDAO orderDao;
	@Autowired
	BookDAO bookDao;
	
	
	public OrderServiceImpl() {
		
		
	}
	@Override
	public int createOrder(Map<String, Integer> isbnsNumberOf, int userId, Calendar cal) throws DatabaseException {
		try{
			int orderId = orderDao.createOrder(isbnsNumberOf, userId, cal);
			return orderId;
		} catch(EntityDoesNotExistException e){
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Book"));
		} catch (HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
		
//		Orderx order = new Orderx(cal);
//		try {
//			int orderId = orderDao.insertOrder(order);
//			for (String isbn : isbnsNumberOf.keySet()){
//			
//				Book book = bookDao.getBookByIsbn(isbn);
//				OrderItem orderItem = new OrderItem(book, book.getPrice(), isbnsNumberOf.get(isbn), order);
//					
//				// OrderItem im Buch setzen
//				book.getOrderItems().add(orderItem);
//				// OrderItem in der Order setzen
//				order.getOrderItems().add(orderItem);
//				
//				// OrderItems speichern
//				orderItemDao.insert(orderItem);
//				
//				
//			}
//			
//			// Verbindung zwischen User und Order herstellen
//			User user = userService.findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.entityDoesNotExist("User")));
//			user.getOrders().add(order);
//			order.setUser(user);
//			orderDao.updateOrder(order);
//			
//			return orderId;
//
//		} catch (HibernateException e){
//			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
//		}
//		
	}
	@Override
	public List<Orderx> getAllOrders() throws DatabaseException {
		try {
			List<Orderx> orders = orderDao.getAllOrders();
			return orders;
		} catch(HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}
	@Override
	public Set<Orderx> getOrdersByUserid(int userId) throws DatabaseException {
		User user = userService.findByID(userId).get();
		try{ 
		Set<Orderx> orders = user.getOrders();
		return orders;
		} catch (HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getLocalizedMessage()));
		}
	}
	@Override
	public List<OrderItem> getAllOrderItems() throws DatabaseException {
		try{
			return orderItemDao.getAllOrderItems();
		}
		catch(HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}
	

}
