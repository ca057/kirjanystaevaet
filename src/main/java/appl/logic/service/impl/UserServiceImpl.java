package appl.logic.service.impl;

import java.util.List;
import java.util.Map;

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
import exceptions.data.ErrorMessageHelper;
import exceptions.data.PrimaryKeyViolation;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;

	@Autowired
	UserBuilder userBuilder;

	@Autowired
	PasswordEncoder pswEncoder;

	@Override
	public int createAccount(Map<Userfields, String> data, PLZ plz) throws PrimaryKeyViolation {
		userBuilder.setRole(UserRoles.USER);
		userBuilder.setPLZ(plz);
		return createAccount(data);
	}

	@Override
	public int createAccount(Map<Userfields, String> data) throws PrimaryKeyViolation {
		data.forEach((userfield, information) -> {
			getData(userfield, information);
		});
		try {
			return userDao.insertUser(userBuilder.createUser());
		} catch (Exception e) {
			throw new PrimaryKeyViolation(ErrorMessageHelper.couldNotBeSaved("User") + e.getMessage());
		}
	}

	@Override
	public User findbyMail(String eMail) {
		return userDao.getUserByEMail(eMail);
	}

	@Override
	public User findByID(int id) {
		return userDao.getUserByID(id);
	}

	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	private UserBuilder getData(Userfields userfield, String information) {
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
