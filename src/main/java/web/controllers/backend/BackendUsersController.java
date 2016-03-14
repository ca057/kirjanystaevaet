package web.controllers.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.enums.UserRoles;
import appl.enums.Userfields;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import exceptions.web.ControllerOvertaxedException;
import web.controllers.ControllerHelper;
import web.jsonwrappers.UserJSONWrapper;

/**
 * Controller handles all requests for managing {@link User}s in the admin
 * backend.
 * 
 * @author Christian
 *
 */
@Controller
public class BackendUsersController {

	@Autowired
	private UserService userService;

	@Autowired
	private ControllerHelper helper;

	/**
	 * Returns the view associated with managing the {@link User}s.
	 * 
	 * @param m
	 *            the {@link Model} for the view
	 * @return the name of the view
	 */
	@RequestMapping(value = "/backend/nutzerinnen", method = RequestMethod.GET)
	public String getUsers(Model m) {
		try {
			int id = helper.getUserId();
			m.addAttribute("users", userService.getUsers());
			m.addAttribute("usersToDelete",
					userService.getUsers().stream().filter(u -> u.getUserId() != id).collect(Collectors.toList()));
		} catch (DatabaseException | ControllerOvertaxedException e) {
			m.addAttribute("errormsg", e.getMessage());
			return "backend/users?error";
		}
		return "backend/users";
	}

	/**
	 * Handles a JSON request send to the path for adding a new {@link User}.
	 * 
	 * @param req
	 *            the request as {@link UserJSONWrapper}
	 * @return a {@link ResponseEntity} with a {@link HttpStatus} giving
	 *         information about success or failure
	 */
	@RequestMapping(path = "/backend/nutzerinnen/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserJSONWrapper> addUser(@RequestBody final UserJSONWrapper req) {
		if (req == null) {
			return new ResponseEntity<UserJSONWrapper>(HttpStatus.BAD_REQUEST);
		}
		Map<Userfields, String> userMap = new HashMap<Userfields, String>();
		userMap.put(Userfields.email, req.getEmail());
		userMap.put(Userfields.name, req.getName());
		userMap.put(Userfields.surname, req.getSurname());
		userMap.put(Userfields.password, "magic");
		userMap.put(Userfields.plzId, req.getPlz());
		userMap.put(Userfields.role,
				req.getRole().equals("ADMIN") ? UserRoles.ADMIN.toString() : UserRoles.USER.toString());
		userMap.put(Userfields.street, req.getStreet());
		userMap.put(Userfields.streetnumber, req.getStreetnumber());

		UserJSONWrapper returnWrapper = req;
		returnWrapper.setPassword("");
		try {
			userService.createAccount(userMap);
			return new ResponseEntity<UserJSONWrapper>(returnWrapper, HttpStatus.CREATED);
		} catch (DatabaseException e) {
			System.err.println(e.getMessage());
			return returnUnprocessableEntity(returnWrapper);
		}
	}

	/**
	 * Responsible for editing data of a {@link User}, consumes data as a
	 * {@link UserJSONWrapper}.
	 * 
	 * @param req
	 *            the request as {@link UserJSONWrapper}
	 * @return a {@link ResponseEntity} with a {@link HttpStatus} giving
	 *         information about success or failure
	 */
	@RequestMapping(path = "/backend/nutzerinnen/edit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserJSONWrapper> editUser(@RequestBody final UserJSONWrapper req) {
		if (req == null) {
			return new ResponseEntity<UserJSONWrapper>(HttpStatus.BAD_REQUEST);
		}

		Map<Userfields, String> userMap = new HashMap<Userfields, String>();
		if (req.getName().isEmpty()) {
			userMap.put(Userfields.name, req.getName());
		} else if (!req.getSurname().isEmpty()) {
			userMap.put(Userfields.surname, req.getSurname());
		} else if (!req.getStreet().isEmpty()) {
			userMap.put(Userfields.street, req.getStreet());
		} else if (!req.getStreetnumber().isEmpty()) {
			userMap.put(Userfields.streetnumber, req.getStreetnumber());
		} else if (!req.getPlz().isEmpty()) {
			userMap.put(Userfields.plzId, req.getPlz());
		} else if (!req.getEmail().isEmpty()) {
			userMap.put(Userfields.email, req.getEmail());
		} else if (!req.getRole().isEmpty()) {
			userMap.put(Userfields.role, req.getRole());
		} else if (!req.getPassword().isEmpty()) {
			userMap.put(Userfields.password, req.getPassword());
		}

		UserJSONWrapper returnWrapper = req;
		returnWrapper.setPassword("");

		try {
			if (!userService.updateAccount(Integer.parseInt(req.getId()), userMap)) {
				return returnUnprocessableEntity(returnWrapper);
			}
			return new ResponseEntity<UserJSONWrapper>(returnWrapper, HttpStatus.OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			return returnUnprocessableEntity(returnWrapper);
		}
	}

	/**
	 * Responsible for handling the deletion of an {@link User}. Takes the id of
	 * the user as request parameter.
	 * 
	 * @param id
	 *            the id of the user to delete
	 * @return redirects the client to the URL responsible for displaying the
	 *         view for managing users
	 */
	@RequestMapping(path = "/backend/nutzerinnen/delete", method = RequestMethod.POST)
	public String deleteUsers(@RequestParam(value = "id") String id) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("The passed id is null or empty, user cannot be deleted.");
		}
		try {
			userService.deleteAccount(Integer.parseInt(id));
		} catch (NumberFormatException | DatabaseException e) {
			return "redirect:/backend/nutzerinnen?error&msg=" + e.getMessage();
		}
		return "redirect:/backend/nutzerinnen";
	}

	/**
	 * Internal method used for better readability of code, returns a
	 * {@link ResponseEntity} with an HTTP status code of 422.
	 * 
	 * @param response
	 * @return
	 */
	private ResponseEntity<UserJSONWrapper> returnUnprocessableEntity(UserJSONWrapper response) {
		if (response == null) {
			throw new IllegalArgumentException(
					"The passed UserJSONWrapper as response is null, no ResponseEntity can be returned.");
		}
		return new ResponseEntity<UserJSONWrapper>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
