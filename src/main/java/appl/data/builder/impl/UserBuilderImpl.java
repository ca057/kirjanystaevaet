package appl.data.builder.impl;

import appl.data.builder.UserBuilder;
import appl.data.enums.UserRoles;
import appl.data.items.PLZ;
import appl.data.items.User;

public class UserBuilderImpl implements UserBuilder {
	private String password;
	private String nickname;
	private String name;
	private String surname;
	private String email;
	private String street;
	private String streetnumber;
	private PLZ plz;
	private String role;

	@Override
	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public UserBuilder setRole(UserRoles role) {
		this.role = role.toString();
		return this;
	}

	@Override
	public UserBuilder setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public UserBuilder setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	@Override
	public UserBuilder setEmail(String eMail) {
		this.email = eMail;
		return this;
	}

	@Override
	public UserBuilder setStreet(String street) {
		this.street = street;
		return this;
	}

	@Override
	public UserBuilder setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
		return this;
	}

	@Override
	public UserBuilder setPLZ(PLZ plz) {
		this.plz = plz;
		return this;
	}

	@Override
	public User createUser() {
		User user = new User(password, name, surname, email, street, streetnumber, plz, role);
		// TODO remove old variables?
		return user;
	}

}
