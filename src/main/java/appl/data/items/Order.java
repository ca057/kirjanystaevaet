package appl.data.items;

import java.util.List;

public class Order{
	private String id;
	private List<Book> orderItems;
	private User user;
	private boolean payed; // Brauchen wir das?
	// Date: Java.utils.Date oder eigene Klasse?
	
	


	public void add() {
		// TODO Auto-generated method stub

	}


	public void remove() {
		// TODO Auto-generated method stub

	}



	public void checkOut() {
		// TODO Auto-generated method stub

	}
	
	
	
	// Neuer Stuff

	public double getPrice() {
		double price = 0.0;
		for (Book b : orderItems){
			price += b.getPrice();
		}
		return price;

	}



	public void changeStatusToPayed() {
		updateOrderInDatabase();
		this.payed = true;
	}


	public List<Book> getOrderedItems() {
		return this.orderItems;
	}


	public boolean getPaymentStatus() {
		return this.payed;
	}


	public User getUser() {
		return this.user;
	}


	public String getId() {
		return this.id;
	}


	public boolean saveOrderInDatabase() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// Private Hilfsmethoden
	
	private boolean updateOrderInDatabase(){
		return false;// Sollte nur benutzt werden um den Paymentstatus in der DB anzupassen
	}


	public boolean removeOrderFromDatabase() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
