package appl.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.dao.BookDAO;
import appl.data.dao.Searchfields;
import appl.data.items.Book;

@Service
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;

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
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBook(Book book, Map<Searchfields, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

}
