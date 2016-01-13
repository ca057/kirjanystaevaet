package appl.data.builder;

import appl.data.items.User;


public interface UserBuilder {

	public User setNickname(String nickname);

	public User setName(String name);

	public User setSurname(String surname);

	public User setMail(String mail);

	public User setStreet(String street);

	public User setStreeNumber(int number);

	public User setPLZ(String plz);

}
