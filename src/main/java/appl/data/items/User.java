package appl.data.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "userID") })
public class User {
	private int userID;
	private String nickname;
	private String name;
	private String surname;
	private String email;
	private String street;
	private String streetnumber;
	private PLZ plz;
	// TODO Bankverbindung?
	// TODO Orders?

	@Id
	@GeneratedValue
	@Column(name = "USERID", unique = true, nullable = false)
	public int getUserID() {
		return userID;
	}

	@Column(name = "NICKNAME", unique = true, nullable = false)
	public String getNickname() {
		return nickname;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "SURNAME", nullable = false)
	public String getSurname() {
		return surname;
	}

	@Column(name = "EMAIL", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	@Column(name = "STREET", nullable = false)
	public String getStreet() {
		return street;
	}

	@Column(name = "STREETNUMBER", nullable = false)
	public String getStreetnumber() {
		return streetnumber;
	}

	@ManyToOne
	@JoinColumn(name = "postcode")
	public PLZ getPlz() {
		return plz;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}

	public void setPlz(PLZ plz) {
		this.plz = plz;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
