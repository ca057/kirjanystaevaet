package appl.data.dao;

import java.util.List;
import java.util.Map;

import appl.data.enums.Searchfields;
import appl.data.items.User;

public interface UserDAO {

	List<User> getUsers();

	List<User> getUsersByName(String name);

	List<User> getUsersBySurname(String surname);

	List<User> getUserByNickname(String nickname);

	List<User> getUserByEMail(String email);

	boolean insertUser(User user);

	boolean deleteUser(User user);

	boolean updateUser(User user, Map<Searchfields, String> map);

}
