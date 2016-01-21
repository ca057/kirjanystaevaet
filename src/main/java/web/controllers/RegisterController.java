package web.controllers;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/registrierung")
public class RegisterController {

	static class User implements Serializable {
		// TODO Serializable needed?
		private static final long serialVersionUID = 1L;
		private String name;
		private String surname;
		private String email;
		private String password;

		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		protected String getSurname() {
			return surname;
		}

		protected void setSurname(String surname) {
			this.surname = surname;
		}

		protected String getEmail() {
			return email;
		}

		protected void setEmail(String email) {
			this.email = email;
		}

		protected String getPassword() {
			return password;
		}

		protected void setPassword(String password) {
			this.password = password;
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public String registerGet() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public ResponseEntity<String> registerPost(@RequestBody User user) {
		// TODO implement AJAX logic here
		System.out.println("I got data!" + user.toString());
		return new ResponseEntity<String>(user.toString(), HttpStatus.OK);
	}

}
