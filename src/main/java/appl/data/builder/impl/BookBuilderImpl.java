/**
 * Created by Sunflower
 */
package appl.data.builder.impl;

import java.util.Set;

import appl.data.builder.BookBuilder;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;

public class BookBuilderImpl implements BookBuilder {
	private String isbn;
	private String title;
	private String description;
	private double price;
	private String publisher;
	private String pubdate;
	private String edition;
	private int pages;
	private Set<Author> authors;
	private Set<Category> categories;

	public BookBuilderImpl() {
	}

	@Override
	public BookBuilder setIsbn(String isbn) {
		this.isbn = isbn;
		return this;
	}

	@Override
	public BookBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	@Override
	public BookBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public BookBuilder setPrice(double price) {
		this.price = price;
		return this;
	}

	@Override
	public BookBuilder setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}

	@Override
	public BookBuilder setPubdate(String pubdate) {
		this.pubdate = pubdate;
		return this;
	}

	@Override
	public BookBuilder setEdition(String edition) {
		this.edition = edition;
		return this;
	}

	@Override
	public BookBuilder setPages(int pages) {
		this.pages = pages;
		return this;
	}

	@Override
	public BookBuilder setAuthors(Set<Author> authors) {
		this.authors = authors;
		return this;
	}

	@Override
	public BookBuilder setCategories(Set<Category> categories) {
		this.categories = categories;
		return this;
	}

	@Override
	public Book createBook() {
		return new Book(this.isbn, this.title, this.description, this.price, this.publisher, this.pubdate, this.edition,
				this.pages, this.categories, this.authors);
	}

}
