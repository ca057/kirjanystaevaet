package web.controllers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.logic.service.DataKraken;
import exceptions.data.DatabaseException;
import exceptions.web.ControllerOvertaxedException;
import web.controllers.ControllerHelper;

@Controller
@RequestMapping(value = "/backend")
public class BackendDashboardController {

	@Autowired
	private ControllerHelper helper;

	@Autowired
	private DataKraken dataKraken;

	@RequestMapping(method = RequestMethod.GET)
	public String getBackend(Model m) throws ControllerOvertaxedException {
		try {
			m.addAllAttributes(dataKraken.attack());
		} catch (DatabaseException ignore) {
		}
		try {
			m.addAttribute("admin", helper.getUser().get());
		} catch (DatabaseException e) {
			throw new ControllerOvertaxedException(
					"The current logged in admin could not be retrieved. This seems to be a major error which can not be handled right now.");
		}
		return "dashboard";
	}

}
