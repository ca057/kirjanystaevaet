package appl.data.items;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "order", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "orderId") })
public class Order {
	private int orderId;
	private Set<ArchiveBook> orderItems;
	private User user;
	//private boolean payed; // Brauchen wir das?
	// Date: Java.utils.Date oder eigene Klasse?
	private Calendar date;
	
	// Required Constructor, may be private
	private Order(){
		
	}

	public Order(Set<ArchiveBook> orderItems, User user, int year, int month, int day, int hourOfDay, int minute, int second) {
		this.orderItems = orderItems;
		this.user = user;
		//this.payed = false;
		this.date = Calendar.getInstance();
		this.date.set(year, month, day, hourOfDay, minute, second);

	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "orderId", unique = true, nullable = false)
	public int getId() {
		return this.orderId;
	}

	@Column(name = "date", nullable = false, length = 256)
	public Calendar getDate() {
		return this.date;
	}

	/*@Column(name = "paymentStatus", nullable = false, length = 256)
	public boolean getPaymentStatus() {
		return this.payed;
	}
	*/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID", nullable = false)
	public User getUser() {
		return this.user;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "OrderOrderItems", schema = "public", joinColumns = @JoinColumn(name = "orderId") , inverseJoinColumns = @JoinColumn(name = "archiveItemId") )
	public Set<ArchiveBook> getOrderItems() {
		return orderItems;
	}
	


	private void setId(int OrderId) {
		this.orderId = OrderId;
	}

	private void setOrderItems(Set<ArchiveBook> orderItems) {
		this.orderItems = orderItems;
	}

	private void setUser(User user) {
		this.user = user;
	}

	/*public void setPaymentStatus(boolean payed) {
		// Sollte eigentlich nichts machen?!? Benötige ich die change status
		// dann?
		this.payed = payed;
	}*/
	

	private void setDate(Calendar date) {
		this.date = date;
	}

	public double calcPrice() {
		double price = 0.0;
		for (ArchiveBook b : orderItems) {
			price += b.getPrice();
		}
		return price;

	}

	/*public void changeStatusToPayed() {
		updateOrderInDatabase();
		this.payed = true;
	}
	*/
	// Private Hilfsmethoden

	/*
	public boolean saveOrderInDatabase() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean updateOrderInDatabase() {
		return false;// Sollte nur benutzt werden um den Paymentstatus in der DB
						// anzupassen
	}

	public boolean removeOrderFromDatabase() {
		// TODO Auto-generated method stub
		return false;
	}
	*/

}
