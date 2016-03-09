package appl.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
		userService.createAccount(getNewUser("philipp"));
		userService.createAccount(getNewUser("sigmar"));
		userService.createAccount(getNewUser("fin"));
		userService.createAccount(getNewUser("lena"));
		userService.createAccount(getNewUser("martina"));
	}

	private Map<Userfields, String> getNewUser(String name) {
		Map<Userfields, String> data = new HashMap<>();
		data.put(Userfields.name, name.substring(0, 1).toUpperCase() + name.substring(1));
		data.put(Userfields.surname, chooseSurname());
		data.put(Userfields.email, name + "@ky.de");
		data.put(Userfields.street, chooseStreet());
		data.put(Userfields.streetnumber, "" + new Random().nextInt(100));
		data.put(Userfields.plzId, "" + new Random().nextInt(200));
		data.put(Userfields.password, name);
		data.put(Userfields.role, UserRoles.USER.toString());
		return data;
	}

	private String chooseSurname() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Ost");
		list.add("Thormann");
		list.add("Rosenhagen");
		list.add("Trepesch");
		list.add("Boosz");
		list.add("Plank");
		list.add("Büchner");
		list.add("Unicorn");
		list.add("Unicorn");
		list.add("Arendt");
		list.add("Brandt");
		list.add("Liebknecht");
		list.add("Noske");

		Random generator = new Random();
		return list.get(generator.nextInt(list.size()));
	}

	private String chooseStreet() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("An der Weberei");
		list.add("Star Destroyer");
		list.add("Baker Street");
		list.add("ISS");
		list.add("Einhornstraße");
		list.add("Friedrich-Ebert-Platz");
		list.add("Platz des Sozialismus");
		list.add("Scheidemannstraße");

		Random generator = new Random();
		return list.get(generator.nextInt(list.size()));
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
