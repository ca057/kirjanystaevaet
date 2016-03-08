
package appl.logic.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import appl.data.items.PLZ;
import appl.data.items.User;
import appl.data.items.UserBookStatistic;
import appl.enums.UserRoles;
import appl.enums.Userfields;
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
	 * Counts the accounts with the specified authority.
	 * 
	 * @param role
	 *            the {@link UserRoles}
	 * @return the amount of {@link User}s with the specified authority.
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	public int getNumberOfAccounts(UserRoles role) throws DatabaseException;

	public int getNumberOfAccounts() throws DatabaseException;

	/**
	 * Lists the books an user was interested in.
	 * 
	 * @param userId
	 *            the {@code id} of the user
	 * @return a list with the seen books
	 * @throws DatabaseException
	 *             if an error occurs while interacting with the underlying DAO
	 */
	// TODO Update Javadoc
	public List<UserBookStatistic> getUserBookStatistics(int userId) throws DatabaseException;

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
	boolean updateUserBookStatistic(int userId, String isbn, Calendar date) throws DatabaseException;

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

}
