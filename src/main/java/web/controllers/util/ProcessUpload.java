package web.controllers.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import web.controllers.UploadHelper;

@Component
public class ProcessUpload implements UploadHelper {

	@Autowired
	private UserService userService;

	@Override
	public boolean saveBookCover(String title, String extension, byte[] inputFile, HttpSession httpSession) {
		File dir = new File(httpSession.getServletContext()
				.getRealPath(File.separator + "uploaded" + File.separator + "img" + File.separator + "cover"));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File serverFile = new File(dir.getAbsolutePath() + File.separator + title + "." + extension);
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
			stream.write(inputFile);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public boolean saveProfilePicture(int userId, byte[] inputFile) throws DatabaseException {
		return userService.updateAccount(userId, inputFile);
	}

}