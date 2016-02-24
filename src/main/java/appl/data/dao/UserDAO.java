package appl.data.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.enums.Userfields;
import appl.data.items.User;
import exceptions.data.PrimaryKeyViolationException;

@Transactional
public interface UserDAO {

	List<User> getUsers();

	List<User> getUserByMetadata(Map<Userfields, String> map);

	Optional<User> getUserByEMail(String email);

	Optional<User> getUserByID(int id);

	/**
	 * Password encryption is supposed to happen in a service.
	 * 
	 * @param user
	 * @return
	 * @throws PrimaryKeyViolation
	 */
	int insertUser(User user) throws PrimaryKeyViolationException;

	void deleteUser(User user);

	void updateUser(User user, Map<Searchfields, String> map);

}
