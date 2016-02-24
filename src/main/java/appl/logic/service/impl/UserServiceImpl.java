package appl.logic.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import appl.data.builder.UserBuilder;
import appl.data.dao.UserDAO;
import appl.data.enums.UserRoles;
import appl.data.enums.Userfields;
import appl.data.items.PLZ;
import appl.data.items.User;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import exceptions.data.ErrorMessageHelper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;

	@Autowired
	UserBuilder userBuilder;

	@Autowired
	PasswordEncoder pswEncoder;

	@Override

	public int createAccount(Map<Userfields, String> data, PLZ plz) throws DatabaseException {
		userBuilder.setPLZ(plz);
		return createAccount(data);
	}

	@Override
	public int createAccount(Map<Userfields, String> data) throws DatabaseException {
		userBuilder.setRole(UserRoles.USER);
		data.forEach((userfield, information) -> {
			readData(userfield, information);
		});
		try {
			return userDao.insertUser(userBuilder.createUser());
		} catch (Exception e) {
			throw new DatabaseException(ErrorMessageHelper.couldNotBeSaved("User") + e.getMessage());
		}
	}

	@Override
	public boolean updateAccount(int userId, Map<Userfields, String> map) throws DatabaseException {
		User user = findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.removeError("User",
				String.valueOf(userId), ErrorMessageHelper.entityDoesNotExist("User"))));
		try {
			userDao.deleteUser(user);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean deleteAccount(int userId) throws DatabaseException {
		User user = findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.removeError("User",
				String.valueOf(userId), ErrorMessageHelper.entityDoesNotExist("User"))));
		// TODO Implement this.
		return false;
	}

	@Override
	public Optional<User> findbyMail(String eMail) throws DatabaseException {
		return userDao.getUserByEMail(eMail);
	}

	@Override
	public Optional<User> findByID(int id) throws DatabaseException {
		return userDao.getUserByID(id);
	}

	@Override
	public List<User> getUsers() throws DatabaseException {
		return userDao.getUsers();
	}

	private UserBuilder readData(Userfields userfield, String information) {
		switch (userfield) {
		case role:
			if (UserRoles.ADMIN.toString().equals(information)) {
				System.out.println("Ein Admin wurde angelegt");
				userBuilder.setRole(UserRoles.ADMIN);
			}
			break;
		case name:
			userBuilder.setName(information);
			break;
		case surname:
			userBuilder.setSurname(information);
			break;
		case email:
			userBuilder.setEmail(information);
			break;
		case street:
			userBuilder.setStreet(information);
			break;
		case streetnumber:
			userBuilder.setStreetnumber(information);
			break;
		case password:
			userBuilder.setPassword(pswEncoder.encode(information));
			break;
		default:
			break;
		}
		return userBuilder;
	}

}
