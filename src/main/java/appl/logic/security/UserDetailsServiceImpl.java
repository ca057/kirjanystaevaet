package appl.logic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import appl.data.items.User;
import appl.logic.service.UserService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	UserService userService;

	@Autowired // Using setter injection
	public void setUserRepository(UserService userService) {
		this.userService = userService;
	}

	public UserDetailsServiceImpl() {
		super();
	}

	// API

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		// final String ip = getClientIP();
		// if (loginAttemptService.isBlocked(ip)) {
		// throw new RuntimeException("blocked");
		// }
		try {
			final User user = userService.findbyMail(email);
			if (user == null) {
				throw new UsernameNotFoundException("No user found with email: " + email);
			}
			// new org.springframework.security.core.userdetails.User
			// FIXME mail wird nicht richtig verarbeitet
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true,
					true, true, true, null);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	// UTIL
}
