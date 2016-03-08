package web.controllers;

import java.util.Optional;

import appl.data.items.User;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import exceptions.web.ControllerOvertaxedException;

/**
 * Simple bean which has supporting methods for the controllers to handle daily
 * and also specific tasks.
 * 
 * @author Christian
 * @author Ludwig
 *
 */
public interface ControllerHelper {

	/**
	 * Returns the id as {@code int} of the current logged in user by retrieving
	 * it from the {@code SecurityContextHolder}.
	 * 
	 * @return the id as {@code int} of the current logged in user
	 * @throws ControllerOvertaxedException
	 *             if an error occurs while requesting the id
	 */
	public int getUserId() throws ControllerOvertaxedException;

	/**
	 * Returns the current logged in user as {@link User}.
	 * 
	 * @return the current logged in {@link User}
	 * @throws DatabaseException
	 *             if an error occurs while requesting the user from the
	 *             underlying {@link UserService}
	 * @throws ControllerOvertaxedException
	 *             if an error occurs while working with the
	 *             {@code Authentication}
	 */
	public Optional<User> getUser() throws DatabaseException, ControllerOvertaxedException;

	/**
	 * Checks if a requested category is a existing one. The formatting of the
	 * passed category does not matter.
	 * 
	 * @param category
	 *            the category as {@link String}
	 * @return {@code true} it it exists, {@code false} otherwise
	 * @throws DatabaseException
	 */
	public boolean isExistingCategory(String category) throws DatabaseException;

	/**
	 * As a category in the URL can be wrong formatted (MYSQL instead of MySQL),
	 * this method returns the correct formatted name, e.g. for displaying it to
	 * the user.
	 * 
	 * @param category
	 *            the category as {@link String} to convert
	 * @return the correct formatted category name as {@link String}
	 * @throws DatabaseException
	 * @throws ControllerOvertaxedException
	 */
	public String getCorrectCategoryName(String category) throws ControllerOvertaxedException, DatabaseException;
}
