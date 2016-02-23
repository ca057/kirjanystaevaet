package web.controllers.backend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.enums.Userfields;
import appl.logic.service.UserService;
import exceptions.data.PrimaryKeyViolation;
import web.jsonwrappers.UserRegisterWrapper;

@Controller
public class BackendUsersController {

	private UserService userService;

	@Autowired
	private void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/backend/nutzerinnen", method = RequestMethod.GET)
	public String getUsers() {
		return "backend/users";
	}

	@RequestMapping(value = "/backend/nutzerinnen/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserRegisterWrapper> addUser(@RequestBody final UserRegisterWrapper req) {
		Map<Userfields, String> userMap = new HashMap<Userfields, String>();
		// FIXME all values are null or an empty string
		userMap.put(Userfields.email, req.getEmail());
		userMap.put(Userfields.name, req.getName());
		userMap.put(Userfields.surname, req.getSurname());
		// FIXME we need some generated password here
		userMap.put(Userfields.password, "magic");
		userMap.put(Userfields.plz, req.getPlz());
		// userMap.put(Userfields.role,
		// req.getRole().equals("ADMIN") ? UserRoles.ADMIN.toString() :
		// UserRoles.USER.toString());
		userMap.put(Userfields.street, req.getStreet());
		userMap.put(Userfields.streetnumber, req.getStreetnumber());

		// return the data without the password
		UserRegisterWrapper returnWrapper = req;
		returnWrapper.setPassword("");
		try {
			int id = userService.createAccount(userMap);
			// TODO check if registration was successfull
			return new ResponseEntity<UserRegisterWrapper>(returnWrapper, HttpStatus.OK);
		} catch (PrimaryKeyViolation e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<UserRegisterWrapper>(returnWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
