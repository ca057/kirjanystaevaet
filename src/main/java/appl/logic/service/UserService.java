
package appl.logic.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import appl.data.enums.Userfields;
import appl.data.items.PLZ;
import appl.data.items.User;
import exceptions.data.DatabaseException;

/**
 * An UserService manages all modifications on an user object. Therefore, it is
 * responsible for the communication to DAOs.
 * 
 * @author Johannes
 * 
 */
@Service
public interface UserService {

	/**
	 * Method to save a new user without postal code.
	 * 
	 * For further information on the fields available and/or necessary, see the
	 * documentation of {@link User} or the available {@link Userfields}.
	 * 
	 * @param data
	 *            A map with the field name as key and the belonging information
	 *            as value
	 * @return the {@code id} of the user
	 * @throws DatabaseException
	 * @throws PrimaryKeyViolation
	 * 
	 * @see {@link Userfields}
	 * @see {@link User}
	 */
	int createAccount(Map<Userfields, String> data) throws DatabaseException;

	/**
	 * Method to save a new user with postal code.
	 * 
	 * For further information on the fields available and/or necessary, see the
	 * documentation of {@link User} or the available {@link Userfields}.
	 * 
	 * @param data
	 *            A map with the field name as key and the belonging information
	 *            as value
	 * @param plz
	 *            the postal code of the user's address as {@link PLZ} object
	 * @return the {@code id} of the user
	 * @throws DatabaseException
	 * @throws PrimaryKeyViolation
	 * 
	 * @see {@link PLZ}
	 * @see {@link Userfields}
	 * @see {@link User}
	 */
	int createAccount(Map<Userfields, String> data, PLZ plz) throws DatabaseException;

	/**
	 * @param eMail
	 *            the email address of the user
	 * @return the user if existing
	 * @throws DatabaseException
	 */
	User findbyMail(String eMail) throws DatabaseException;

	/**
	 * @param id
	 *            the id of the user
	 * @return the user if existing
	 * @throws DatabaseException
	 */
	User findByID(int id) throws DatabaseException;

	/**
	 * @return A {@code list} with all users saved
	 */
	List<User> getUsers();

}
