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
	public LinkedHashMap<String, Integer> getShelfWarmers(int range) throws DatabaseException {
		List<Book> shelfWarmers = orderDao.getBooksWithoutOrderItem();
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		// sortedMap zunächst mit allen Books auffüllen, die gar nicht bestellt wurden
		for (Book b : shelfWarmers){
			sortedMap.put(b.getIsbn(), 0);
			range--;
			if (range < 1){
				break;
			}
		
		}
		// Wenn vom range noch was übrig ist, mit der umgekehrten BestsellerListe auffüllen
		if (range > 0){
			List<OrderItem> orderItems = orderItemDao.getAllOrderItems();
			Map<String, Integer> tmpMap = new HashMap<String, Integer>();
			List<Map.Entry<String, Integer>> bestsellerList = new ArrayList<Map.Entry<String, Integer>>();


			for (OrderItem o : orderItems){
				if(tmpMap.get(o.getBook().getIsbn())!= null){
					tmpMap.put(o.getBook().getIsbn(), tmpMap.get(o.getBook().getIsbn()) + o.getNumberOf());
				} else {
					tmpMap.put(o.getBook().getIsbn(), o.getNumberOf());
				}
			}
			bestsellerList.addAll(tmpMap.entrySet());
			// Sortieren
			
			Collections.sort(bestsellerList, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> o1,
	                                           Map.Entry<String, Integer> o2) {
					return (o1.getValue()).compareTo(o2.getValue());
				}
			});
			for (Map.Entry<String, Integer> e : bestsellerList){
				sortedMap.put(e.getKey(), e.getValue());
				range--;
				if (range < 1){
					break;
				}
			}
		}
		return sortedMap;
	}
	@Override
	public LinkedHashMap<Book, Integer> getBestsellers(int range) throws DatabaseException {
		List<Map.Entry<Book, Integer>> bestsellerList = new ArrayList<Map.Entry<Book, Integer>>();
		Map<Book, Integer> tmpMap = new HashMap<Book, Integer>();
		try {
			List<OrderItem> orderItems = orderItemDao.getAllOrderItems();
			for (OrderItem o : orderItems){
				if(tmpMap.get(o.getBook().getIsbn())!= null){
					tmpMap.put(o.getBook(), tmpMap.get(o.getBook().getIsbn()) + o.getNumberOf());
				} else {
					tmpMap.put(o.getBook(), o.getNumberOf());
				}
			}
			bestsellerList.addAll(tmpMap.entrySet());
			// Sortieren
			
			Collections.sort(bestsellerList, new Comparator<Map.Entry<Book, Integer>>() {
				public int compare(Map.Entry<Book, Integer> o1,
	                                           Map.Entry<Book, Integer> o2) {
					return (o2.getValue()).compareTo(o1.getValue());
				}
			});
			LinkedHashMap<Book, Integer> sortedMap = new LinkedHashMap<Book, Integer>();
			int i = range;
			for (Map.Entry<Book, Integer> e : bestsellerList){
				sortedMap.put(e.getKey(), e.getValue());
				i--;
				if (i < 1){
					break;
				}
			}
//			for (Iterator<Map.Entry<String, Integer>> it = bestsellerList.iterator(); it.hasNext();) {
//				Map.Entry<String, Integer> entry = it.next();
//				sortedMap.put(entry.getKey(), entry.getValue());
//			}
			return sortedMap;
			
		} catch (HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}
//	@Override
//	public LinkedHashMap<String, Integer> getBestsellers(int range) throws DatabaseException {
//		List<Map.Entry<String, Integer>> bestsellerList = new ArrayList<Map.Entry<String, Integer>>();
//		Map<String, Integer> tmpMap = new HashMap<String, Integer>();
//		try {
//			List<OrderItem> orderItems = orderItemDao.getAllOrderItems();
//			for (OrderItem o : orderItems){
//				if(tmpMap.get(o.getBook().getIsbn())!= null){
//					tmpMap.put(o.getBook().getIsbn(), tmpMap.get(o.getBook().getIsbn()) + o.getNumberOf());
//				} else {
//					tmpMap.put(o.getBook().getIsbn(), o.getNumberOf());
//				}
//			}
//			bestsellerList.addAll(tmpMap.entrySet());
//			// Sortieren
//			
//			Collections.sort(bestsellerList, new Comparator<Map.Entry<String, Integer>>() {
//				public int compare(Map.Entry<String, Integer> o1,
//	                                           Map.Entry<String, Integer> o2) {
//					return (o2.getValue()).compareTo(o1.getValue());
//				}
//			});
//			LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
//			int i = range;
//			for (Map.Entry<String, Integer> e : bestsellerList){
//				sortedMap.put(e.getKey(), e.getValue());
//				i--;
//				if (i < 1){
//					break;
//				}
//			}
////			for (Iterator<Map.Entry<String, Integer>> it = bestsellerList.iterator(); it.hasNext();) {
////				Map.Entry<String, Integer> entry = it.next();
////				sortedMap.put(entry.getKey(), entry.getValue());
////			}
//			return sortedMap;
//			
//		} catch (HibernateException e){
//			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
//		}
//	}
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
	@Override
	public double getPriceOfOrder(int orderId) throws DatabaseException {
		Orderx order = orderDao.getOrderByOrderId(orderId);
		Set<OrderItem> orderItems = order.getOrderItems();
		double price = 0.0;
		for (OrderItem o : orderItems){
			System.out.println(o.toString());
			price = price + (o.getPrice()) * o.getNumberOf();
		}
	    price = Math.round(price * 100) / 100.0;

		return price;
	}
	
	
	

}
