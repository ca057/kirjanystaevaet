package appl.data.items;
/**
 * @author Madeleine
 */
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "orderx", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "orderId") })
public class Orderx {
	private int orderId;
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	private User user;

	private Calendar date;
	
	// Required Constructor, may be private
	private Orderx(){
		
	}
	
	public Orderx(Calendar date){
		this.date = date;
	}
	
	public Orderx(Set<OrderItem> orderItems, Calendar date){
		this.orderItems = orderItems;
		this.date = date;
	}

	public Orderx(Set<OrderItem> orderItems, User user, Calendar date) {
		this.orderItems = orderItems;
		this.user = user;
		//this.payed = false;
		this.date = date;

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

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USERID", nullable = true)
	public User getUser() {
		return this.user;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
	//@JoinTable(name = "orderorderitems", schema = "public", joinColumns = @JoinColumn(name = "orderId") , inverseJoinColumns = @JoinColumn(name = "archiveItemId") )
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	


	private void setId(int OrderId) {
		this.orderId = OrderId;
	}

	private void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setUser(User user) {
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
		for (OrderItem b : orderItems) {
			price += b.getPrice();
		}
		return price;

	}
	public String toString(){
		String s = "OrderId + " +orderId + " UserId" +user.getUserId() + " UserSurName " + user.getSurname() + "\n" + "Amount of Ordered Items " +orderItems.size() + "\n";
		for (OrderItem a : orderItems){
			s = s + "Isbn " + a.getBook().getIsbn() + " Title" + a.getBook().getTitle() + " Ordering-Price " + a.getPrice() +"\n";
 		}
		return s;
	}
	//public String toString(){
		
	//}

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
