package appl.logic.security;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import appl.data.items.User;
import appl.logic.service.UserService;
import exceptions.data.PrimaryKeyViolation;

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
		User user = null;
		try {
			user = userService.findbyMail(email);
			// TODO check if exists / Fehler fangen
			// TODO UserRole Enum / String überlegen
			LinkedList<GrantedAuthorityImpl> list = new LinkedList<GrantedAuthorityImpl>();
			System.out.println("Rolle: " + user.getRole());
			list.add(new GrantedAuthorityImpl("ROLE_" + user.getRole()));
			return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), list);
		} catch (PrimaryKeyViolation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO verbessern
		return null;

	}

	// UTIL
}
