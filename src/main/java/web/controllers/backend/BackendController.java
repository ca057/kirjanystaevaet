package web.controllers.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/backend")
public class BackendController {

	public BackendController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String getBackend() {
		return "backend";
	}

}
