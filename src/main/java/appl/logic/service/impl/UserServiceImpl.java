package appl.logic.service.impl;

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
import appl.data.enums.UserRoles;
import appl.data.enums.Userfields;
import appl.data.items.Book;
import appl.data.items.PLZ;
import appl.data.items.User;
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
		UserBuilder userBuilder = getUserBuilder();
		userBuilder.setImage(image);
		return createAccount(userBuilder, data);
	}

	@Override
	public int createAccount(Map<Userfields, String> data, PLZ plz, byte[] image) throws DatabaseException {
		UserBuilder userBuilder = getUserBuilder();
		userBuilder.setPLZ(plz);
		userBuilder.setImage(image);
		return createAccount(userBuilder, data);
	}

	@Override
	public int createAccount(Map<Userfields, String> data, PLZ plz) throws DatabaseException {
		UserBuilder userBuilder = getUserBuilder();
		userBuilder.setPLZ(plz);
		return createAccount(userBuilder, data);
	}

	@Override
	public int createAccount(Map<Userfields, String> data) throws DatabaseException {
		UserBuilder userBuilder = getUserBuilder();
		return createAccount(userBuilder, data);
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
		return updateAcount(userId, data, Optional.empty(), Optional.empty());
	}

	@Override
	public boolean updateAccount(int userId, Map<Userfields, String> data, PLZ plz) throws DatabaseException {
		return updateAcount(userId, data, Optional.empty(), Optional.empty());
	}

	@Override
	public boolean updateAccount(int userId, Map<Userfields, String> data, PLZ plz, byte[] image)
			throws DatabaseException {
		return updateAcount(userId, data, Optional.ofNullable(plz), Optional.ofNullable(image));
	}

	@Override
	public boolean updateAccount(int userId, Map<Userfields, String> data, byte[] image) throws DatabaseException {
		if (image == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("image"));
		}
		return updateAcount(userId, data, Optional.empty(), Optional.ofNullable(image));
	}

	private boolean updateAcount(int userId, Map<Userfields, String> data, Optional<PLZ> plz, Optional<byte[]> image)
			throws DatabaseException {
		User user = findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.removeError("User",
				String.valueOf(userId), ErrorMessageHelper.entityDoesNotExist("User"))));
		UserBuilder userBuilder = getUserBuilder();
		userBuilder = saveOldValues(user, userBuilder);

		if (image.isPresent()) {
			userBuilder.setImage(image.orElse(null));
		}
		if (plz.isPresent()) {
			userBuilder.setPLZ(plz.orElse(null));
		}

		for (Entry<Userfields, String> entry : data.entrySet()) {
			readData(userBuilder, entry.getKey(), entry.getValue());
		}
		return userDao.updateUser(userBuilder.createUser());
	}

	private UserBuilder saveOldValues(User user, UserBuilder userBuilder) {
		userBuilder.setEmail(user.getEmail());
		userBuilder.setImage(user.getImage());
		userBuilder.setName(user.getName());
		userBuilder.setPassword(user.getPassword());
		userBuilder.setPLZ(user.getPlz());
		userBuilder.setRole(UserRoles.ADMIN.equals(user.getRole()) ? UserRoles.ADMIN : UserRoles.USER);
		userBuilder.setStreet(user.getStreet());
		userBuilder.setStreetnumber(user.getStreetnumber());
		userBuilder.setSurname(user.getSurname());
		userBuilder.setId(user.getUserId());
		return userBuilder;
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
	public List<Book> getVisitedBooks(int userId) throws DatabaseException {
		return userDao.getVisitedBooks(userId);
	}

	@Override
	public boolean updateVisitedBooks(int userId, String isbn) throws DatabaseException {
		Book book = bookService.getBookByIsbn(isbn);
		if (book == null) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Book"));
		}
		return userDao.updateVisitedBooks(userId, book);
	}

	private UserBuilder readData(UserBuilder userBuilder, Userfields userfield, String information)
			throws DatabaseException {
		switch (userfield) {
		case role:
			if (UserRoles.ADMIN.toString().equals(information)) {
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

	@Override
	public List<PLZ> getPLZs(String postalCode) throws DatabaseException {
		return plzDAO.getPLZByPostalCode(postalCode);
	}

	@Override
	public Optional<PLZ> getPLZ(int plzId) throws DatabaseException {
		return plzDAO.getPLZ(plzId);
	}

}
