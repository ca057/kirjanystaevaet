package appl.data.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.ArchiveDAO;
import appl.data.items.ArchiveBook;

@Repository
public class ArchiveDAOImpl implements ArchiveDAO {

	public ArchiveDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
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
		Criteria cr = s.createCriteria(ArchiveBook.class);
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return cr;
		//return cr.createAlias("books", "b").createAlias("authors", "a");
		//return cr.createAlias("books", "b"); // Category hat keinen Author -> kein Alias dafür angeben
	}
	@Override
	public void update(ArchiveBook archiveItem) {
		getSession().update(archiveItem);
		
	}
	@Override
	public int insert(ArchiveBook archiveItem){
		int id = (int) getSession().save(archiveItem);
		return id;
	}

}
