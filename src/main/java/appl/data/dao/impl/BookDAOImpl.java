package appl.data.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.BookDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Book;
import exceptions.data.IsbnAlreadyExistsException;

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
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return cr;
		//return cr.createAlias("categories", "c").createAlias("authors", "a");
	}

	@Override
	public List<Book> getAllBooks() {
		return getSession().createCriteria(Book.class).list();
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
				// TODO
				// Siehe Issue #14
				break;
			case publisher:
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
				break;
			case pubdate:
				//TODO Nur das Jahr
				// Siehe Issue #13
				break;
			case edition:
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
				break;
			case pages:
				// TODO
				// Range? Überhaupt in der Suche?
				break;

			}
		}
			/*
			if ((key.contains("category"))) { // Wieso contains und nicht
												// equals, wollen wir das
												// wirklich zulassen?
				cr.add(Restrictions.ilike("c." + key, "%" + entry.getValue() + "%"));
			} else if (key.contains("name") || key.contains("author")) { // Wieso
																			// verodert?
																			// Wieso
																			// legen
																			// wir
																			// nicht
																			// feste
																			// keys
																			// fest?
				cr.add(Restrictions.ilike("a." + key, "%" + entry.getValue() + "%"));
			} else {
				cr.add(Restrictions.ilike(key, "%" + entry.getValue() + "%"));
				System.out.println("in Dao: else-case, Searchfield = " + key);
			}
		}
		*/
		// System.out.println("Criteria to String " + cr.toString());
		List<Book> result = (List<Book>) cr.list();
		// sessionFactory.getCurrentSession().getTransaction().commit();
		// System.out.println("In DAO: Resultsize: " + result.size());
		return result;
	}

	@Override
	//public String insertBook(Book book) throws IsbnAlreadyExistsException {
	public String insertBook(Book book){
		Object id = getSession().save(book);
			
		
		//if (id instanceof String){
		if (id != null && id instanceof String ){	
			return (String) id;
		} else {
			// TODO Fehlerbehandlung, wenn Insert Book nicht funktioniert
			//throw new IsbnAlreadyExistsException();
			return null;
		}
		// TODO implement this
	}

	@Override
	public void deleteBook(Book book) {
		// TODO implement this
	}

	@Override
	public void updateBook(Book book, Map<Searchfields, String> map) {
		// TODO implement this
	}

}
