package appl.data.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import appl.data.dao.ArchiveDAO;
import appl.data.items.ArchiveBook;

public class ArchiveDAOImpl implements ArchiveDAO {

	public ArchiveDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void update(ArchiveBook archiveItem) {
		getSession().update(archiveItem);
		
	}

}
