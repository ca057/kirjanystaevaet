package appl.data.builder.impl;

import org.springframework.stereotype.Component;

import appl.data.builder.UserBuilder;
import appl.data.enums.UserRoles;
import appl.data.items.PLZ;
import appl.data.items.User;

@Component
public class UserBuilderImpl implements UserBuilder {
	private String password;
	private String name;
	private String surname;
	private String email;
	private String street;
	private String streetnumber;
	private PLZ plz;
	private UserRoles role;
	private byte[] image;
	private int id = -1;

	@Override
	public UserBuilder setId(int id) {
		this.id = (id >= 0) ? id : -1;
		return this;
	}

	@Override
	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public UserBuilder setRole(UserRoles role) {
		this.role = role;
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
	public UserBuilder setImage(byte[] image) {
		this.image = image;
		return this;
	}

	@Override
	public User createUser() {
		if (id < 0) {
			return new User(password, name, surname, email, street, streetnumber, plz, role.toString(), image, null);
		} else {
			return new User(id, password, name, surname, email, street, streetnumber, plz, role.toString(), image,
					null);
		}
		// TODO remove old variables?
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getStreet() {
		return street;
	}

	@Override
	public String getStreetnumber() {
		return streetnumber;
	}

	@Override
	public PLZ getPlz() {
		return plz;
	}

	@Override
	public UserRoles getRole() {
		return role;
	}

	@Override
	public byte[] getImage() {
		return image;
	}

	@Override
	public int getId() {
		return id;
	}

}
