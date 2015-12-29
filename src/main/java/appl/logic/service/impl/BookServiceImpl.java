package appl.logic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.dao.BookDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Book;
import appl.logic.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO dao;

	@Override
	public List<Book> getAllBooks() {
		return dao.getAllBooks();
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		Map<Searchfields, String> map = new HashMap<Searchfields, String>();
		map.put(Searchfields.categroyName, category);
		//return dao.getBooksByCategory(category);
		return null;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Map<Searchfields, String> map = new HashMap<Searchfields, String>();
		map.put(Searchfields.isbn, isbn);

		List<Book> bookList = dao.getBooksByMetadata(map);
		if (bookList.size() > 1){
			// todo Fehlerbehandlung
			System.out.println("Something went totally wrong");
		}
		return bookList.get(0);
	}

	@Override
	public List<Book> getBooksByOpenSearch(String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map) {
		return dao.getBooksByMetadata(map);
	}

}
