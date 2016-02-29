package appl.logic.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import appl.data.items.User;

/**
 * Implementation of {@link UserDetails} to pass information about an
 * {@link User}.
 * 
 * @author Johannes
 *
 */
public class UserPrincipal extends User implements UserDetails {
	private static final long serialVersionUID = 5341519721167335979L;
	private List<GrantedAuthority> authorities;

	/**
	 * @param user
	 *            the user connected to the login attempt
	 * @param authorities
	 *            the permissions of the user
	 */
	public UserPrincipal(User user, List<GrantedAuthority> authorities) {
		super(user.getUserId(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(),
				user.getStreet(), user.getStreetnumber(), user.getPlz(), user.getRole(), user.getImage(),
				user.getOrders());
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

}
