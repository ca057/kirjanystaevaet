package appl.data.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.AuthorDAO;
import appl.data.items.Author;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

	@Autowired
	private SessionFactory sessionFactory;

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

}
