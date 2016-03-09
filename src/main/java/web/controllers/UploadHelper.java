package web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import exceptions.data.DatabaseException;

public interface UploadHelper {

	public void saveBookCover(String title, String extension, byte[] inputFile, HttpServletRequest request,
			boolean createThumbnail) throws IOException;

	public void saveProfilePicture(int userId, byte[] inputFile, boolean createThumbnail) throws DatabaseException;

}
