package web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.items.UserRegisterWrapper;

@Controller
@RequestMapping(path = "/registrierung")
public class RegisterController {

	@RequestMapping(method = RequestMethod.GET)
	public String registerGet() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserRegisterWrapper> add(@RequestBody UserRegisterWrapper req) {
		System.out.println("I got data!" + req.getName());

		return new ResponseEntity<UserRegisterWrapper>(req, HttpStatus.OK);
	}
}
