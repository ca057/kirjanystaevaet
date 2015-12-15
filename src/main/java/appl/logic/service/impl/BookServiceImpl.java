package appl.logic.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import appl.data.dao.BookDAO;
import appl.data.dao.impl.BookDAOImpl;
import appl.data.items.Book;
import appl.logic.service.BookService;

@Component
public class BookServiceImpl implements BookService {
	BookDAO dao = new BookDAOImpl();

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
		// TODO Auto-generated method stub
		return null;
	}

}
