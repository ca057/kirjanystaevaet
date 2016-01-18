package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.items.User;

@Transactional
public interface UserDAO {

	List<User> getUsers();

	List<User> getUsersByName(String name);

	List<User> getUsersBySurname(String surname);

	User getUserByEMail(String email);

	void insertUser(User user);

	void deleteUser(User user);

	void updateUser(User user, Map<Searchfields, String> map);

}
