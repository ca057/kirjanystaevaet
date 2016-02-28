
package appl.logic.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import appl.data.enums.Userfields;
import appl.data.items.Book;
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
	 * @param image
	 *            the image of the user as {@code byte[]}
	 * @return the {@code id} of the user
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 * 
	 * @see {@link Userfields}
	 * @see {@link User}
	 * @see {@link PLZ}
	 */
	public int createAccount(Map<Userfields, String> data, byte[] image) throws DatabaseException;

	/**
	 * Method to save a new user without postal code.
	 * 
	 * For further information on the fields available and/or necessary, see the
	 * documentation of {@link User} or the available {@link Userfields}.
	 * 
	 * @param data
	 *            A map with the field name as key and the belonging information
	 *            as value
	 * @param plz
	 *            the {@link PLZ} of the user
	 * @param image
	 *            the image of the user as {@code byte[]}
	 * @return the {@code id} of the user
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 * 
	 * @see {@link Userfields}
	 * @see {@link User}
	 * @see {@link PLZ}
	 */
	public int createAccount(Map<Userfields, String> data, PLZ plz, byte[] image) throws DatabaseException;

	/**
	 * Method to save a new user without postal code.
	 * 
	 * For further information on the fields available and/or necessary, see the
	 * documentation of {@link User} or the available {@link Userfields}.
	 * 
	 * @param data
	 *            A map with the field name as key and the belonging information
	 *            as value
	 * @param plz
	 *            the {@link PLZ} of the user
	 * @return the {@code id} of the user
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 * 
	 * @see {@link Userfields}
	 * @see {@link User}
	 * @see {@link PLZ}
	 */
	public int createAccount(Map<Userfields, String> data, PLZ plz) throws DatabaseException;

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
	 *             if an error occurs while interacting with the underlying DAO
	 * 
	 * @see {@link Userfields}
	 * @see {@link User}
	 */
	public int createAccount(Map<Userfields, String> data) throws DatabaseException;

	/**
	 * @param userId
	 *            the id of the user who is to be updated
	 * @param map
	 *            a map containing the {@link Userfields} as key and updated
	 *            value as {@link String}.
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 *             or if the user cannot be found
	 */
	public boolean updateAccount(int userId, Map<Userfields, String> map) throws DatabaseException;

	/**
	 * @param userId
	 *            the id of the user who is to be updated
	 * @param map
	 *            a map containing the {@link Userfields} as key and updated
	 *            value as {@link String}.
	 * @param plz
	 *            the new place of the user as {@link PLZ}
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 *             or if the user cannot be found
	 */
	public boolean updateAccount(int userId, Map<Userfields, String> map, PLZ plz) throws DatabaseException;

	/**
	 * @param userId
	 *            the id of the user who is to be updated
	 * @param map
	 *            a map containing the {@link Userfields} as key and updated
	 *            value as {@link String}.
	 * @param plz
	 *            the new place of the user as {@link PLZ}
	 * @param image
	 *            the new image of the user as {@code byte[]}
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 *             or if the user cannot be found
	 */
	public boolean updateAccount(int userId, Map<Userfields, String> map, PLZ plz, byte[] image)
			throws DatabaseException;

	/**
	 * @param userId
	 *            the id of the user who is to be updated
	 * @param map
	 *            a map containing the {@link Userfields} as key and updated
	 *            value as {@link String}.
	 * @param image
	 *            the new image of the user as {@code byte[]}
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 *             or if the user cannot be found
	 */
	public boolean updateAccount(int userId, Map<Userfields, String> map, byte[] image) throws DatabaseException;

	/**
	 * Deletes the user with an specific {@code id}.
	 * 
	 * @param userId
	 *            the {@code id} of the user who is to be removed from the
	 *            database
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 *             or if the user cannot be found
	 */
	public boolean deleteAccount(int userId) throws DatabaseException;

	/**
	 * Method to find an user by their specific email address.
	 * 
	 * @param email
	 *            the email address of the user
	 * @return the user if existing
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public Optional<User> findbyMail(String email) throws DatabaseException;

	/**
	 * Method to find an user by their specific id.
	 * 
	 * @param id
	 *            the id of the user
	 * @return an {@link Optional} containing the user (if existing)
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public Optional<User> findByID(int id) throws DatabaseException;

	/**
	 * Method to get a {@code list} with all users of the database. No
	 * distinction between different roles of these users is made.
	 * 
	 * @return A {@code list} with all users stored in the database
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public List<User> getUsers() throws DatabaseException;

	/**
	 * Lists the books an user was interested in.
	 * 
	 * @param userId
	 *            the {@code id} of the user
	 * @return a list with the seen books
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public List<Book> getVisitedBooks(int userId) throws DatabaseException;

	/**
	 * Updates the list of the recently seen books.
	 * 
	 * @param userId
	 *            the id of the user
	 * @param isbn
	 *            the ISBN of the last seen book
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public boolean updateVisitedBooks(int userId, String isbn) throws DatabaseException;

	/**
	 * Checks which objects of {@link PLZ} contain a specific postal code and
	 * returns the result as a {@link list}.
	 * 
	 * @param postalCode
	 *            the postal code inserted
	 * @return a {link list} with all {link PLZ}s in question
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public List<PLZ> getPLZs(String postalCode) throws DatabaseException;

	/**
	 * Getter to find a {@link PLZ} object by its {@code id} in the database.
	 * 
	 * @param plzId
	 *            the id of the {@link PLZ} object
	 * @return an optional with the resulting {@code PLZ} if existent
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public Optional<PLZ> getPLZ(int plzId) throws DatabaseException;
}
