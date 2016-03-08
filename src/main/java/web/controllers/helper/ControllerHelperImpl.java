package web.controllers.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import appl.data.items.User;
import exceptions.controller.ControllerOvertaxedException;
import web.controllers.ControllerHelper;

@Component
public class ControllerHelperImpl implements ControllerHelper {

	@Override
	public int getUserId() throws ControllerOvertaxedException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			throw new ControllerOvertaxedException(
					"The authentication context is null, the ID of the user can not be figured out.");
		}
		return ((User) auth.getPrincipal()).getUserId();
	}

}
