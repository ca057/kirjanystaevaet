package appl.admin.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.enums.UserRoles;
import appl.enums.Userfields;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;

/**
 * @author Johannes
 *
 */
@Component
public class InitializationImpl implements InitializingBean {

	@Autowired
	UserService userService;

	public InitializationImpl() {
	}

	@Override
	public void afterPropertiesSet() {
		try {
			if (!userService.findbyMail("admin@ky.de").isPresent()) {
				createAdmin();
				createUser();
			}
		} catch (DatabaseException e) {
			System.err.println("Problem while adding default accounts: " + e.getMessage());
		}
	}

	private void createUser() throws DatabaseException {
		userService.createAccount(getNewUser("user"));
		userService.createAccount(getNewUser("peter"));
		userService.createAccount(getNewUser("klaus"));
		userService.createAccount(getNewUser("fin"));
		userService.createAccount(getNewUser("lena"));
		userService.createAccount(getNewUser("martina"));
	}

	private Map<Userfields, String> getNewUser(String name) {
		Map<Userfields, String> data = new HashMap<>();
		data.put(Userfields.name, name);
		data.put(Userfields.surname, "Nachname");
		data.put(Userfields.email, name + "@ky.de");
		data.put(Userfields.password, name);
		data.put(Userfields.role, UserRoles.USER.toString());
		return data;
	}

	private void createAdmin() throws DatabaseException {
		System.out.println("createAdmin");
		Map<Userfields, String> data = new HashMap<>();
		data.put(Userfields.name, "admin");
		data.put(Userfields.surname, "admin");
		data.put(Userfields.email, "admin@ky.de");
		data.put(Userfields.password, "admin");
		data.put(Userfields.role, UserRoles.ADMIN.toString());
		userService.createAccount(data);
	}

}
