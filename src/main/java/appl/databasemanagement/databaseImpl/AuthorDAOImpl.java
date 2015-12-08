package appl.databasemanagement.databaseImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.databasemanagement.AuthorDAO;
import appl.items.Author;

@Service
public class AuthorDAOImpl implements AuthorDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Author> getAuthors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> getAuthorsByNameF(String nameF) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> getAuthorsByNameL(String nameL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author getAuthorByID(int authorID) {
		// TODO Auto-generated method stub
		return null;
	}

}
