package appl.data.dao.impl;

import java.util.List;
import java.util.Map;

import appl.data.dao.UserDAO;
import appl.data.enums.Searchfields;
import appl.data.items.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersBySurname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserByNickname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserByEMail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user, Map<Searchfields, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

}
