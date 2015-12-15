package appl.logic.orders.impl;

import appl.logic.orders.Order;

import java.util.List;

import appl.data.items.Book;
import appl.data.items.User;
// Die Frage hier ist: Ist das Objekt Order schon vollständig? D.h. es wird bei Bestellung erzeugt.

public class OrderImpl implements Order {
	private String id;
	private List<Book> orderItems;
	private User user;
	private boolean payed; // Brauchen wir das?
	// Date: Java.utils.Date oder eigene Klasse?
	
	

	@Override
	public void add() {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}


	@Override
	public void checkOut() {
		// TODO Auto-generated method stub

	}
	
	
	
	// Neuer Stuff
	@Override
	public double getPrice() {
		double price = 0.0;
		for (Book b : orderItems){
			price += b.getPrice();
		}
		return price;

	}


	@Override
	public void changeStatusToPayed() {
		updateOrderInDatabase();
		this.payed = true;
	}

	@Override
	public List<Book> getOrderedItems() {
		return this.orderItems;
	}

	@Override
	public boolean getPaymentStatus() {
		return this.payed;
	}

	@Override
	public User getUser() {
		return this.user;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public boolean saveOrderInDatabase() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// Private Hilfsmethoden
	
	private boolean updateOrderInDatabase(){
		return false;// Sollte nur benutzt werden um den Paymentstatus in der DB anzupassen
	}

	@Override
	public boolean removeOrderFromDatabase() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
