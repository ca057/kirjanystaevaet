package appl.data.builder;

import appl.data.enums.UserRoles;
import appl.data.items.PLZ;
import appl.data.items.User;

public interface UserBuilder {

	public UserBuilder setRole(UserRoles role);

	public UserBuilder setPassword(String password);

	public UserBuilder setName(String name);

	public UserBuilder setSurname(String surname);

	public UserBuilder setEmail(String eMail);

	public UserBuilder setStreet(String street);

	public UserBuilder setStreetnumber(String number);

	public UserBuilder setPLZ(PLZ plz);

	public User createUser();

}
