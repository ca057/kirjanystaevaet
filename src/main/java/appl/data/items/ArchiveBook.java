package appl.data.items;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "archivebook", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "archiveItemId") })
public class ArchiveBook {
	private int archiveItemId;
	double price;
	Book book;
	Set<Order> orders;
	
	private ArchiveBook() {
	}
	
	public ArchiveBook(Book book, double price, Set<Order> orders){
		this.orders = orders;
		this.book = book;
		this.price = price;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "archiveItemId", unique = true, nullable = false)
	public int getArchiveItemId(){
		return archiveItemId;
	}
	@Column(name = "price", nullable = false)
	public double getPrice(){
		return price;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ISBN", nullable = false)
	public Book getBook(){
		return book;
	}
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "orderItems")
	public Set<Order> getOrders(){
		return orders;
	}
	private void setArchiveItemId (int archiveItemId){
		this.archiveItemId = archiveItemId;
	}
	private void setPrice (double price){
		this.price = price;
	}
	private void setBook(Book book){
		this.book = book;
	}
	private void setOrders(Set<Order> orders){
		this.orders = orders;
	}
}
