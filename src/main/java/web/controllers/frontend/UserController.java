package web.controllers.frontend;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import appl.logic.service.OrderService;
import exceptions.data.DatabaseException;
import exceptions.web.ControllerOvertaxedException;
import web.controllers.ControllerHelper;
import web.controllers.ProcessUpload;

/**
 * Controller responsible for displaying the page of a single user.
 * 
 * @author Christian
 * @author Ludwig
 *
 */
@Controller
@RequestMapping(value = "/meinkonto")
public class UserController {

	@Autowired
	private ControllerHelper helper;

	public void setControllerHelper(ControllerHelper helper) {
		this.helper = helper;
	}

	@Autowired
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * Handles a GET request for rendering the page of a single user.
	 * 
	 * @return the name of the view
	 * @throws ControllerOvertaxedException
	 *             if the logged in user can not be retrieved
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
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

	@RequestMapping("/meinkonto/profilbild/{id}")
	public @ResponseBody byte[] getPicture(@PathVariable("id") final String id) {
		byte[] imageBytes;
		try {
			imageBytes = helper.getUser().get().getImage();
			return imageBytes;
		} catch (DatabaseException | ControllerOvertaxedException e) {
			// TODO Handle this
		}
		return null;
	}

	@RequestMapping(value = "/meinkonto/bildhochladen", method = RequestMethod.POST)
	public String addPicture(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (!file.getContentType().contains("image")) {
			throw new IllegalArgumentException("The uploaded file is not an image");
		}
		try {
			new ProcessUpload().saveProfilePicture(helper.getUserId(), file.getBytes(), true);
		} catch (IOException | ControllerOvertaxedException | DatabaseException e) {
			// TODO Handle this
		}
		return "redirect:/meinkonto";
	}

}
