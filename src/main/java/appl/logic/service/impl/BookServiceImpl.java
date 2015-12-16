package appl.logic.service.impl;

import java.util.LinkedList;
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
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map) {
		return dao.getBooksByMetadata(map);
	}

}
