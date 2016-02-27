package appl.logic.service.impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.dao.BookDAO;
import appl.data.dao.OrderDAO;
import appl.data.items.ArchiveBook;
import appl.data.items.Book;
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
	OrderDAO orderDao;
	@Autowired
	BookDAO bookDao;
	@Autowired
	UserService userService;
	public OrderServiceImpl() {
		
		
	}
	@Override
	public int createOrder(Set<String> isbns, int userId, Calendar cal) throws DatabaseException {
		// Aller Bücher herholen
		Set<Book> books = new HashSet<Book>();
		
		for (String isbn : isbns){
			books.add(dataService.getBookByIsbn(isbn));			
		}
			
		// Das ArchivSet, dass in der Order gespeichert wird
		Set<ArchiveBook> archiveItemsOfOrder = new HashSet<ArchiveBook>();
		// Archiv erstellen
		for (Book b : books){
			// ArchiveItems herholen
			Set<ArchiveBook> archiveItemsOfThisBook = b.getArchiveItems();
			// Preis überprüfen
			for (ArchiveBook a : archiveItemsOfThisBook){
				if(b.getPrice() == a.getPrice()){
					b.getArchiveItems().add(a);
					try {
						bookDao.updateBook(b);
						archiveItemsOfOrder.add(a); // Auch dem Archive Set für die aktuelle Order hinzufügen
					} catch (HibernateException e){
						throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
					}
					
				} else {
					// TODO neues ArchiveItem erstellen
					ArchiveBook newArchiveItem = new ArchiveBook(b, b.getPrice());
					// TODO Testen, ob hier ArchiveItem schon persistiert werden muss
					archiveItemsOfOrder.add(newArchiveItem);
				}
			}
			
		}
		// Order anlegen und speichern
		User user = userService.findByID(userId).get();
		Orderx order = new Orderx(archiveItemsOfOrder, user, cal);
		try{ 
			int orderId = orderDao.insertOrder(order);
			return orderId;
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
		User user = userService.findByID(userId).get();
		try{ 
		Set<Orderx> orders = user.getOrders();
		return orders;
		} catch (HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getLocalizedMessage()));
		}
	}
	

}
