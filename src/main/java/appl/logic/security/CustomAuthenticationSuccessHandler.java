package appl.logic.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import appl.data.items.User;
import appl.enums.UserRoles;

/**
 * The {@code CustomAuthenticationSuccessHandler} manages the routing after a
 * successful login. If the user could be identified as ADMIN, a redirect to the
 * backend route will be send; if the user could be identified as USER, a
 * redirect to the route to the own account will be send. If somehow the user
 * could not be associated with one of the {@link UserRoles}, the user will get
 * logged out programmatically and will be redirect to the login page.
 * 
 * @author Christian
 *
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (response == null) {
			throw new IllegalArgumentException(
					"The passed HttpServletResponse or Authentication is null, no redirect can be performed.");
		}
		User user = (User) authentication.getPrincipal();
		if (user != null && UserRoles.ADMIN.toString().equals(user.getRole())) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect("backend");
		} else if (user != null && UserRoles.USER.toString().equals(user.getRole())) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect("meinkonto");
		} else {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			response.sendRedirect("login");
		}
	}
}
