package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the route to contact and imprint.
 * 
 * @author Christian
 * @author Ludwig
 * 
 */
@Controller
public class ContactController {

	/**
	 * If the user requests the contact and imprint route, this method will
	 * return the view responsible for displaying this page.
	 * 
	 * @return the name of the view
	 */
	@RequestMapping(value = "/kontakt", method = RequestMethod.GET)
	public String getContact() {
		return "contact";
	}
}
