package web.controllers.helper;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

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
	public void saveBookCover(String title, String extension, byte[] inputFile, HttpServletRequest request,
			boolean createThumbnail) throws IOException {
		File dir = new File(request.getSession().getServletContext()
				.getRealPath(File.separator + "uploaded" + File.separator + "img" + File.separator + "cover"));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// extension: MultipartFile..getOriginalFilename().split("\\.")[1]
		File serverFile = new File(dir.getAbsolutePath() + File.separator + title + "." + extension);

		if (createThumbnail) {
			// try {
			// TODO implement this
			// serverFile = resize(serverFile, 50, 50);
			// } catch (IOException ignore) {
			// }
		}

		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
			stream.write(inputFile);
			stream.close();
		}
	}

	@Override
	public void saveProfilePicture(int userId, byte[] inputFile, boolean createThumbnail) throws DatabaseException {
		if (createThumbnail) {
			// TODO implement this
			// result = resize(new File(inputFile), 50, 50);
		}
		System.out.println("Service: " + userService);
		System.out.println("Bild upload: " + userService.updateAccount(userId, inputFile));
	}

	public static BufferedImage resize(File inputFile, int scaledWidth, int scaledHeight) throws IOException {
		BufferedImage inputImage = ImageIO.read(inputFile);
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		return outputImage;
	}
}