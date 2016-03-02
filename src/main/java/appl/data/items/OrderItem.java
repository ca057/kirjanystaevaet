package appl.data.items;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "archivebook", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "orderItemId") })
public class OrderItem {
	private int orderItemId;
	double price;
	int numberOf;
	Book book;
	Orderx order;
	
	private OrderItem() {
	}
	
	/**
	 * 
	 * @param book
	 * @param price
	 */
	public OrderItem(Book book, double price, int numberOf){
		this.book = book;
		this.price = price;
		this.numberOf = numberOf;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "orderItemId", unique = true, nullable = false)
	public int getArchiveItemId(){
		return orderItemId;
	}
	@Column(name = "price", nullable = false)
	public double getPrice(){
		return price;
	}
	@Column(name = "numberOf", nullable=false)
	public int getNumberOf(){
		return numberOf;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ISBN", nullable = false)
	public Book getBook(){
		return book;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDERID", nullable = true)
	public Orderx getOrder(){
		return order;
	}
	
	
	
	private void setArchiveItemId (int archiveItemId){
		this.orderItemId = archiveItemId;
	}
	private void setPrice (double price){
		this.price = price;
	}
	private void setBook(Book book){
		this.book = book;
	}
	private void setOrder(Orderx order){
		this.order = order;
	}
	private void setNumberOf(int numberOf){
		this.numberOf = numberOf;
	}
	
	@Override
	public String toString(){
		String s = "OrderItemId=" + orderItemId + " BookTitle=" + book.getTitle() + 
				" Price at Order=" + price + " number=" + numberOf + " User Name=" + 
				" orderId=" + order.getId() + 
				order.getUser().getName() + " User Id=" +
				order.getUser().getUserId();
		return s;
	}
}
