package web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import appl.data.items.Book;
import exceptions.data.DatabaseException;

/**
 * Provides methods to upload files.
 * 
 * @author Johannes
 *
 */
public interface UploadHelper {

	/**
	 * This method saves the cover of a {@link Book} with a specific
	 * {@code title} in {@code /resources/img}. In case of
	 * <i>kirjanystaevaet</i>, the title is supposed to be the ISBN of the book.
	 * 
	 * @param title
	 *            the title of the file
	 * @param extension
	 *            the extension of the file
	 * @param inputFile
	 *            the file
	 * @param session
	 *            the current session
	 * @throws IOException
	 *             if an error occurs while reading / writing
	 */
	public boolean saveBookCover(String title, String extension, byte[] inputFile, HttpSession httpSession);

	/**
	 * Saves a profile picture in the data set of the specific {@link User}.
	 * 
	 * @param userId
	 *            the id of the user
	 * @param inputFile
	 *            the image
	 * @throws DatabaseException
	 *             if an error occurs while saving
	 */
	public boolean saveProfilePicture(int userId, byte[] inputFile) throws DatabaseException;

}
