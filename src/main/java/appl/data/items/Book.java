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
 * </ul>
 * Lists of {@link Author} and {@link Category} are joined via many-to-many
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

	private Set<Category> categories = new HashSet<Category>(0);
	private Set<Author> authors = new HashSet<Author>(0);
	// TODO In Javadoc erwähnen.
	// private Set<Order> orders = new HashSet<Order>(0);
	private Set<ArchiveBook> archiveItems = new HashSet<ArchiveBook>(0);
	private Set<User> visitingUsers = new HashSet<User>(0);

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
			String edition, String pages, Set<Category> categories, Set<Author> authors) {
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
	public String getPages() {
		return pages;
	}

	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToMany(fetch = FetchType.LAZY)// Wenn das letzte Buch einer Kategorie gelöscht wird, wird die Kategorie nicht gelöscht
	@JoinTable(name = "bookcategoriesbooks", schema = "public", joinColumns = @JoinColumn(name = "isbn") , inverseJoinColumns = @JoinColumn(name = "categoryId") )
	public Set<Category> getCategories() {
		return categories;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "bookauthorsbooks", schema = "public", joinColumns = @JoinColumn(name = "isbn") , inverseJoinColumns = @JoinColumn(name = "authorId") )
	public Set<Author> getAuthors() {
		return authors;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	public Set<ArchiveBook> getArchiveItems() {
		return archiveItems;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "userbooks", schema = "public", joinColumns = @JoinColumn(name = "isbn") , inverseJoinColumns = @JoinColumn(name = "userId") )
	public Set<User> getVisitingUsers() {
		return visitingUsers;
	}
	/*
	 * @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "bookorder", schema = "public", joinColumns
	 * = @JoinColumn(name = "isbn") , inverseJoinColumns = @JoinColumn(name =
	 * "orderId") ) public Set<Order> getOrders() { return orders; }
	 */

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

	public void setPages(String pages) {
		this.pages = pages;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/*
	 * public void setOrders(Set<Order> orders) { this.orders = orders; }
	 */

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public void setArchiveItems(Set<ArchiveBook> archiveItems) {
		this.archiveItems = archiveItems;
	}

	private void setVisitingUsers(Set<User> visitingUsers) {
		this.visitingUsers = visitingUsers;
	}

	@Override
	public String toString() {
		String categoryString = "";
		/*
		 * for (Category c : categories){ categoryString = categoryString + " "
		 * + c.toString(); }
		 */
		return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", publisher=" + publisher + ", pubdate=" + pubdate + ", edition=" + edition + ", pages=" + pages
				+ ", category= " + categoryString + "]";
	}

}
