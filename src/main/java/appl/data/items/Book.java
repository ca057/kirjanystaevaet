package appl.data.items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
//import org.hibernate.annotations.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Book is a POJO marked as persistent entity with table name
 * "bookdescriptions". It represents a book object with different variables:
 * <ul>
 * <li>isbn</li>
 * <li>title</li>
 * <li>description</li>
 * <li>price</li>
 * <li>publisher</li>
 * <li>pubdate</li>
 * <li>edition</li>
 * <li>pages</li>
 * <li>stock</li>
 * <li>visitCount</li>
 * </ul>
 * {@link Set}s of {@link Author} and {@link Category} are joined via many-to-many
 * connections.
 * {@link Set}s of {@link OrderItem} and {@link UserBookStatistic} are joined via one-to-many
 * connections.
 * 
 * @author Johannes
 * @author Madeleine
 *
 */
@Entity
@Table(name = "bookdescriptions", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "isbn") })
public class Book {
	private String isbn;
	private String title;
	private String description;
	private double price;
	private String publisher;
	private String pubdate;
	private String edition;
	private String pages;
	private int stock;
	private int visitCount;

	private Set<Category> categories = new HashSet<Category>(0);
	private Set<Author> authors = new HashSet<Author>(0);
	private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);
	private Set<UserBookStatistic> userBookStatistics = new HashSet<UserBookStatistic>(0);

	public Book() {
	}

	/**
	 * Constructor to set all variables of an instance of {@link Book}.
	 * 
	 * @param isbn
	 * @param title
	 * @param description
	 * @param price
	 * @param publisher
	 * @param pubdate
	 * @param edition
	 * @param pages
	 * @param categories
	 * @param authors
	 */
	public Book(String isbn, String title, String description, double price, String publisher, String pubdate,
			String edition, String pages, int stock, Set<Category> categories, Set<Author> authors, int visitCount, Set<UserBookStatistic> userBookStatistics) {
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.price = price;
		this.publisher = publisher;
		this.pubdate = pubdate;
		this.edition = edition;
		this.pages = pages;
		this.categories = categories;
		this.authors = authors;
		this.stock = stock;
		this.visitCount = visitCount;
		this.userBookStatistics = userBookStatistics;
	}

	@Id
	@Column(name = "isbn", unique = true, nullable = false)
	public String getIsbn() {
		return isbn;
	}

	@Column(name = "title", nullable = false, length = 256)
	public String getTitle() {
		return title;
	}

	@Column(name = "description", nullable = true, length = 4096)
	public String getDescription() {
		return description;
	}

	@Column(name = "price", nullable = true, length = 8)
	public double getPrice() {
		return price;
	}

	@Column(name = "publisher", nullable = true, length = 256)
	public String getPublisher() {
		return publisher;
	}

	@Column(name = "pubdate", nullable = true, length = 256)
	public String getPubdate() {
		return pubdate;
	}

	@Column(name = "edition", nullable = true, length = 256)
	public String getEdition() {
		return edition;
	}

	@Column(name = "pages", nullable = true, length = 8)
	public String getPages() {
		return pages;
	}

	@Column(name = "stock", nullable = false, length = 8)
	public int getStock() {
		return stock;
	}

	@Column(name = "visitCount", nullable = false, length = 8, columnDefinition = "int default 0")
	public int getVisitCount() {
		return visitCount;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "bookcategoriesbooks", schema = "public", joinColumns = @JoinColumn(name = "isbn") , inverseJoinColumns = @JoinColumn(name = "categoryId") )
	public Set<Category> getCategories() {
		return categories;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name = "bookauthorsbooks", schema = "public", joinColumns = @JoinColumn(name = "isbn") , inverseJoinColumns = @JoinColumn(name = "authorId") )
	public Set<Author> getAuthors() {
		return authors;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	public Set<UserBookStatistic> getUserBookStatistics() {
		return userBookStatistics;
	}

	private void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	private void setPrice(double price) {
		this.price = price;
	}

	private void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	private void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	private void setEdition(String edition) {
		this.edition = edition;
	}

	private void setPages(String pages) {
		this.pages = pages;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	private void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	private void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	private void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	private void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	private void setUserBookStatistics(Set<UserBookStatistic> userBookStatistics) {
		this.userBookStatistics = userBookStatistics;
	}

	public void decrementStock(int decrement) {
		stock -= decrement;
	}
	// TODO bessere Lösung finden

	public int addToStock(int add) {
		stock += add;
		return stock;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", publisher=" + publisher + ", pubdate=" + pubdate + ", edition=" + edition + ", pages=" + pages
				+ ", stock=" + stock + "]";
	}

}
