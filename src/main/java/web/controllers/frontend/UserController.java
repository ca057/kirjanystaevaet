package web.controllers.frontend;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import appl.logic.service.OrderService;
import exceptions.data.DatabaseException;
import exceptions.web.ControllerOvertaxedException;
import web.controllers.ControllerHelper;
import web.controllers.UploadHelper;

/**
 * Controller responsible for displaying the page of a single user.
 * 
 * @author Christian
 * @author Ludwig
 *
 */
@Controller
public class UserController {

	@Autowired
	private ControllerHelper helper;

	@Autowired
	private UploadHelper uploadHelper;

	@Autowired
	private OrderService orderService;

	/**
	 * Handles a GET request for rendering the page of a single user.
	 * 
	 * @return the name of the view
	 * @throws ControllerOvertaxedException
	 *             if the logged in user can not be retrieved
	 */
	@RequestMapping(value = "/meinkonto", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String userGet(Model m) throws ControllerOvertaxedException {
		try {
			m.addAttribute("user", helper.getUser().get());
			m.addAttribute("picture", helper.getUser().get());
			m.addAttribute("lastOrders", orderService.getOrdersByUserid(helper.getUserId()));
		} catch (DatabaseException e) {
			throw new ControllerOvertaxedException(
					"An unknown error occured which should not happen. This can not be handled by the controller.");
		}
		return "user";
	}

	@RequestMapping(value = "/meinkonto/profilbild", method = RequestMethod.GET)
	public @ResponseBody byte[] getPicture(/* HttpServletResponse response */) {
		byte[] imageBytes;
		try {
			// response.setContentType("image/jpeg");
			imageBytes = helper.getUser().get().getImage();
			// response.getOutputStream().write(imageBytes);
			// response.getOutputStream().flush();
			return imageBytes;
		} catch (DatabaseException | ControllerOvertaxedException /* | IOException */ e) {
			System.err.println("Fehler beim Bild holen");
		}
		return null;
	}

	@RequestMapping(value = "/meinkonto/bildhochladen", method = RequestMethod.POST)
	public String addPicture(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (!file.getContentType().contains("image")) {
			throw new IllegalArgumentException("The uploaded file is not an image");
		}
		try {
			uploadHelper.saveProfilePicture(helper.getUserId(), file.getBytes(), true);
		} catch (IOException | ControllerOvertaxedException | DatabaseException e) {
			// TODO Handle this
		}
		return "redirect:/meinkonto";
	}

}
