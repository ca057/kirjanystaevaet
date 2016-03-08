package web.controllers.helper;

import java.util.Optional;
import java.util.stream.Collectors;

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

	@Override
	public boolean isExistingCategory(String category) throws DatabaseException {
		return bookService.getAllCategoryNames().stream().map(s -> s.toUpperCase()).collect(Collectors.toList())
				.contains(category.toUpperCase());
	}

	@Override
	public String getCorrectCategoryName(String category) throws ControllerOvertaxedException, DatabaseException {
		return bookService.getAllCategoryNames().stream().filter(c -> c.toUpperCase().equals(category.toUpperCase()))
				.findFirst()
				.orElseThrow(() -> new ControllerOvertaxedException("The searched category could not be found."));
	}

	@Override
	public void saveProfilePicture(int userId, byte[] inputFile, boolean createThumbnail) throws DatabaseException {
		if (createThumbnail) {
			// TODO implement this
			// result = resize(new File(inputFile), 50, 50);
		}
		System.out.println("Service: " + userService);
		System.out.println("Bild upload: " + userService.updateAccount(userId, inputFile));
	}

}
