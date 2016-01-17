package appl.logic.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import appl.data.builder.UserBuilder;
import appl.data.dao.UserDAO;
import appl.data.enums.Userfields;
import appl.logic.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;

	@Autowired
	UserBuilder userBuilder;

	@Autowired
	PasswordEncoder pswEncoder;

	@Override
	public int registerNewUserAccount(Map<Userfields, String> data) {
		data.forEach((userfield, string) -> {
			switch ((Userfields) userfield) {

			}
		});
		String psw = pswEncoder.encode("");
		return 0;
	}

	private UserBuilder getData() {
		return userBuilder;

	}

}
