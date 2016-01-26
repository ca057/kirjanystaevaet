package appl.logic.security;

import java.util.HashMap;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import appl.data.enums.UserRoles;
import appl.data.enums.Userfields;
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
		// try {
		// TODO final User?
		User user = userService.findbyMail(email);
		// new org.springframework.security.core.userdetails.User
		// TODO UserRole Enum / String überlegen
		if (user == null) {
			System.out.println("eingegebene mail: " + email);
			if ("admin@kirjanystaevaet.de".equals(email)) {
				HashMap<Userfields, String> data = new HashMap<>();
				data.put(Userfields.email, "admin@kirjanystaevaet.de");
				data.put(Userfields.password, "admin");
				data.put(Userfields.name, "admin");
				data.put(Userfields.surname, "admin");
				data.put(Userfields.role, UserRoles.ADMIN.toString());
				user = userService.findByID(userService.registerNewUserAccount(data));
				System.out.println("user neu: " + user);
				System.out.println("Alle user: " + userService.getUsers());
			} else if ("user@kirjanystaevaet.de".equals(email)) {
				HashMap<Userfields, String> data = new HashMap<>();
				data.put(Userfields.email, "user@kirjanystaevaet.de");
				data.put(Userfields.password, "user");
				data.put(Userfields.name, "user");
				data.put(Userfields.surname, "user");
				data.put(Userfields.role, UserRoles.USER.toString());
				userService.registerNewUserAccount(data);
				user = userService.findByID(userService.registerNewUserAccount(data));
			}
		}
		LinkedList<GrantedAuthorityImpl> list = new LinkedList<GrantedAuthorityImpl>();
		// Zu Testzwecken ausformuliert:
		String role = user.getRole();
		list.add(new GrantedAuthorityImpl(role));
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), list);
		// } catch (final Exception e) {
		// // TODO Das hier rausnehmen

		// throw new UsernameNotFoundException("No user found with email: " +
		// email + " " + e.getMessage());
		// }
	}

	// UTIL
}
