package appl.logic.service.impl;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import appl.data.items.User;
import appl.logic.security.GrantedAuthorityImpl;
import appl.logic.security.UserPrincipal;
import appl.logic.service.UserService;
import exceptions.data.DatabaseException;

/**
 * Class to authenticate Login and connect Springs
 * {@link org.springframework.security.core.userdetails.User} with {@link User}.
 * 
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

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		try {
			User user = userService.findbyMail(email).get();
			LinkedList<GrantedAuthority> list = new LinkedList<GrantedAuthority>();
			list.add(new GrantedAuthorityImpl("ROLE_" + user.getRole()));
			return new UserPrincipal(user, list);
		} catch (DatabaseException | NoSuchElementException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
