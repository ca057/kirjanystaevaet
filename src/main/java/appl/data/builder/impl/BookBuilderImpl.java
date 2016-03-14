package appl.data.builder.impl;

import java.util.HashSet;
import java.util.Set;

import appl.data.builder.BookBuilder;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import appl.data.items.UserBookStatistic;

public class BookBuilderImpl implements BookBuilder {
	private String isbn;
	private String title;
	private String description;
	private double price;
	private String publisher;
	private String pubdate;
	private String edition;
	private String pages;
	private int stock;
	private Set<Author> authors;
	private Set<Category> categories;
	private int visitCount;
	private Set<UserBookStatistic> userBookStatistics = new HashSet<UserBookStatistic>(0);

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
	public BookBuilder setPages(String pages) {
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
	public BookBuilder setStock(int stock) {
		this.stock = stock;
		return this;
	}

	@Override
	public BookBuilder setVisitCount(int visitCount) {
		this.visitCount = visitCount;
		return this;
	}

	@Override
	public BookBuilder setUserBookStatistics(Set<UserBookStatistic> userBookStatistics) {
		this.userBookStatistics = userBookStatistics;
		return this;
	}

	@Override
	public Book createBook() {
		return new Book(this.isbn, this.title, this.description, this.price, this.publisher, this.pubdate, this.edition,
				this.pages, this.stock, this.categories, this.authors, this.visitCount, this.userBookStatistics);
	}

}
