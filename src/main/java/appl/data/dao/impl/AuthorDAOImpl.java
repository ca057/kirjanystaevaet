package appl.data.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.data.dao.AuthorDAO;
import appl.data.items.Author;

@Component
public class AuthorDAOImpl implements AuthorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Author> getAuthors() {
		return sessionFactory.getCurrentSession().createQuery("from Author").list();
	}

	@Override
	public List<Author> getAuthorsByNameF(String nameF) {
		return sessionFactory.getCurrentSession().createQuery("from Author where nameF like %" + nameF + "%").list();
	}

	@Override
	public List<Author> getAuthorsByNameL(String nameL) {
		return sessionFactory.getCurrentSession().createQuery("from Author where nameL like %" + nameL + "%").list();
	}

	@Override
	public Author getAuthorByID(int authorID) {
		return (Author) sessionFactory.getCurrentSession().createQuery("from Author where authorID = " + authorID)
				.uniqueResult();
	}

	@Override
	public boolean insertAuthor(Author author) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAuthor(Author author) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAuthor(Author author) {
		// TODO Auto-generated method stub
		return false;
	}

}
