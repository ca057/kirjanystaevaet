package web.controllers.frontend;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
 * @author Johannes
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
	public @ResponseBody ResponseEntity<byte[]> getPicture(HttpServletResponse response) {
		byte[] imageBytes;
		try {
			response.setContentType("image/jpeg");
			imageBytes = helper.getUser().get().getImage();
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			response.getOutputStream().write(imageBytes);
			response.getOutputStream().flush();
			return new ResponseEntity<byte[]>(imageBytes, headers, HttpStatus.CREATED);
		} catch (DatabaseException | ControllerOvertaxedException | IOException e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/meinkonto/bildhochladen", method = RequestMethod.POST)
	public String addPicture(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (!file.getContentType().contains("image")) {
			throw new IllegalArgumentException("The uploaded file is not an image");
		}
		try {
			uploadHelper.saveProfilePicture(helper.getUserId(), file.getBytes(), true);
		} catch (IOException | ControllerOvertaxedException | DatabaseException e) {
			return "redirect:/meinkonto?error";
		}
		return "redirect:/meinkonto";
	}

}
