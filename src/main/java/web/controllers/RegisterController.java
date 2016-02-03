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
import exceptions.data.PrimaryKeyViolationException;

@Controller
@RequestMapping(path = "/registrierung")
public class RegisterController {

	static class UserRegisterWrapper {

		private String name;
		private String surname;
		private String email;
		private String password;
		private String street;
		private String streetnumber;
		private String plz;

		public UserRegisterWrapper() {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getStreetnumber() {
			return streetnumber;
		}

		public void setStreetnumber(String streetnumber) {
			this.streetnumber = streetnumber;
		}

		public String getPlz() {
			return plz;
		}

		public void setPlz(String plz) {
			this.plz = plz;
		}
	}

	@Autowired
	private UserService userService;

	@Autowired
	private DaoAuthenticationProvider authProvider;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAuthProvider(DaoAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String registerGet() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserRegisterWrapper> add(@RequestBody final UserRegisterWrapper req) {
		Map<Userfields, String> userMap = new HashMap<Userfields, String>();
		userMap.put(Userfields.email, req.getEmail());
		userMap.put(Userfields.name, req.getName());
		userMap.put(Userfields.surname, req.getSurname());
		userMap.put(Userfields.password, req.getPassword());
		userMap.put(Userfields.plz, req.getPlz());
		userMap.put(Userfields.role, UserRoles.USER.toString());
		userMap.put(Userfields.street, req.getStreet());
		userMap.put(Userfields.streetnumber, req.getStreetnumber());

		UserRegisterWrapper returnWrapper = req;
		req.setPassword("");

		try {
			int id = userService.registerNewUserAccount(userMap);
			User user = userService.findByID(id);
			// TODO log user in and redirect to /meinkonto
			// authProvider.authenticate(new
			// UsernamePasswordAuthenticationToken(user.getEmail(),
			// user.getPassword()));

			return new ResponseEntity<UserRegisterWrapper>(returnWrapper, HttpStatus.OK);
		} catch (PrimaryKeyViolationException e) {
			return new ResponseEntity<UserRegisterWrapper>(returnWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
