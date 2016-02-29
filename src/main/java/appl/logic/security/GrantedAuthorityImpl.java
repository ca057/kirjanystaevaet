package appl.logic.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Basic implementation of {@link GrantedAuthority} to manage the authentication
 * using user roles.
 * 
 * @author Johannes
 *
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
	private String role;

	public GrantedAuthorityImpl(String role) {
		this.role = role;
	};

	@Override
	public String getAuthority() {
		return role;
	}

}
