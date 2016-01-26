package appl.data.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.AuthorDAO;
import appl.data.items.Author;
import appl.data.items.Book;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Criteria setupAndGetCriteria() {
		if (sessionFactory == null) {
			throw new RuntimeException("[Error] SessionFactory is null");
		}
		Session s = getSession();
		Criteria cr = s.createCriteria(Author.class);
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return cr.createAlias("categories", "c").createAlias("books", "b");
	}


	@Override
	public List<Author> getAuthors() {
		// TODO implement this!
		return null;
	}

	@Override
	public List<Author> getAuthorsByNameF(String nameF) {
		// TODO implement this!
		return null;
	}

	@Override
	public List<Author> getAuthorsByNameL(String nameL) {
		// TODO implement this!
		return null;
	}

	@Override
	public Author getAuthorByID(int authorID) {
		// TODO implement this!
		return null;
	}

	@Override
	public void insertAuthor(Author author) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAuthor(Author author) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateAuthor(Author author) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Author> getAuthorByExactNames(String nameF, String nameL) {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq("nameF", nameF));
		cr.add(Restrictions.eq("nameL", nameL));
		List<Author> authors = (List<Author>) cr.list();
		return authors;
	}

}
