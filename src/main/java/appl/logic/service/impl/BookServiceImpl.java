package appl.logic.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.data.dao.BookDAO;
import appl.data.dao.impl.BookDAOImpl;
import appl.data.enums.Searchfields;
import appl.data.items.Book;
import appl.logic.service.BookService;

@Component
public class BookServiceImpl implements BookService {
	@Autowired
	BookDAO dao;

	@Override
	public List<Book> getAllBooks() {
		return null;
	}

	@Override
	public List<String> getBooksByCategory(String category) {
		List<Book> list = dao.getBooksByCategory(category);
		List<String> bookTitles = new LinkedList<String>();
		for (Book book : list) {
			bookTitles.add(book.getTitle());
		}
		return bookTitles;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Map map = new HashMap<Searchfields, String>();
		map.put(Searchfields.isbn, isbn);
		
		List<Book> bookList = dao.getBooksByMetadata(map);
		return null;
	}

	@Override
	public List<Book> getBooksByOpenSearch(String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByMetadata(String title, String author, String year, String isbn, String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
