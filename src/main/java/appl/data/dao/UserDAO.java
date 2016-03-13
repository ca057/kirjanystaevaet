package appl.data.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Book;
import appl.data.items.User;
import appl.data.items.UserBookStatistic;
import appl.enums.Userfields;
import exceptions.data.DatabaseException;

/**
 * Interface for a Data Access Object. Manages all access to the database
 * connected with the {@link User} entity.
 * 
 * @author Johannes
 *
 */
@Transactional
public interface UserDAO {

	/**
	 * Use this method to get all users stored in the database. No distinction
	 * between different roles (e.g. user and admin) is made.
	 * 
	 * @return a {@link List} with all users stored in the database. If no users
	 *         are found, an empty list is returned.
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database
	 */
	List<User> getUsers() throws DatabaseException;

	/**
	 * This method provides the possibility to search users by different fields.
	 * It is possible to limit the result by filtering all available
	 * {@link Userfields}.
	 * 
	 * @param map
	 *            a map containing the {@link Userfields} as key and the search
	 *            term as {@link String}.
	 * @return a {@link List} with all relevant users. If no users are found, an
	 *         empty list is returned.
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database
	 * 
	 * @see {@link Userfields}
	 */
	List<User> getUserByMetadata(Map<Userfields, String> map) throws DatabaseException;

	/**
	 * This method can be used when a specific user is wanted. The scanned
	 * database column has to contain unique values. Therefore, please check the
	 * used entity to see which fields are permitted to hand over as parameter.
	 * 
	 * @param field
	 *            the name of the database column as {@link Userfields}
	 * @param value
	 *            the value to filter {@code field} with
	 * @return an {@link Optional} containing the user (if found)
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database
	 * 
	 * @see {@link Userfields}
	 * @see {@link User}
	 */
	Optional<User> getUserByUniqueField(Userfields field, String value) throws DatabaseException;

	/**
	 * Stores an user in the database.
	 * 
	 * Please check if all fields which are not nullable are provided. If this
	 * is not the case, it is very likely that an {@code exception} is thrown by
	 * the database.
	 * 
	 * Password encryption is supposed to happen in a service.
	 * 
	 * @param user
	 *            the user who is to be stored in the database
	 * @return the allocated {@code id} of the user
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database
	 */
	int insertUser(User user) throws DatabaseException;

	/**
	 * Deletes the user handed over as parameter.
	 * 
	 * @param user
	 *            the user who is to be deleted
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database or if the
	 *             user cannot be found
	 */
	boolean deleteUser(User user) throws DatabaseException;

	/**
	 * Updates the user with id = {@code userId} with the values stored in
	 * {@code user}
	 * 
	 * @param userId
	 *            the {@code id} of the stored user
	 * @param user
	 *            the values which are to be updated
	 * @return true if successful
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database or if the
	 *             user cannot be found
	 */
	boolean updateUser(User user) throws DatabaseException;

	/**
	 * Lists the statistics of all {@link Book}s a specific {@link User} has
	 * seen.
	 * 
	 * @param userId
	 *            the {@code id} of the user
	 * @return a list with the statistics
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database or if the
	 *             user cannot be found
	 */
	List<UserBookStatistic> getUserBookStatistics(int userId) throws DatabaseException;

	/**
	 * Updates the statistics concerning one specific {@link Book} and one
	 * specific {@link User}.
	 * 
	 * @param userId
	 *            the id of the user
	 * @param book
	 *            the last seen {@link Book}
	 * @param date
	 *            the time the book was last visited by the {@code user}
	 * @return true if successful or if the book is already in the list
	 * @throws DatabaseException
	 *             if an error occurs while accessing the database or if the
	 *             user cannot be found
	 */
	boolean updateUserBookStatistic(User user, Book book, Calendar date) throws DatabaseException;
}
