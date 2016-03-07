package web.controllers.backend;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

public class ProcessUpload {

	public ProcessUpload() {
	}

	void saveImage(String title, String extension, byte[] inputFile, HttpServletRequest request,
			boolean createThumbnail) throws IOException {
		File dir = new File(request.getSession().getServletContext()
				.getRealPath(File.separator + "uploaded" + File.separator + "img" + File.separator + "cover"));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// extension: MultipartFile..getOriginalFilename().split("\\.")[1]
		File serverFile = new File(dir.getAbsolutePath() + File.separator + title + "." + extension);

		if (createThumbnail) {
			try {
				resize(serverFile, 50, 50);
			} catch (IOException ignore) {
			}
		}

		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
			stream.write(inputFile);
			stream.close();
		}
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