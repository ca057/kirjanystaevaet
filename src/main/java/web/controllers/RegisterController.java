package web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.enums.UserRoles;
import appl.data.enums.Userfields;
import appl.data.items.User;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import web.jsonwrappers.UserJSONWrapper;

/**
 * Controller for managing the registration of new users.
 * 
 * @author Christian
 * @author Ludwig
 *
 */
@Controller
@RequestMapping(path = "/registrierung")
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private DaoAuthenticationProvider authProvider;

	/**
	 * Setter injection for the {@link UserService}.
	 * 
	 * @param userService
	 *            the {@link UserService} to inject
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Setter injection for the {@link DaoAuthenticationProvider}.
	 * 
	 * @param authProvider
	 *            the {@link DaoAuthenticationProvider} to inject
	 */
	public void setAuthProvider(DaoAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

	/**
	 * Handles a GET request and returns the name of the associated view.
	 * 
	 * @return the name of the view which displays the registration
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String registerGet() {
		return "register";
	}

	/**
	 * Handles a JSON request with the data of a new user. Request must be of
	 * type POST. It returns the passed data as JSON, the password field of the
	 * response will be empty. If registering a new user was not successful, an
	 * error code as status code is returned, a success code otherwise.
	 * 
	 * @param req
	 *            the {@link UserJSONWrapper} with the data of the new user to
	 *            register
	 * @return a {@link ResponseEntity} with a {@link UserJSONWrapper} either
	 *         with a success or an error status code
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserJSONWrapper> add(@RequestBody final UserJSONWrapper req) {
		System.out.println(req.getName());
		Map<Userfields, String> userMap = new HashMap<Userfields, String>();
		userMap.put(Userfields.email, req.getEmail());
		userMap.put(Userfields.name, req.getName());
		userMap.put(Userfields.surname, req.getSurname());
		userMap.put(Userfields.password, req.getPassword());
		userMap.put(Userfields.plz, req.getPlz());
		userMap.put(Userfields.role, UserRoles.USER.toString());
		userMap.put(Userfields.street, req.getStreet());
		userMap.put(Userfields.streetnumber, req.getStreetnumber());

		UserJSONWrapper returnWrapper = req;
		req.setPassword("");

		try {
			int id = userService.createAccount(userMap);
			// FIXME Habe ihr ein get() angehängt, das eine NoSuchElementE
			// schmeißen würde, falls leer / Johannes
			User user = userService.findByID(id).get();
			// TODO log user in and redirect to /meinkonto
			// authProvider.authenticate(new
			// UsernamePasswordAuthenticationToken(user.getEmail(),
			// user.getPassword()));

			return new ResponseEntity<UserJSONWrapper>(returnWrapper, HttpStatus.OK);
		} catch (DatabaseException e) {
			// } catch (PrimaryKeyViolationException e) {
			return new ResponseEntity<UserJSONWrapper>(returnWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}