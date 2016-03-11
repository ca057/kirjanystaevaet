package web.jsonwrappers;

import java.util.ArrayList;
import java.util.List;

import appl.data.items.Book;

public class ExtendedBookJSONWrapper extends BookJSONWrapper {

	private int stock = 0;

	private List<Integer> authors = new ArrayList<>();

	private List<Integer> categories = new ArrayList<>();

	public ExtendedBookJSONWrapper() {

	}

	public ExtendedBookJSONWrapper(Book book) {
		super(book);
		stock = book.getStock();
		book.getAuthors().forEach(a -> authors.add(a.getAuthorId()));
		book.getCategories().forEach(c -> categories.add(c.getCategoryID()));
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Integer> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Integer> authors) {
		this.authors = authors;
	}

	public List<Integer> getCategories() {
		return categories;
	}

	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "ExtendedBookJSONWrapper [stock=" + stock + ", authors=" + authors + ", categories=" + categories
				+ ", getIsbn()=" + getIsbn() + ", getTitle()=" + getTitle() + ", getDescription()=" + getDescription()
				+ ", getPrice()=" + getPrice() + ", getPublisher()=" + getPublisher() + ", getPubdate()=" + getPubdate()
				+ ", getEdition()=" + getEdition() + ", getPages()=" + getPages() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
