package web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import appl.data.items.RequestWrapper;

@Controller
@RequestMapping(path = "/registrierung")
public class RegisterController {

	@RequestMapping(method = RequestMethod.GET)
	public String registerGet() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<RequestWrapper> add(@RequestBody RequestWrapper req, UriComponentsBuilder ucBuilder) {
		System.out.println("I got data!" + req.toString());

		RequestWrapper r = new RequestWrapper();
		return new ResponseEntity<RequestWrapper>(r, HttpStatus.OK);
	}
}
