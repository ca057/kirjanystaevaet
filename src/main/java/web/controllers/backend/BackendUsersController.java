package web.controllers.backend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.enums.UserRoles;
import appl.data.enums.Userfields;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import web.jsonwrappers.UserJSONWrapper;

@Controller
public class BackendUsersController {

	private UserService userService;

	@Autowired
	private void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/backend/nutzerinnen", method = RequestMethod.GET)
	public String getUsers(Model m) {
		try {
			m.addAttribute("users", userService.getUsers());
		} catch (DatabaseException e) {
			m.addAttribute("errormsg", e.getMessage());
			return "backend/users?error";
		}
		return "backend/users";
	}

	@RequestMapping(path = "/backend/nutzerinnen/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserJSONWrapper> addUser(@RequestBody final UserJSONWrapper req) {
		Map<Userfields, String> userMap = new HashMap<Userfields, String>();
		userMap.put(Userfields.email, req.getEmail());
		userMap.put(Userfields.name, req.getName());
		userMap.put(Userfields.surname, req.getSurname());
		userMap.put(Userfields.password, "magic");
		// FIXME implement PLZ
		// userMap.put(Userfields.plz, req.getPlz());
		userMap.put(Userfields.role,
				req.getRole().equals("ADMIN") ? UserRoles.ADMIN.toString() : UserRoles.USER.toString());
		userMap.put(Userfields.street, req.getStreet());
		userMap.put(Userfields.streetnumber, req.getStreetnumber());

		UserJSONWrapper returnWrapper = req;
		returnWrapper.setPassword("");
		try {
			int id = userService.createAccount(userMap);
			return new ResponseEntity<UserJSONWrapper>(returnWrapper, HttpStatus.OK);
		} catch (DatabaseException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<UserJSONWrapper>(returnWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(path = "/backend/nutzerinnen/edit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserJSONWrapper> editUser(@RequestBody final UserJSONWrapper req) {
		// FIXME implement me!
		System.out.println("ID of user: " + req.getId());

		UserJSONWrapper returnWrapper = req;
		returnWrapper.setPassword("");
		try {
			return new ResponseEntity<UserJSONWrapper>(returnWrapper, HttpStatus.OK);
		} catch (Exception e) {
			// TODO implement correct exception
			System.err.println(e.getMessage());
			return new ResponseEntity<UserJSONWrapper>(returnWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
