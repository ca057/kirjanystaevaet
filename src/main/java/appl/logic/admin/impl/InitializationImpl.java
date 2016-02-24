package appl.logic.admin.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.data.enums.UserRoles;
import appl.data.enums.Userfields;
import appl.logic.admin.Initialization;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;

/**
 * @author Johannes
 *
 */
@Component
public class InitializationImpl implements Initialization, InitializingBean {

	@Autowired
	UserService userService;

	public InitializationImpl() {
	}

	@Override
	public void afterPropertiesSet() {
		try {
			userService.findbyMail("admin@ky.de");

		} catch (DatabaseException e) {
			System.err.println("No admin available. Creating default accounts (" + e.getMessage() + ")");
			try {
				createAdmin();
				createUser();
			} catch (DatabaseException e1) {
				System.err.println("Problem while adding default accounts: " + e.getMessage());
			}
		}
	}

	private void createUser() throws DatabaseException {
		Map<Userfields, String> data = new HashMap<>();
		data.put(Userfields.name, "user");
		data.put(Userfields.surname, "user");
		data.put(Userfields.email, "user@ky.de");
		data.put(Userfields.password, "user");
		data.put(Userfields.role, UserRoles.USER.toString());
		userService.createAccount(data, null);
	}

	private void createAdmin() throws DatabaseException {
		System.out.println("createAdmin");
		Map<Userfields, String> data = new HashMap<>();
		data.put(Userfields.name, "admin");
		data.put(Userfields.surname, "admin");
		data.put(Userfields.email, "admin@ky.de");
		data.put(Userfields.password, "admin");
		data.put(Userfields.role, UserRoles.ADMIN.toString());
		userService.createAccount(data, null);
	}

}
