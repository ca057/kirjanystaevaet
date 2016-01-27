package web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.enums.UserRoles;
import appl.data.enums.Userfields;
import appl.data.items.UserRegisterWrapper;
import appl.logic.service.UserService;
import exceptions.data.PrimaryKeyViolation;

@Controller
@RequestMapping(path = "/registrierung")
public class RegisterController {

	@Autowired
	private UserService userService;

	public void setService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String registerGet() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserRegisterWrapper> add(@RequestBody UserRegisterWrapper req) {
		Map<Userfields, String> userMap = new HashMap<Userfields, String>();
		userMap.put(Userfields.email, req.getEmail());
		userMap.put(Userfields.name, req.getName());
		userMap.put(Userfields.surname, req.getSurname());
		userMap.put(Userfields.password, req.getPassword());
		userMap.put(Userfields.plz, req.getPlz());
		userMap.put(Userfields.role, UserRoles.USER.toString());
		userMap.put(Userfields.street, req.getStreet());
		userMap.put(Userfields.streetnumber, req.getStreetnumber());

		// TODO don´t handle the success in this way, work with exceptions
		int returnedID = 0;
		try {
			returnedID = userService.registerNewUserAccount(userMap);
		} catch (PrimaryKeyViolation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserRegisterWrapper returnWrapper = req;
		req.setPassword("");
		System.err.println("return-value: " + returnedID);

		if (returnedID >= 0) {
			// TODO try to redirect the user
			return new ResponseEntity<UserRegisterWrapper>(returnWrapper, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserRegisterWrapper>(returnWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
