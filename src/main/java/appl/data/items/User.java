package appl.data.items;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import appl.data.builder.UserBuilder;

/**
 * This class represents users of any kind (for example user as well as
 * administrators).
 * 
 * User is a POJO marked as persistent entity with table name "user". It
 * represents a user object with different variables:
 * <ul>
 * <li>password</li>
 * <li>name</li>
 * <li>surname</li>
 * <li>email</li>
 * <li>street</li>
 * <li>street number</li>
 * <li>role</li>
 * </ul>
 * Lists of {@link Orderx}s are joined via many-to-many connections. {@link PLZ}
 * is joined via many-to-one connections.
 * 
 * The {@code password} will not be encrypted, so a prior handling of this issue
 * is necessary!
 * 
 * Furthermore, it is recommended to use an {@link UserBuilder} to build a new
 * object of this class.
 * 
 * @author Johannes
 *
 */
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
	private Set<Orderx> orders;
	private Set<Book> lastBooks;
	private byte[] image;

	private User() {
	}

	/**
	 * Constructor to instantiate an object of the type {@code User}. It is
	 * highly recommended to use it only in conjunction with an
	 * {@code UserBuilder}.
	 * 
	 * The password will not be encrypted by this constructor.
	 * 
	 * @param password
	 *            The password to access the account - must not be empty
	 * @param name
	 *            the first name of the person - must not be empty
	 * @param surname
	 *            the surname of the person - must not be empty
	 * @param email
	 *            the email of the person - must not be empty
	 * @param street
	 *            the street name
	 * @param streetnumber
	 *            the street number
	 * @param plz
	 *            the post code as object
	 * @param role
	 *            the role of the person
	 * @param image
	 *            the image of the user as {@code byte[]}
	 * @param orders
	 *            orders made by the person
	 * 
	 * @see {@link UserBuilder}
	 * @see {@link PLZ}
	 */
	public User(String password, String name, String surname, String email, String street, String streetnumber, PLZ plz,
//<<<<<<< HEAD
//			String role, HashSet<Orderx> orders) {
//=======
			//String role, byte[] image, Set<Order> orders) {
		String role, byte[] image, Set<Orderx> orders) {
	
//>>>>>>> refs/remotes/origin/master
		setPassword(password);
		setName(surname);
		setSurname(surname);
		setEmail(email);
		setStreet(street);
		setStreetnumber(streetnumber);
		setPlz(plz);
		setRole(role);
		setImage(image);
		setOrders(orders);
	}

	//public User(int id, String password, String name, String surname, String email, String street, String streetnumber,
	//		PLZ plz, String role, byte[] image, Set<Order> orders) {
		public User(int id, String password, String name, String surname, String email, String street, String streetnumber,
				PLZ plz, String role, byte[] image, Set<Orderx> orders) {
	
		setUserId(id);
		setPassword(password);
		setName(surname);
		setSurname(surname);
		setEmail(email);
		setStreet(street);
		setStreetnumber(streetnumber);
		setPlz(plz);
		setRole(role);
		setImage(image);
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

//<<<<<<< HEAD
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//	public Set<Orderx> getOrders() {
//=======
	@Lob
	@Column(name = "image", nullable = true)
	public byte[] getImage() {
		return image;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	//public Set<Order> getOrders() {
		public Set<Orderx> getOrders() {

//>>>>>>> refs/remotes/origin/master
		return this.orders;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "userbooks", schema = "public", joinColumns = @JoinColumn(name = "userId") , inverseJoinColumns = @JoinColumn(name = "isbn") )
	public Set<Book> getLastBooks() {
		return lastBooks;
	}

	private void setUserId(int id) {
		this.userId = id;
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
		this.password = password;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setSurname(String surname) {
		this.surname = surname;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private void setImage(byte[] image) {
		this.image = image;
	}

	private void setRole(String role) {
		if (role != null) {
			this.role = role.toString();
		}
	}

	private void setOrders(Set<Orderx> orders) {
		this.orders = orders;
	}

	private void setLastBooks(Set<Book> lastBooks) {
		this.lastBooks = lastBooks;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", email=" + email + ", street=" + street + ", streetnumber=" + streetnumber + ", plz=" + plz
				+ ", role=" + role + " image=" + (this.image != null) + "]";
	}

}
