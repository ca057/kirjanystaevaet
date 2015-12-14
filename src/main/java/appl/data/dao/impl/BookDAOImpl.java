package appl.data.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.data.dao.BookDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Book;

@Component
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Book> getBooksByCategory(String categoryName) {
		// return ((Category)
		// (sessionFactory.getCurrentSession().createQuery("from Category where
		// categoryName = " + categoryName).uniqueResult())).getBooks();
		return null;
	}

	@Override
	public Book getBookByIsbn(int isbn) {
		return (Book) sessionFactory.getCurrentSession().createQuery("from Book where isbn=" + isbn).uniqueResult();
	}

	@Override
	public List<Book> getBooksByOpenSearch(String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map) {
		Criteria cr = setupAndGetCriteria();
		for (Entry<Searchfields, String> entry : map.entrySet()) {
			String key = entry.getKey().toString();
			if ((key.contains("category"))) {
				cr.add(Restrictions.ilike("c." + key, "%" + entry.getValue() + "%"));
			} else if (key.contains("name") || key.contains("author")) {
				cr.add(Restrictions.ilike("a." + key, "%" + entry.getValue() + "%"));
			} else {
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
			}
		}
		return (List<Book>) cr.list();
	}

	private Criteria setupAndGetCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(Book.class).createAlias("categories", "c")
				.createAlias("authors", "a");
	}

	@Override
	public boolean insertBook(Book book) {
		try {
			// TODO save gibt identifier zurück, aber das ist vermutlich auch
			// nicht so überragend gelöst
			return sessionFactory.getCurrentSession().save(book) != null;
		} catch (HibernateException e) {
			// TODO falls das bleibt, sinnvolles Exceptionhandling
			return false;
		}
	}

	@Override
	public boolean deleteBook(Book book) {
		// TODO siehe insertBook
		try {
			sessionFactory.getCurrentSession().delete(book);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	public boolean updateBook(Book book, Map<Searchfields, String> map) {
		// TODO session.merge()? session.saveOrUpdate()?
		return false;
	}

	@Override
	public Book executeQuery(String query) {
		sessionFactory.getCurrentSession().createQuery(query).uniqueResult();
		return null;
	}

}
