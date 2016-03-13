package web.jsonwrappers;

import java.io.File;

/**
 * Wrapper for sending and receiving user data as JSON to and from the client.
 * 
 * @author Christian
 *
 */
public class UserJSONWrapper {
	private String name;
	private String surname;
	private String email;
	private String password;
	private String street;
	private String streetnumber;
	private String plz;
	private String role;
	private String id;
	private File image;

	public UserJSONWrapper() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserJSONWrapper [name=" + name + ", surname=" + surname + ", email=" + email + ", password=" + password
				+ ", street=" + street + ", streetnumber=" + streetnumber + ", plz=" + plz + ", role=" + role + ", id="
				+ id + ", image=" + (image != null) + "]";
	}

}
