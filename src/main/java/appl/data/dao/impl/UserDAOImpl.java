package appl.data.dao.impl;

import java.util.List;
import java.util.Map;

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
	public List<User> getUserByNickname(String nickname) {
		// TODO implement this!
		return null;
	}

	@Override
	public List<User> getUserByEMail(String email) {
		// TODO implement this!
		return null;
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
