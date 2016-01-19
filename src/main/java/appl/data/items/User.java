package appl.data.items;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User {
	private String password;
	private String name;
	private String surname;
	private String email;
	private String street;
	private String streetnumber;
	private PLZ plz;
	private String role;
	private Set<Order> orders;

	public User(String password, String name, String surname, String email, String street, String streetnumber, PLZ plz,
			String role, HashSet<Order> orders) {
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.street = street;
		this.streetnumber = streetnumber;
		this.plz = plz;
		this.role = role;

		this.orders = orders;
	}

	private User() {
	}

	@Id
	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	@Column(name = "password", unique = false, nullable = false)
	public String getPassword() {
		return password;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "surname", nullable = false)
	public String getSurname() {
		return surname;
	}

	@Column(name = "street", nullable = true)
	public String getStreet() {
		return street;
	}

	@Column(name = "streetnumber", nullable = true)
	public String getStreetnumber() {
		return streetnumber;
	}

	@Column(name = "role", nullable = false)
	public String getRole() {
		return role;
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

	public void setPassword(String password) {
		this.password = password;
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

	public void setRole(String role) {
		this.role = role;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
