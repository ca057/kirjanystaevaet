package appl.logic.service.impl;

import java.util.List;

import appl.data.items.Book;

public interface QueryCreator {

	// public Map getBookByID(String id);
	public List<String> getCategories();
	public List<Book> getBooksByCategory(String category);
	public Book getBookByIsbn(int isbn);
	public List<Book> getBooksByOpenSearch(String searchTerm);
	public List<Book> getBooksByMetadata(String title, String author, String year, String isbn, String category);
}