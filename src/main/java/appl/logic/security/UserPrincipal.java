package appl.logic.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import appl.data.items.User;

public class UserPrincipal extends User implements UserDetails {
	private List<GrantedAuthority> authorities;

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
