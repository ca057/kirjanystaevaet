package appl.data.builder;

import org.springframework.stereotype.Component;

import appl.data.builder.impl.UserBuilderImpl;
import appl.data.enums.UserRoles;
import appl.data.items.PLZ;
import appl.data.items.User;

@Component
public interface UserBuilder {

	public UserBuilder setRole(UserRoles role);

	public UserBuilder setPassword(String password);

	public UserBuilder setName(String name);

	public UserBuilder setSurname(String surname);

	public UserBuilder setEmail(String eMail);

	public UserBuilder setStreet(String street);

	public UserBuilder setStreetnumber(String number);

	public UserBuilder setPLZ(PLZ plz);

	public UserBuilder setImage(byte[] image);

	public UserBuilder setId(int id);

	public User createUser();

	// TODO Ist das sinnvoll? Bisher noch nicht in Gebrauch.
	public default UserBuilder getUserBuilder() {
		return new UserBuilderImpl();
	}

}
