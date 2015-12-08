package appl.data.dao;

import java.util.List;
import java.util.Map;

import appl.data.enums.Searchfields;
import appl.data.items.User;

public interface UserDAO {

	User getUsers();

	List<User> getUsersByName();

	List<User> getUsersBySurname();

	List<User> getUserByNickname();

	List<User> getUserByEMail();

	boolean insertUser(User user);

	boolean deleteUser(User user);

	boolean updateUser(User user, Map<Searchfields, String> map);

}
