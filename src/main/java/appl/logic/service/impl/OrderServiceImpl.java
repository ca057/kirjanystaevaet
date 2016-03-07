package appl.logic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
		User user = userService.findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.entityDoesNotExist("User")));
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
	@Override
	public List<Book> getOrderedBooksOfUser(int userId) throws DatabaseException {
		try{
			List<Book> books = orderDao.getOrderedBook(userId);
			return books;
		} catch(HibernateException e){
			e.printStackTrace();
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
//		Set<Orderx> orders = getOrdersByUserid(userId);
//		Set<Book> books = new HashSet<Book>();
//		for(Orderx o : orders){
//			Set<OrderItem> orderItems = o.getOrderItems();
//			for(OrderItem i : orderItems){
//				books.add(i.getBook());
//			}
//		}
		//return books;
	}
	
	@Override
	public LinkedHashMap<String, Integer> getBestsellers(int range) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Map.Entry<String, Integer>> getBestsellers() throws DatabaseException {
		List<Map.Entry<String, Integer>> bestsellers = new ArrayList<Map.Entry<String, Integer>>();
		Map<String, Integer> tmpMap = new HashMap<String, Integer>();
		try {
			List<OrderItem> orderItems = orderItemDao.getAllOrderItems();
			for (OrderItem o : orderItems){
				if(tmpMap.get(o.getBook().getIsbn())!= null){
					tmpMap.put(o.getBook().getIsbn(), tmpMap.get(o.getBook().getIsbn()) + o.getNumberOf());
				} else {
					tmpMap.put(o.getBook().getIsbn(), o.getNumberOf());
				}
			}
			bestsellers.addAll(tmpMap.entrySet());
			// Sortieren
			
			Collections.sort(bestsellers, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> o1,
	                                           Map.Entry<String, Integer> o2) {
					return (o2.getValue()).compareTo(o1.getValue());
				}
			});
			
			return bestsellers;
		} catch (HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}
	
	

}
