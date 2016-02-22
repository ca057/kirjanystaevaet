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

import appl.data.enums.UserRoles;
import exceptions.data.ErrorMessageHelper;

@Entity
@Table(name = "user", schema = "public")
public class User {
	private int userId;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String street;
	private String streetnumber;
	private PLZ plz;
	private String role;
	private Set<Order> orders;

	private User() {
	}

	public User(String password, String name, String surname, String email, String street, String streetnumber, PLZ plz,
			UserRoles role, HashSet<Order> orders) {
		setPassword(password);
		setName(surname);
		setSurname(surname);
		setEmail(email);
		setStreet(street);
		setStreetnumber(streetnumber);
		setPlz(plz);
		setRole(role);
		setOrders(orders);
	}

	@Id
	@GeneratedValue
	@Column(name = "userId")
	public int getUserId() {
		return userId;
	}

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

	private void setStreet(String street) {
		this.street = street;
	}

	private void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}

	private void setPlz(PLZ plz) {
		this.plz = plz;
	}

	private void setPassword(String password) {
		if (role == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("Password"));
		}
		this.password = password;
	}

	private void setName(String name) {
		if (role == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("Name"));
		}
		this.name = name;
	}

	private void setSurname(String surname) {
		if (role == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("Surname"));
		}
		this.surname = surname;
	}

	private void setEmail(String email) {
		if (role == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("E-Mail"));
		}
		this.email = email;
	}

	private void setRole(UserRoles role) {
		if (role == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("UserRole"));
		}
		this.role = role.toString();
	}

	private void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	private void setUserId(int id) {
		this.userId = id;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", email=" + email + ", street=" + street + ", streetnumber=" + streetnumber + ", plz=" + plz
				+ ", role=" + role + "]";
	}

}
