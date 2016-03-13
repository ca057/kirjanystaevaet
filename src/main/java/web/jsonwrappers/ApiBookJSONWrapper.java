package web.jsonwrappers;

import java.util.List;
import java.util.stream.Collectors;

import appl.data.items.Book;

public class ApiBookJSONWrapper {
	private String isbn;

	private String title;

	private String description;

	private double price;

	private String publisher;

	private String pubdate;

	private String edition;

	private String pages;

	private List<String[]> authors;

	private List<String> categories;

	public ApiBookJSONWrapper() {
		super();
	}

	public ApiBookJSONWrapper(Book book) {
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
		this.authors = book.getAuthors().stream().map(a -> new String[] { a.getNameF(), a.getNameL() })
				.collect(Collectors.toList());
		this.categories = book.getCategories().stream().map(c -> c.getCategoryName()).collect(Collectors.toList());
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public List<String[]> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String[]> authors) {
		this.authors = authors;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

}
