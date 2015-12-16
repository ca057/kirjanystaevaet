package appl.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.UserDAO;
import appl.data.enums.Searchfields;
import appl.data.items.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	@Override
	public List<User> getUsersByName(String name) {
		return sessionFactory.getCurrentSession().createQuery("from User where name like %" + name + "%").list();
	}

	@Override
	public List<User> getUsersBySurname(String surname) {
		return sessionFactory.getCurrentSession().createQuery("from User where surname like %" + surname + "%").list();
	}

	@Override
	public List<User> getUserByNickname(String nickname) {
		return sessionFactory.getCurrentSession().createQuery("from User where nickname like %" + nickname + "%")
				.list();
	}

	@Override
	public List<User> getUserByEMail(String email) {
		return sessionFactory.getCurrentSession().createQuery("from User where email = %" + email + "%").list();
	}

	@Override
	public boolean insertUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			// TODO Fehlerbehandlung
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (HibernateException e) {
			// TODO Fehlerbehandlung
			return false;
		}
	}

	@Override
	public boolean updateUser(User user, Map<Searchfields, String> map) {
		// TODO checken, ob das so richtig ist. Eine Ebene drüber müsste das
		// jeweilige zu ändernde Feld via user.set() geändert werden
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			// TODO Fehlerbehandlung
			return false;
		}
	}

}
