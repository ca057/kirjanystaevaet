package appl.logic.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import appl.data.builder.UserBuilder;
import appl.data.enums.User;
import appl.logic.service.UserService;

public class UserServiceImpl implements UserService {

	// @Autowired
	// UserDAO userDao;
	//
	// @Autowired
	//
	//
	// @Autowired
	// PasswordEncoder pswEncoder;
	@Autowired
	UserBuilder userBuilder;

	@Override
	public int registerNewUserAccount(Map<User, String> data) {

		// appl.data.items.User user = new appl.data.items.User(nickname, name,
		// surname, email, street, streetnumber, plz)
		return 0;
	}

}
