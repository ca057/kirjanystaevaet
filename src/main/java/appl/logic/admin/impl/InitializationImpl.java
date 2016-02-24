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
import exceptions.data.PrimaryKeyViolation;

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
		// TODO Verbessern.
		try {
			if (userService.findbyMail("admin@ky.de") == null) {
				createAdmin();
				createUser();
			}
		} catch (PrimaryKeyViolation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createUser() {
		Map<Userfields, String> data = new HashMap<>();
		data.put(Userfields.name, "user");
		data.put(Userfields.surname, "user");
		data.put(Userfields.email, "user@ky.de");
		data.put(Userfields.password, "user");
		data.put(Userfields.role, UserRoles.USER.toString());
		try {
			userService.createAccount(data, null);
		} catch (PrimaryKeyViolation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createAdmin() {
		System.out.println("createAdmin");
		Map<Userfields, String> data = new HashMap<>();
		data.put(Userfields.name, "admin");
		data.put(Userfields.surname, "admin");
		data.put(Userfields.email, "admin@ky.de");
		data.put(Userfields.password, "admin");
		data.put(Userfields.role, UserRoles.ADMIN.toString());
		try {
			userService.createAccount(data, null);
		} catch (PrimaryKeyViolation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
