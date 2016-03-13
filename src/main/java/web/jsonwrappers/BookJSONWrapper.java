package web.jsonwrappers;

import java.util.ArrayList;
import java.util.List;

import appl.data.items.Book;

/**
 * Wrapper for sending and receiving data of a {@link Book} to and from the
 * client. Should not be used with the API as it containts internal data.
 * 
 * @author Christian
 *
 */
public class BookJSONWrapper {
	private String isbn;

	private String title;

	private String description;

	private double price;

	private String publisher;

	private String pubdate;

	private String edition;

	private String pages;

	private int stock = 0;

	private List<Integer> authors = new ArrayList<>();

	private List<Integer> categories = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public BookJSONWrapper() {
		super();
	}

	/**
	 * Constructor for converting a {@link Book} into a {@code BookJSONWrapper}.
	 * 
	 * @param book
	 *            the {@link Book} to convert
	 */
	public BookJSONWrapper(Book book) {
		if (book == null) {
			throw new IllegalArgumentException(
					"The passed book is null and can not be converted to a BookJSONWrapper.");
		}
		this.isbn = book.getIsbn();
		this.title = book.getTitle();
		this.description = book.getDescription();
		this.price = book.getPrice();
		this.publisher = book.getPublisher();
		this.pubdate = book.getPubdate();
		this.edition = book.getEdition();
		this.pages = book.getPages();
		book.getAuthors().forEach(a -> authors.add(a.getAuthorId()));
		book.getCategories().forEach(c -> categories.add(c.getCategoryID()));
	}

	public final String getIsbn() {
		return isbn;
	}

	public final void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final double getPrice() {
		return price;
	}

	public final void setPrice(double price) {
		this.price = price;
	}

	public final String getPublisher() {
		return publisher;
	}

	public final void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public final String getPubdate() {
		return pubdate;
	}

	public final void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public final String getEdition() {
		return edition;
	}

	public final void setEdition(String edition) {
		this.edition = edition;
	}

	public final String getPages() {
		return pages;
	}

	public final void setPages(String pages) {
		this.pages = pages;
	}

	public final int getStock() {
		return stock;
	}

	public final void setStock(int stock) {
		this.stock = stock;
	}

	public final List<Integer> getAuthors() {
		return authors;
	}

	public final void setAuthors(List<Integer> authors) {
		this.authors = authors;
	}

	public final List<Integer> getCategories() {
		return categories;
	}

	public final void setCategories(List<Integer> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "BookJSONWrapper [isbn=" + isbn + ", title=" + title + ", description=" + description + ", price="
				+ price + ", publisher=" + publisher + ", pubdate=" + pubdate + ", edition=" + edition + ", pages="
				+ pages + ", stock=" + stock + ", authors=" + authors + ", categories=" + categories + "]";
	}
}
