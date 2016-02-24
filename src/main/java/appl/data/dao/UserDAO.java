package appl.data.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Userfields;
import appl.data.items.User;
import exceptions.data.DatabaseException;

/**
 * @author Johannes
 *
 */
@Transactional
public interface UserDAO {

	/**
	 * @return
	 * @throws DatabaseException
	 */
	List<User> getUsers() throws DatabaseException;

	/**
	 * @param map
	 * @return
	 * @throws DatabaseException
	 */
	List<User> getUserByMetadata(Map<Userfields, String> map) throws DatabaseException;

	/**
	 * @param field
	 * @param value
	 * @return
	 * @throws DatabaseException
	 */
	Optional<User> getUserByUniqueField(Userfields field, String value) throws DatabaseException;

	/**
	 * Password encryption is supposed to happen in a service.
	 * 
	 * @param user
	 * @return
	 * @throws PrimaryKeyViolation
	 */
	int insertUser(User user) throws DatabaseException;

	/**
	 * @param user
	 * @throws DatabaseException
	 */
	boolean deleteUser(User user) throws DatabaseException;

	/**
	 * @param userId
	 * @param user
	 * @param map
	 * @throws DatabaseException
	 */
	boolean updateUser(int userId, User user) throws DatabaseException;

}
