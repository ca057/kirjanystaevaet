package appl.databasemanagement.databaseImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.databasemanagement.BookDAO;
import appl.databasemanagement.SearchOptions;
import appl.databasemanagement.Searchfields;
import appl.items.Book;
import appl.items.Category;

@Service
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> getCategories() {
		return sessionFactory.getCurrentSession().createQuery("select distinct categoryName from Category").list();
	}

	@Override
	public Map searchBooks(Map<Enum<Searchfields>, String> searchterms, Enum<SearchOptions> searchOptions) {
		// Muss man das hier nicht aufsplitten, wie dynamisch kann man das
		// machen, wenn es um die Auswahl geht, aus welcher Table man überhaupt
		// selected?
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
