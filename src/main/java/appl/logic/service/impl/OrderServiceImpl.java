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
import appl.data.items.Book;
import appl.data.items.OrderItem;
import appl.data.items.Orderx;
import appl.data.items.User;
import appl.logic.service.BookService;
import appl.logic.service.OrderService;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
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
		
		Orderx order = new Orderx(cal);
		try {
			int orderId = orderDao.insertOrder(order);
			for (String isbn : isbnsNumberOf.keySet()){
			
				Book book = bookDao.getBookByIsbn(isbn);
				OrderItem orderItem = new OrderItem(book, book.getPrice(), isbnsNumberOf.get(isbn), order);
					
				// OrderItem im Buch setzen
				book.getOrderItems().add(orderItem);
				// OrderItem in der Order setzen
				order.getOrderItems().add(orderItem);
				
				// OrderItems speichern
				orderItemDao.insert(orderItem);
				
				
			}
			
			// Verbindung zwischen User und Order herstellen
			User user = userService.findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.entityDoesNotExist("User")));
			user.getOrders().add(order);
			order.setUser(user);
			orderDao.updateOrder(order);
			
			return orderId;

		} catch (HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
//		for (String isbn : isbnsNumberOf.keySet()){
//			try{
//				Book book = bookDao.getBookByIsbn(isbn);
//				OrderItem orderItem = new OrderItem(book, book.getPrice(), isbnsNumberOf.get(isbn), order);
//				
//				// OrderItem im Buch setzen
//				book.getOrderItems().add(orderItem);
//				// OrderItem in der Order setzen
//				order.getOrderItems().add(orderItem);
//				
//				int orderId = orderDao.insertOrder(order);
//				
//			} catch(HibernateException e){
//				throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
//			}
//			
//		}
//		// Order speichern
//		try {
//			int orderId = orderDao.insertOrder(order);
//			
//			// Der Order den User hinzufügen, order updaten
//			
//			User user = userService.findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.entityDoesNotExist("User")));
//			Orderx savedOrder = orderDao.getOrderByOrderId(orderId);
//			// Dem User die neue Order hinzufügen
//			user.getOrders().add(savedOrder);
//			// Der Order den User hinzufügen
//			savedOrder.setUser(user);
//			// Die Ordernochmals updaten
//			
//		}catch(HibernateException e){
//			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
//		}
//		// order mit 
//		
//		
//		
//		//return orderId;
//		return 0;
//		// Das ArchivSet, dass in der Order gespeichert wird
//		Set<OrderItem> orderItems = new HashSet<OrderItem>();
//		
//		for (String isbn : isbnsNumberOf.keySet()){
//			// One-To-Many Relationship: Einfach ein neues orderItem erstellen
//			Book book = bookDao.getBookByIsbn(isbn);
//			OrderItem orderItem = new OrderItem(book, book.getPrice(), isbnsNumberOf.get(isbn) );
//			// Dem Book hinzufügen
//			book.getOrderItems().add(orderItem);
//			// neues OrderItem persitieren
//				try{ 
//					int orderItemId = orderItemDao.insert(orderItem);
//				}catch(HibernateException e){
//					e.printStackTrace();
//					throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
//				}
//				// 
//				orderItems.add(orderItem);
//			}
//
//			
//		
//		// Order anlegen und speichern, mit User verknüpfen
//		User user = userService.findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.entityDoesNotExist("User")));
//		Orderx order = new Orderx(orderItems, user, cal);
//
////		Orderx order = new Orderx(orderItems, user, cal);
////		//Orderx order = new Orderx(archiveItemsOfOrder, cal);
////		//user.addOrder(order);
//		try{ 
//			int orderId = orderDao.insertOrder(order);
//			return orderId;
//		} catch (HibernateException e){
//			e.printStackTrace();
//				throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
//		}
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
