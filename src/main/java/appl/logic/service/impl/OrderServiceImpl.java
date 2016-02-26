package appl.logic.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.dao.OrderDAO;
import appl.data.items.ArchiveBook;
import appl.data.items.Book;
import appl.logic.service.BookService;
import appl.logic.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	BookService dataService;
	@Autowired 
	OrderDAO orderDao;
	public OrderServiceImpl() {
		
		
	}
	@Override
	public int createOrder(Set<String> isbns, int userId, int year, int month, int day) {
		// Aller Bücher herholen
		Set<Book> books = new HashSet<Book>();
		
		for (String isbn : isbns){
			books.add(dataService.getBookByIsbn(isbn));			
		}
				
		// Archiv erstellen
		for (Book b : books){
			// ArchiveItems herholen
			Set<ArchiveBook> archiveItems = b.getArchiveItems();
			// Preis überprüfen
			for (ArchiveBook a : archiveItems){
				if(b.getPrice() == a.getPrice()){
					//TODO neue Verknüpfung
					b.getArchiveItems().add(a);
					
				} else {
					// TODO neues ArchiveItem erstellen
				}
			}
		}
		
		// OrderObjekt erstellen
		// CascadingType -> Wenn letzte Order mit einem ArchiveItem gelöscht wird, dann auch ArchiveItem löschen
		
		// Order speichern
		
		
		return 0;
	}
	

}
