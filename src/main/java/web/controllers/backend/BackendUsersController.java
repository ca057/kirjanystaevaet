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

import appl.enums.UserRoles;
import appl.enums.Userfields;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import web.jsonwrappers.UserJSONWrapper;

@Controller
public class BackendUsersController {

	@Autowired
	private UserService userService;

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
			System.err.println(e.getMessage());
			return returnUnprocessableEntity(returnWrapper);
		}
	}

	private ResponseEntity<UserJSONWrapper> returnUnprocessableEntity(UserJSONWrapper response) {
		if (response == null) {
			throw new IllegalArgumentException(
					"The passed UserJSONWrapper as response is null, no ResponseEntity can be returned.");
		}
		return new ResponseEntity<UserJSONWrapper>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
