package appl.logic.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import appl.data.builder.UserBuilder;
import appl.data.dao.UserDAO;
import appl.data.enums.UserRoles;
import appl.data.enums.Userfields;
import appl.data.items.User;
import appl.logic.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;

	@Autowired
	UserBuilder userBuilder;

	@Autowired
	PasswordEncoder pswEncoder;

	@Override
	public int registerNewUserAccount(Map<Userfields, String> data) {
		data.forEach((userfield, information) -> {
			userBuilder = getData(userfield, information);
		});
		return 0;
	}

	private UserBuilder getData(Userfields userfield, String information) {
		switch (userfield) {
		case role:
			if (UserRoles.admin.toString().equals(information)) {
				userBuilder.setRole(UserRoles.admin);
			} else {
				userBuilder.setRole(UserRoles.user);
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
		case plz:
			// TODO implement this
			break;
		case password:
			userBuilder.setPassword(pswEncoder.encode(information));
			break;
		}
		return userBuilder;
	}

	@Override
	public User findbyMail(String eMail) {
		// TODO rausnehmen
		// userDao.insertUser(new
		// UserBuilderImpl().setName("admin").setSurname("admin").setEmail("a@b.de")
		// .setRole(UserRoles.admin).setPassword("admin").createUser());
		return userDao.getUserByEMail(eMail);
	}

}
