package appl.logic.security;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import appl.data.items.User;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;

/**
 * @author Johannes
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	public UserDetailsServiceImpl() {
		super();
	}

	// API

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		try {
			// TODO check if exists / Fehler fangen
			// TODO UserRole Enum / String überlegen
			User user = userService.findbyMail(email).get();
			LinkedList<GrantedAuthority> list = new LinkedList<GrantedAuthority>();
			System.out.println("Rolle: " + user.getRole());
			list.add(new GrantedAuthorityImpl("ROLE_" + user.getRole()));
			// return new
			// org.springframework.security.core.userdetails.User(user.getName(),
			// user.getPassword(), list);
			return new UserPrincipal(user, list);
		} catch (DatabaseException | NoSuchElementException e) {
			// TODO Spring-Exception
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	// UTIL
}
