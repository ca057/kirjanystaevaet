package appl.logic.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import appl.data.items.Book;

@Service
public class QueryCreatorImpl implements QueryCreator {
	/*
	 * @Override public Map getBookByID(String id) { // TODO Auto-generated
	 * method stub return null; }
	 */
	public QueryCreatorImpl() {

	}

	@Override
	public List<String> getCategories() {
		// TODO vernünftige Abfrage an die Datenbank
		List<String> categoryList = new ArrayList<String>();

		categoryList.add("Java");
		categoryList.add("PHP");
		categoryList.add("MySQL");
		categoryList.add("mysql");
		LinkedList<String> list = new LinkedList<String>();
		// (new
		// CategoryDAOImpl().getCategories().iterator().next().getCategoryName());
		// session.createQuery("select distinct categoryName from
		// Category").list();

		return categoryList;
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookByIsbn(int isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByOpenSearch(String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByMetadata(String title, String author, String year, String isbn, String category) {

		return null;
	}

}