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
import web.jsonwrappers.UserRegisterWrapper;

@Controller
public class BackendUsersController {

	private UserService userService;

	@Autowired
	private void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/backend/nutzerinnen", method = RequestMethod.GET)
	public String getUsers(Model m) {
		m.addAttribute("users", userService.getUsers());
		return "backend/users";
	}

	@RequestMapping(path = "/backend/nutzerinnen/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserRegisterWrapper> addUser(@RequestBody final UserRegisterWrapper req) {
		// TODO use register API here because code is the same
		Map<Userfields, String> userMap = new HashMap<Userfields, String>();
		userMap.put(Userfields.email, req.getEmail());
		userMap.put(Userfields.name, req.getName());
		userMap.put(Userfields.surname, req.getSurname());
		userMap.put(Userfields.password, "magic");
		userMap.put(Userfields.plz, req.getPlz());
		userMap.put(Userfields.role,
				req.getRole().equals("ADMIN") ? UserRoles.ADMIN.toString() : UserRoles.USER.toString());
		userMap.put(Userfields.street, req.getStreet());
		userMap.put(Userfields.streetnumber, req.getStreetnumber());

		UserRegisterWrapper returnWrapper = req;
		returnWrapper.setPassword("");
		try {
			int id = userService.createAccount(userMap);
			return new ResponseEntity<UserRegisterWrapper>(returnWrapper, HttpStatus.OK);
		} catch (DatabaseException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<UserRegisterWrapper>(returnWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
