package appl.logic.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import appl.data.builder.BuilderFactory;
import appl.data.builder.UserBuilder;
import appl.data.dao.PlzDAO;
import appl.data.dao.UserDAO;
import appl.data.items.Book;
import appl.data.items.PLZ;
import appl.data.items.User;
import appl.data.items.UserBookStatistic;
import appl.enums.UserRoles;
import appl.enums.Userfields;
import appl.logic.service.BookService;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import exceptions.data.ErrorMessageHelper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private PlzDAO plzDAO;

	@Autowired
	private BookService bookService;

	@Autowired
	private PasswordEncoder pswEncoder;

	@Autowired
	private BuilderFactory builderFactory;

	private UserBuilder getUserBuilder() {
		return builderFactory.getUserBuilder();
	}

	@Override
	public int createAccount(Map<Userfields, String> data, byte[] image) throws DatabaseException {
		return createAccount(getUserBuilder().setImage(image), data);
	}

	@Override
	public int createAccount(Map<Userfields, String> data) throws DatabaseException {
		return createAccount(getUserBuilder(), data);
	}

	private int createAccount(UserBuilder userBuilder, Map<Userfields, String> data) throws DatabaseException {
		userBuilder.setRole(UserRoles.USER);
		for (Entry<Userfields, String> entry : data.entrySet()) {
			readData(userBuilder, entry.getKey(), entry.getValue());
		}
		try {
			return userDao.insertUser(userBuilder.createUser());
		} catch (Exception e) {
			throw new DatabaseException(ErrorMessageHelper.couldNotBeSaved("User") + e.getMessage());
		}
	}

	@Override
	public boolean updateAccount(int userId, Map<Userfields, String> data) throws DatabaseException {
		return updateAccount(userId, data, Optional.empty());
	}

	@Override
	public boolean updateAccount(int userId, Map<Userfields, String> data, byte[] image) throws DatabaseException {
		if (image == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("image"));
		}
		return updateAccount(userId, data, Optional.ofNullable(image));
	}

	private boolean updateAccount(int userId, Map<Userfields, String> data, Optional<byte[]> image)
			throws DatabaseException {
		User user = findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.removeError("User",
				String.valueOf(userId), ErrorMessageHelper.entityDoesNotExist("User"))));
		UserBuilder userBuilder = saveOldValues(user, getUserBuilder());

		if (image.isPresent()) {
			userBuilder.setImage(image.orElse(null));
		}

		System.out.println("Map-Größe: " + data.size());
		System.out.println(data.toString());
		for (Entry<Userfields, String> entry : data.entrySet()) {
			System.out.println("Update: " + entry.getKey() + ": " + entry.getValue());
			readData(userBuilder, entry.getKey(), entry.getValue());
		}
		System.out.println("Rolle: " + userBuilder.getRole());
		return userDao.updateUser(userBuilder.createUser());
	}

	private UserBuilder saveOldValues(User user, UserBuilder userBuilder) {
		return userBuilder.setEmail(user.getEmail()).setImage(user.getImage()).setName(user.getName())
				.setPassword(user.getPassword()).setPLZ(user.getPlz())
				.setRole(UserRoles.ADMIN.toString().equals(user.getRole()) ? UserRoles.ADMIN : UserRoles.USER)
				.setStreet(user.getStreet()).setStreetnumber(user.getStreetnumber()).setSurname(user.getSurname())
				.setId(user.getUserId());
	}

	@Override
	public boolean deleteAccount(int userId) throws DatabaseException {
		User user = findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.removeError("User",
				String.valueOf(userId), ErrorMessageHelper.entityDoesNotExist("User"))));
		return userDao.deleteUser(user);
	}

	@Override
	public Optional<User> findbyMail(String email) throws DatabaseException {
		return userDao.getUserByUniqueField(Userfields.email, email);
	}

	@Override
	public Optional<User> findByID(int id) throws DatabaseException {
		return userDao.getUserByUniqueField(Userfields.userId, String.valueOf(id));
	}

	@Override
	public List<User> getUsers() throws DatabaseException {
		return userDao.getUsers();
	}

	@Override
	public int getNumberOf(UserRoles role) throws DatabaseException {
		Map<Userfields, String> map = new HashMap<Userfields, String>();
		map.put(Userfields.role, role.toString());
		return userDao.getUserByMetadata(map).size();
	}

	@Override
	public List<UserBookStatistic> getUserBookStatistics(int userId) throws DatabaseException {
		return userDao.getUserBookStatistics(userId);
	}

	@Override
	public boolean updateUserBookStatistic(int userId, String isbn, Calendar date) throws DatabaseException {
		Book book = bookService.getBookByIsbn(isbn);
		User user = findByID(userId)
				.orElseThrow(() -> new DatabaseException(ErrorMessageHelper.updateError("UserBookStatistic",
						String.valueOf(userId), ErrorMessageHelper.entityDoesNotExist("User"))));
		return userDao.updateUserBookStatistic(user, book, date);
	}

	// TODO In PLZ-Service/-DAO verschieben?
	@Override
	public List<PLZ> getPLZs(String postalCode) throws DatabaseException {
		return plzDAO.getPLZByPostalCode(postalCode);
	}

	private Optional<PLZ> getPLZ(int plzId) throws DatabaseException {
		return plzDAO.getPLZ(plzId);
	}

	private UserBuilder readData(UserBuilder userBuilder, Userfields userfield, String information)
			throws DatabaseException {
		switch (userfield) {
		case role:
			userBuilder.setRole(UserRoles.ADMIN.toString().equals(information) ? UserRoles.ADMIN : UserRoles.USER);
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
			System.out.println("Im Service angekommenes Passwort: " + information);
			String psw = pswEncoder.encode(information);
			System.out.println("Verschlüsseltes Passwort: " + psw);
			userBuilder.setPassword(psw);
			break;
		case plzId:
			PLZ plz = getPLZ(Integer.parseInt(information))
					.orElseThrow(() -> new DatabaseException(ErrorMessageHelper.entityDoesNotExist("PLZ")));
			userBuilder.setPLZ(plz);
		default:
			break;
		}
		return userBuilder;
	}

}
