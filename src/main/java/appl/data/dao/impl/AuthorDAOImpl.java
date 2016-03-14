package appl.data.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.AuthorDAO;
import appl.data.items.Author;
import exceptions.data.EntityDoesNotExistException;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

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
		Criteria cr = s.createCriteria(Author.class);
		cr.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		return cr;

		// return cr.createAlias("books", "b");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAuthors() {
		return getSession().createCriteria(Author.class).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAuthorsByNameF(String nameF) {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.ilike("nameF", nameF));
		List<Author> authors = cr.list();

		return authors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAuthorsByNameL(String nameL) {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.ilike("nameL", nameL));
		List<Author> authors = cr.list();

		return authors;
	}

	@Override
	public Author getAuthorByID(int authorID) throws EntityDoesNotExistException {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq("authorId", authorID));
		Object result = cr.uniqueResult();
		if (result != null) {
			Author author = (Author) result;
			return author;
		} else {
			throw new EntityDoesNotExistException();

		}

	}

	@Override
	public int insertAuthor(Author author) {
		int id = (int) getSession().save(author);
		return id;
	}

	@Override
	public void deleteAuthor(Author author) {
		getSession().delete(author);
	}

	@Override
	public void updateAuthor(Author author) {
		getSession().update(author);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAuthorByExactNames(String nameF, String nameL) {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq("nameF", nameF));
		cr.add(Restrictions.eq("nameL", nameL));
		List<Author> authors = cr.list();
		return authors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAuthorsByIsbn(String isbn) {
		Criteria cr = setupAndGetCriteria();
		cr.createAlias("books", "b");
		cr.add(Restrictions.eq("b.isbn", isbn));
		List<Author> authors = cr.list();
		return authors;
	}

}
