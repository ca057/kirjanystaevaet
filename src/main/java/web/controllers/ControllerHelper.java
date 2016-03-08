package web.controllers;

import exceptions.controller.ControllerOvertaxedException;

/**
 * Simple bean which has supporting methods for the controllers to handle daily
 * and also specific tasks.
 * 
 * @author Christian
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
}
