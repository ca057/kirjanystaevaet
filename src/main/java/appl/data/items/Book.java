package appl.data.items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "BOOKDESCRIPTIONS", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "isbn") })
public class Book {
	private String isbn;
	private String title;
	private String description;
	private double price;
	private String publisher;
	private String pubdate;
	private String edition;
	private int pages;

	private Set<Category> categories = new HashSet<Category>(0);
	private Set<Author> authors = new HashSet<Author>(0);
	private Set<Order> orders = new HashSet<Order>(0);

	public Book() {
	}

	public Book(String isbn, String title, String description, double price, String publisher, String pubdate,
			String edition, int pages, Set<Category> categories, Set<Author> authors) {
		super();
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
	public int getPages() {
		return pages;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "bookcategoriesbooks", schema = "public", joinColumns = @JoinColumn(name = "isbn") , inverseJoinColumns = @JoinColumn(name = "categoryId") )
	public Set<Category> getCategories() {
		return categories;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "bookauthorsbooks", schema = "public", joinColumns = @JoinColumn(name = "isbn") , inverseJoinColumns = @JoinColumn(name = "authorId") )
	public Set<Author> getAuthors() {
		return authors;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "bookorder", schema = "public", joinColumns = @JoinColumn(name = "isbn") , inverseJoinColumns = @JoinColumn(name = "orderId") )
	public Set<Order> getOrders() {
		return orders;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}


	
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", publisher=" + publisher + ", pubdate=" + pubdate + ", edition=" + edition + ", pages=" + pages
				+ "]";
	}

}
