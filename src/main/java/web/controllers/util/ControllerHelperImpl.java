package web.controllers.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import appl.data.items.User;
import appl.logic.service.BookService;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;
import exceptions.web.ControllerOvertaxedException;
import web.controllers.ControllerHelper;

@Component
public class ControllerHelperImpl implements ControllerHelper {

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	private Optional<Authentication> getAuthentication() {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
	}

	@Override
	public int getUserId() throws ControllerOvertaxedException {
		return ((User) getAuthentication().orElseThrow(() -> new ControllerOvertaxedException()).getPrincipal())
				.getUserId();
	}

	@Override
	public Optional<User> getUser() throws DatabaseException, ControllerOvertaxedException {
		return userService.findByID(getUserId());
	}
}
