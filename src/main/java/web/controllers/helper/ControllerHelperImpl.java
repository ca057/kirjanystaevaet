package web.controllers.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import appl.data.items.User;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import web.controllers.ControllerHelper;

@Component
public class ControllerHelperImpl implements ControllerHelper {

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private Optional<Authentication> getAuthentication() {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
	}

	@Override
	public int getUserId() {
		return ((User) getAuthentication().get().getPrincipal()).getUserId();
	}

	@Override
	public Optional<User> getUser() throws DatabaseException {
		return userService.findByID(getUserId());
	}

}
