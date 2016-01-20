package appl.data.items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Set<Order> orders;
	// TODO Bankverbindung = Rechnung

	public User(String nickname, String name, String surname, String email, String street, String streetnumber,
			PLZ plz) {
		this.nickname = nickname;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.street = street;
		this.streetnumber = streetnumber;
		this.plz = plz;

		this.orders = new HashSet<Order>();
	}

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Order> getOrders() {
		return this.orders;
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

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
