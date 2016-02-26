package appl.logic.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import appl.data.builder.UserBuilder;
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
	private BookService bookService;

	@Autowired
	private PasswordEncoder pswEncoder;

	@Autowired
	private BeanFactory beanFactory;

	private UserBuilder getUserBuilder() {
		return beanFactory.getBean(UserBuilder.class);
	}

	@Override
	public int createAccount(Map<Userfields, String> data, PLZ plz) throws DatabaseException {
		UserBuilder userBuilder = getUserBuilder().setPLZ(plz);
		return createAccount(userBuilder, data);
	}

	@Override
	public int createAccount(Map<Userfields, String> data) throws DatabaseException {
		UserBuilder userBuilder = getUserBuilder();
		return createAccount(userBuilder, data);
	}

	private int createAccount(UserBuilder userBuilder, Map<Userfields, String> data) throws DatabaseException {
		userBuilder.setRole(UserRoles.USER);
		data.forEach((userfield, information) -> {
			readData(userBuilder, userfield, information);
		});
		try {
			return userDao.insertUser(userBuilder.createUser());
		} catch (Exception e) {
			throw new DatabaseException(ErrorMessageHelper.couldNotBeSaved("User") + e.getMessage());
		}
	}

	@Override
	public boolean updateAccount(int userId, Map<Userfields, String> map) throws DatabaseException {
		User user = findByID(userId).orElseThrow(() -> new DatabaseException(ErrorMessageHelper.removeError("User",
				String.valueOf(userId), ErrorMessageHelper.entityDoesNotExist("User"))));
		UserBuilder userBuilder = getUserBuilder();
		map.forEach((userfield, information) -> {
			readData(userBuilder, userfield, information);
		});
		return userDao.updateUser(userId, user);
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

	private UserBuilder readData(UserBuilder userBuilder, Userfields userfield, String information) {
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
