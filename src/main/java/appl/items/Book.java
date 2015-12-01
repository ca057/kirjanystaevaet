package appl.items;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "BOOKDESCRIPTIONS", catalog = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "ISBN") })
public class Book implements Serializable {
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ISBN", unique = true, nullable = false)
	public String getIsbn() {
		return isbn;
	}

	@Column(name = "TITLE", nullable = false, length = 20)
	public String getTitle() {
		return title;
	}

	@Column(name = "DESCRIPTION", nullable = true, length = 20)
	public String getDescription() {
		return description;
	}

	@Column(name = "PRICE", nullable = true, length = 20)
	public double getPrice() {
		return price;
	}

	@Column(name = "PUBLISHER", nullable = true, length = 20)
	public String getPublisher() {
		return publisher;
	}

	@Column(name = "PUBDATE", nullable = true, length = 20)
	public String getPubdate() {
		return pubdate;
	}

	@Column(name = "EDITION", nullable = true, length = 20)
	public String getEdition() {
		return edition;
	}

	@Column(name = "PAGES", nullable = true, length = 20)
	public int getPages() {
		return pages;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "bookcategoriesbooks", catalog = "public", joinColumns = {
			@JoinColumn(name = "ISBN", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CATEGORYID", nullable = false, updatable = false) })
	public Set<Category> getCategories() {
		return categories;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "bookauthorsbooks", catalog = "public", joinColumns = {
			@JoinColumn(name = "ISBN", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "AUTHORID", nullable = false, updatable = false) })
	public Set<Author> getAuthors() {
		return authors;
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

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

}
