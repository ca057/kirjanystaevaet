package appl.logic.orders;
import java.util.List;

import appl.data.items.Book;
import appl.data.items.User;

public interface Order {
	// Add was?
	public void add();
	// Remove was
	public void remove();
	
	public double getPrice();

	// Bitte sprechendere Methodennamen!
	public void checkOut();
	
	public void changeStatusToPayed();
	
	public boolean saveOrderInDatabase();
	public boolean removeOrderFromDatabase();
	
	// Getters
	public List<Book> getOrderedItems();
	public boolean getPaymentStatus();
	public User getUser();
	public String getId();
	

}
