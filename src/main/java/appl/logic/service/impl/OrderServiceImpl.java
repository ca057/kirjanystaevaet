package appl.logic.service.impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.dao.ArchiveDAO;
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
	public int createOrder(Set<String> isbns, int userId, Calendar cal) throws DatabaseException {
		// Aller Bücher herholen
		Set<Book> books = new HashSet<Book>();
		
		for (String isbn : isbns){
			System.out.println("Bestellte Bücher: " + isbn);
			books.add(dataService.getBookByIsbn(isbn));			
		}
			
		// Das ArchivSet, dass in der Order gespeichert wird
		Set<ArchiveBook> archiveItemsOfOrder = new HashSet<ArchiveBook>();
		// Archiv erstellen
		for (Book b : books){
			// ArchiveItems herholen
			Set<ArchiveBook> archiveItemsOfThisBook = b.getArchiveItems();
			// Falls noch keines existiert, muss auf jeden Fall ein neues erstellt werden
			if(archiveItemsOfThisBook.size() == 0){
				// TODO Codeduplikat vermeiden
				System.out.println("\n Book has no ArchiveItems yet\n");
				//neues ArchiveItem erstellen
				ArchiveBook newArchiveItem = new ArchiveBook(b, b.getPrice());
				// TODO Testen, ob hier ArchiveItem schon persistiert werden muss! JA MUSS!
				// ArchiveItem persitieren
				try{ 
					int archiveItemId = archiveDao.insert(newArchiveItem);
				}catch(HibernateException e){
					throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
				}
				archiveItemsOfOrder.add(newArchiveItem);
			}
			System.out.println("\nGot archive Items for book with title " + b.getTitle() + " its size is " + archiveItemsOfThisBook.size() + "\n\n");
			// Preis überprüfen
			for (ArchiveBook a : archiveItemsOfThisBook){
				if(b.getPrice() == a.getPrice()){
					System.out.println("\nItem has the same price\n");
					b.getArchiveItems().add(a);
					try {
						bookDao.updateBook(b);
						System.out.println("\n updated Book\n");
						archiveItemsOfOrder.add(a); // Auch dem Archive Set für die aktuelle Order hinzufügen
					} catch (HibernateException e){
						throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
					}
					
				} else {
					System.out.println("\n need a new Archiveitem \n");
					//neues ArchiveItem erstellen
					ArchiveBook newArchiveItem = new ArchiveBook(b, b.getPrice());
					// TODO Testen, ob hier ArchiveItem schon persistiert werden muss
					archiveItemsOfOrder.add(newArchiveItem);
				}
			}
			
		}
		// Order anlegen und speichern, mit User verknüpfen
		User user = userService.findByID(userId).get();
		//Orderx order = new Orderx(archiveItemsOfOrder, user, cal);
		Orderx order = new Orderx(archiveItemsOfOrder, cal);
		user.addOrder(order);
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
