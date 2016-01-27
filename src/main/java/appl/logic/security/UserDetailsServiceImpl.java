package appl.logic.security;

import java.util.LinkedList;

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
		final User user = userService.findbyMail(email);
		// TODO UserRole Enum / String überlegen
		LinkedList<GrantedAuthorityImpl> list = new LinkedList<GrantedAuthorityImpl>();
		list.add(new GrantedAuthorityImpl("ROLE_" + user.getRole()));
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), list);
	}

	// UTIL
}
