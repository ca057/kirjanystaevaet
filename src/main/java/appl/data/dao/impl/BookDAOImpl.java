package appl.data.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.BookDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Criteria setupAndGetCriteria() {
		if (sessionFactory == null) {
			throw new RuntimeException("[Error] SessionFactory is null");
		}
		Session s = getSession();
		Criteria cr = s.createCriteria(Book.class);
		return cr.createAlias("categories", "c").createAlias("authors", "a");
	}

	@Override
	public List<Book> getAllBooks() {
		return getSession().createCriteria(Book.class).list();
	}

	@Override
	public List<Book> getBooksByCategory(String categoryName) {
		// TODO implement this
		return null;
	}

	@Override
	public Book getBookByIsbn(int isbn) {
		return (Book) getSession().createQuery("from Book where isbn=" + isbn).uniqueResult();
	}

	@Override
	public List<Book> getBooksByOpenSearch(String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map) {
		// sessionFactory.getCurrentSession().beginTransaction();
		Criteria cr = setupAndGetCriteria();
		for (Entry<Searchfields, String> entry : map.entrySet()) {
			String key = entry.getKey().toString();
			if ((key.contains("category"))) { // Wieso contains und nicht
												// equals, wollen wir das
												// wirklich zulassen?
				cr.add(Restrictions.ilike("c." + key, "%" + entry.getValue() + "%"));
			} else if (key.contains("name") || key.contains("author")) { // Wieso verodert? Wieso legen wir nicht feste keys fest?
				cr.add(Restrictions.ilike("a." + key, "%" + entry.getValue() + "%"));
			} else {
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
			}
		}
		List result = (List<Book>) cr.list();
		// sessionFactory.getCurrentSession().getTransaction().commit();
		return result;
	}

	@Override
	public boolean insertBook(Book book) {
		try {
			// TODO save gibt identifier zurück, aber das ist vermutlich auch
			// nicht so überragend gelöst
			return getSession().save(book) != null;
		} catch (HibernateException e) {
			// TODO falls das bleibt, sinnvolles Exceptionhandling
			return false;
		}
	}

	@Override
	public boolean deleteBook(Book book) {
		// TODO siehe insertBook
		try {
			getSession().delete(book);
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
		getSession().createQuery(query).uniqueResult();
		return null;
	}

}
