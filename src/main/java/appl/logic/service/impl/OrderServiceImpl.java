package appl.logic.service.impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.dao.ArchiveDAO;
import appl.data.dao.BookDAO;
import appl.data.dao.OrderDAO;
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
	ArchiveDAO archiveDao;
	@Autowired 
	OrderDAO orderDao;
	@Autowired
	BookDAO bookDao;
	
	
	public OrderServiceImpl() {
		
		
	}
	@Override
	public int createOrder(Map<String, Integer> isbnsNumberOf, int userId, Calendar cal) throws DatabaseException {
		
		// Das ArchivSet, dass in der Order gespeichert wird
		Set<OrderItem> orderItems = new HashSet<OrderItem>();
		
		for (String isbn : isbnsNumberOf.keySet()){
			// One-To-Many Relationship: Einfach ein neues orderItem erstellen
			Book book = bookDao.getBookByIsbn(isbn);
			OrderItem orderItem = new OrderItem(book, book.getPrice(), isbnsNumberOf.get(isbn) );
			// Dem Book hinzufügen
			book.getOrderItems().add(orderItem);
			// neues OrderItem persitieren
				try{ 
					int orderItemId = archiveDao.insert(orderItem);
				}catch(HibernateException e){
					e.printStackTrace();
					throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
				}
				// 
				orderItems.add(orderItem);
			}
	
//			// Preis überprüfen
//			for (OrderItem a : archiveItemsOfThisBook){
//				if(b.getPrice() == a.getPrice()){
//					System.out.println("\nItem has the same price\n");
//					b.getArchiveItems().add(a);
//					try {
//						bookDao.updateBook(b);
//						System.out.println("\n updated Book\n");
//						orderItems.add(a); // Auch dem Archive Set für die aktuelle Order hinzufügen
//					} catch (HibernateException e){
//						throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
//					}
//					
//				} else {
//					System.out.println("\n need a new Archiveitem \n");
//					//neues ArchiveItem erstellen
//					OrderItem newArchiveItem = new OrderItem(b, b.getPrice());
//					// TODO Testen, ob hier ArchiveItem schon persistiert werden muss
//					orderItems.add(newArchiveItem);
//				}
//			}
			
		
		// Order anlegen und speichern, mit User verknüpfen
		User user = userService.findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.entityDoesNotExist("User")));
		Orderx order = new Orderx(orderItems, user, cal);

//		Orderx order = new Orderx(orderItems, user, cal);
//		//Orderx order = new Orderx(archiveItemsOfOrder, cal);
//		//user.addOrder(order);
		try{ 
			int orderId = orderDao.insertOrder(order);
			return orderId;
		} catch (HibernateException e){
			e.printStackTrace();
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
		User user = userService.findByID(userId).get();
		try{ 
		Set<Orderx> orders = user.getOrders();
		return orders;
		} catch (HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getLocalizedMessage()));
		}
	}
	

}
