package web.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/backend/einstellungen")
public class BackendSettingsController {

	@RequestMapping(method = RequestMethod.GET)
	public String getSettings() {
		return "backend/settings";
	}
}
