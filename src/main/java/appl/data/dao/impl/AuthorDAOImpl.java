package appl.data.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.AuthorDAO;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;

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
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return cr.createAlias("books", "b");
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
		System.out.println("in getAuthorByID\n");
		Criteria cr = setupAndGetCriteria();
		System.out.println("Got the criteria\n");
		cr.add(Restrictions.eq("authorId", authorID));
		System.out.println("added restrictions\n");
		Object result = cr.uniqueResult();
		System.out.println("Got result\n");
		if ( result != null){
			Author author = (Author) result;
			System.out.println("in getAuthorById, got Author: " + author.toString()+ "\n");
			return author;
		} else {
			//TODO Fehlerbehandlung -> Author does not exist exception
			System.out.println("Author wurde nicht in der DB gefunden\n\n");
		}
		/*
		System.out.println("Versuch über hql\n\n");
		Query query = getSession().createQuery("select nameL from Author where authorId='15'");
		List<String> fromDB = query.list();
		System.out.println("got book from db\n\nsize is : " + fromDB.size());
		
		for (String a : fromDB){
			System.out.println("Authors printen\n" + a);
			
		}
		*/
		System.out.println("BEfore I return null\n\n");
		
		return null;
	}

	@Override
	public int insertAuthor(Author author) {
		System.out.println("\n\nauthorDao.insertAuthor\n\n");
		int id = (int) getSession().save(author);
		System.out.println("\n\n authorDao,´.insertAuthor nach dem save \n\n id = " + id);
		System.out.println("\nNochmal den Author checken\n" + author.toString());
		
		// Hier ne Abfrage machen, um zu sehen, ob der in der Datenbank ist
		//Author fromDB = getAuthorByID(id);
		//System.out.println("\n\n author nochmal aus DB geholt\n" + fromDB.toString() );
		return id;
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
