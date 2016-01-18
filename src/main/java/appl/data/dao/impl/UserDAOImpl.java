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
		Session s = getSession();
		Criteria cr = s.createCriteria(User.class);
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return cr.createAlias("plz", "p");
	}

	@Override
	public List<User> getUsers() {
		// TODO implement this!
		return null;
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
		Criteria cr = setupAndGetCriteria().add(Restrictions.ilike(Userfields.email.toString(), email));
		return (User) cr.uniqueResult();
	}

	@Override
	public void insertUser(User user) {
		// TODO implement this!
	}

	@Override
	public void deleteUser(User user) {
		// TODO implement this!
	}

	@Override
	public void updateUser(User user, Map<Searchfields, String> map) {
		// TODO update(user)? Eine Ebene drüber müsste das
		// jeweilige zu ändernde Feld via user.set() geändert werden
	}

}
