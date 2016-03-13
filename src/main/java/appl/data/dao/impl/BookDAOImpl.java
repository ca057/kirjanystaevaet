package appl.data.dao.impl;

/**
 * @author Madeleine
 */
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.builder.BookBuilder;
import appl.data.builder.BuilderFactory;
import appl.data.builder.BuilderHelper;
import appl.data.dao.BookDAO;
import appl.data.items.Book;
import appl.enums.SearchMode;
import appl.enums.Searchfields;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;
import exceptions.data.ErrorMessageHelper;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	BuilderFactory builderFactory;

	private BookBuilder getBookBuilder() {
		return builderFactory.getBookBuilder();
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Criteria setupAndGetCriteria() {
		if (sessionFactory == null) {
			throw new RuntimeException("[Error] SessionFactory is null");
		}
		Session s = getSession();
		Criteria cr = s.createCriteria(Book.class);
		cr.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return cr;
		// return cr.createAlias("categories", "c").createAlias("authors", "a");
	}

	private Criteria getCriteriaForSell(Criteria cr) {
		cr.add(Restrictions.ge("stock", 0));
		return cr;
	}

	private Criteria getCriteriaForAvailable(Criteria cr) {
		cr.add(Restrictions.gt("stock", 0));
		return cr;
	}

	@Override
	public List<Book> getAllBooks(SearchMode mode) {
		Criteria cr = setupAndGetCriteria();
		switch (mode) {
		case ALL:
			break;
		case SELL:
			cr = getCriteriaForSell(cr);
			break;

		case AVAILABLE:
			cr = getCriteriaForAvailable(cr);
			break;
		}

		return cr.list();
	}

	@Override
	public List<Book> getBooksByOpenSearch(String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map, SearchMode mode) {
		Criteria cr = setupAndGetCriteria();
		switch (mode) {
		case ALL:
			break;
		case SELL:
			cr = getCriteriaForSell(cr);
			break;

		case AVAILABLE:
			cr = getCriteriaForAvailable(cr);
			break;
		}

		cr.createAlias("categories", "c").createAlias("authors", "a");
		for (Entry<Searchfields, String> entry : map.entrySet()) {
			String key = entry.getKey().toString();

			switch (entry.getKey()) {
			case nameF:
				cr.add(Restrictions.ilike("a." + key, "%" + entry.getValue() + "%"));
				break;
			case nameL:
				cr.add(Restrictions.ilike("a." + key, "%" + entry.getValue() + "%"));
				break;
			case title:
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
				break;
			case isbn:
				cr.add(Restrictions.eq(key, entry.getValue()));
				break;
			case categoryName:
				cr.add(Restrictions.ilike("c." + key, "%" + entry.getValue() + "%"));
				break;
			case authorId:
				cr.add(Restrictions.eq("a." + key, entry.getValue()));
				break;
			case categoryId:
				cr.add(Restrictions.eq("c." + key, entry.getValue()));
				break;
			case description:
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
				break;
			case price:
				double price = Double.parseDouble(entry.getValue().replace(",", "."));
				cr.add(Restrictions.between(key, price - 5, price + 5));
				break;
			case publisher:
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
				break;
			case pubdate:
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
				break;
			case edition:
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
				break;
			case pages:
				cr.add(Restrictions.eq(key, entry.getValue()));
				break;
			default:
				break;
			}
		}
		@SuppressWarnings("unchecked")
		List<Book> result = cr.list();
		return result;

	}

	@Override
	public Book getBookByIsbn(String isbn) throws EntityDoesNotExistException {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq("isbn", isbn));
		Object result = cr.uniqueResult();
		if (result != null) {
			Book book = (Book) result;
			return book;
		} else {
			throw new EntityDoesNotExistException();
		}
	}

	@Override
	public String insertBook(Book book) throws DatabaseException {
		Object id = getSession().save(book);
		if (id != null && id instanceof String) {
			return (String) id;
		} else {
			throw new DatabaseException(ErrorMessageHelper.insertFailed("book"));

		}
	}

	@Override
	public void deleteBook(String isbn) {
		Book book = getSession().get(Book.class, isbn);
		getSession().delete(book);
	}

	@Override
	public void updateBook(Book book) {
		getSession().update(book);
	}

	@Override
	public void decrementStock(String isbn, int decrement) throws DatabaseException {
		Book book = getSession().get(Book.class, isbn);
		if (book.getStock() > 0) {
			book.decrementStock(decrement);
			getSession().update(book);
		} else {
			throw new DatabaseException(ErrorMessageHelper.stockIsNull(book.getTitle()));
		}

	}

	@Override
	public void setStockToNegative(String isbn) {
		Book book = getSession().get(Book.class, isbn);
		BookBuilder bookBuilder = BuilderHelper.saveOldValues(book, getBookBuilder());

		bookBuilder.setStock(-1);
		// book.setStock(-1);
		getSession().update(bookBuilder.createBook());
		// getSession().update(book);
	}
}
