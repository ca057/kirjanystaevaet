package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.items.User;
import exceptions.data.PrimaryKeyViolation;

@Transactional
public interface UserDAO {

	List<User> getUsers();

	List<User> getUsersByName(String name);

	List<User> getUsersBySurname(String surname);

	User getUserByEMail(String email);

	User getUserByID(int id);

	int insertUser(User user) throws PrimaryKeyViolation;

	void deleteUser(User user);

	void updateUser(User user, Map<Searchfields, String> map);

}
