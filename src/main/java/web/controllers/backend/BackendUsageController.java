package web.controllers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.logic.service.DataKraken;

@Controller
@RequestMapping(value = "/backend/nutzungsstatistiken")
public class BackendUsageController {

	private DataKraken dataKraken;

	@Autowired
	public void summoningTheKraken(DataKraken dataKraken) {
		this.dataKraken = dataKraken;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getUsage(Model m) {
		m.addAllAttributes(dataKraken.attack());
		return "backend/usage";
	}
}
