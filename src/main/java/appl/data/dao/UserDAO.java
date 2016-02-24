package appl.data.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.enums.Userfields;
import appl.data.items.User;
import exceptions.data.DatabaseException;

@Transactional
public interface UserDAO {

	List<User> getUsers() throws DatabaseException;

	List<User> getUserByMetadata(Map<Userfields, String> map) throws DatabaseException;

	Optional<User> getUserByEMail(String email) throws DatabaseException;

	Optional<User> getUserByID(int id) throws DatabaseException;

	/**
	 * Password encryption is supposed to happen in a service.
	 * 
	 * @param user
	 * @return
	 * @throws PrimaryKeyViolation
	 */
	int insertUser(User user) throws DatabaseException;

	void deleteUser(User user) throws DatabaseException;

	void updateUser(User user, Map<Searchfields, String> map) throws DatabaseException;

}
