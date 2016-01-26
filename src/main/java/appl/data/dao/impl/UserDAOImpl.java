package appl.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.UserDAO;
import appl.data.enums.Searchfields;
import appl.data.enums.Userfields;
import appl.data.items.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Criteria setupAndGetCriteria() {
		if (sessionFactory == null) {
			throw new RuntimeException("[Error] SessionFactory is null");
		}
		return getSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.createAlias("plz", "p");
	}

	@Override
	public List<User> getUsers() {
		return getSession().createCriteria(User.class).list();
	}

	@Override
	public List<User> getUsersByName(String name) {
		// TODO implement this!
		return null;
	}

	@Override
	public List<User> getUsersBySurname(String surname) {
		// TODO implement this!
		return null;
	}

	@Override
	public User getUserByEMail(String email) {
		// TODO Passt das?
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq(Userfields.email.toString(), email));
		// FIXME Criteria wieder einführen
		// User user = (User) getSession().createQuery("from User where email
		// ='" + email + "'").uniqueResult();
		// System.out.println("\n\n\n\nUser-Inhalt:" +
		// getSession().createCriteria(User.class) + "\n\n\n\n\n");
		User user = (User) cr.uniqueResult();
		if (user == null) {
			System.err.println("no user found with this email: " + email);
		}
		return user;
	}

	@Override
	public int insertUser(User user) {
		return (Integer) getSession().save(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO implement this!
	}

	@Override
	public void updateUser(User user, Map<Searchfields, String> map) {
		// TODO update(USER)? Eine Ebene drüber müsste das
		// jeweilige zu ändernde Feld via USER.set() geändert werden
	}

	@Override
	public User getUserByID(int id) {
		// Criteria cr = setupAndGetCriteria();
		// cr.add(Restrictions.idEq(id));
		// System.out.println(cr);
		// User user = (User) cr.uniqueResult();
		User user = (User) getSession().createQuery("from User where userId ='" + id + "'").uniqueResult();
		if (user == null) {
			System.err.println("no user found with this ID: " + id);
		}
		return user;
	}

}
