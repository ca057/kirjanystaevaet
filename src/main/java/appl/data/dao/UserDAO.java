package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.items.User;

public interface UserDAO {

	@Transactional
	List<User> getUsers();

	@Transactional
	List<User> getUsersByName(String name);

	@Transactional
	List<User> getUsersBySurname(String surname);

	@Transactional
	List<User> getUserByNickname(String nickname);

	@Transactional
	List<User> getUserByEMail(String email);

	@Transactional
	boolean insertUser(User user);

	@Transactional
	boolean deleteUser(User user);

	@Transactional
	boolean updateUser(User user, Map<Searchfields, String> map);

}
