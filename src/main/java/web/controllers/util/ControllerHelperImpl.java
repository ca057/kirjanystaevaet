package web.controllers.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import appl.data.items.User;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import exceptions.web.ControllerOvertaxedException;
import web.controllers.ControllerHelper;

@Component
public class ControllerHelperImpl implements ControllerHelper {

	@Autowired
	private UserService userService;

	private Optional<Authentication> getAuthentication() {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
	}

	@Override
	public int getUserId() throws ControllerOvertaxedException {
		if (getAuthentication().orElseThrow(() -> new ControllerOvertaxedException()).getPrincipal() instanceof User) {
			return ((User) getAuthentication().orElseThrow(() -> new ControllerOvertaxedException()).getPrincipal())
					.getUserId();
		}
		throw new ControllerOvertaxedException("Return value of Authentication is not an User object as expected.");
	}

	@Override
	public Optional<User> getUser() throws DatabaseException, ControllerOvertaxedException {
		return userService.findByID(getUserId());
	}
}
