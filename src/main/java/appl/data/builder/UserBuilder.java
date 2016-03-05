package appl.data.builder;

import java.util.Set;

import org.springframework.stereotype.Component;

import appl.data.enums.UserRoles;
import appl.data.items.Orderx;
import appl.data.items.PLZ;
import appl.data.items.User;
import appl.data.items.UserBookStatistic;;

/**
 * Builder to create a new object of the {@link User} class.
 * 
 * It is essential to use a new instance of this class every time a new
 * {@link User} is to be created!
 * 
 * @author Johannes
 * 
 * @see {@link User}
 *
 */
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

	public UserBuilder setUserBookStatistics(Set<UserBookStatistic> userBookStatistics);

	public UserBuilder setOrders(Set<Orderx> orders);

	public UserBuilder setId(int id);

	public UserRoles getRole();

	public String getPassword();

	public String getName();

	public String getSurname();

	public String getEmail();

	public String getStreet();

	public String getStreetnumber();

	public PLZ getPlz();

	public byte[] getImage();

	public Set<UserBookStatistic> getUserBookStatistics();

	public Set<Orderx> getOrders();

	public int getId();

	public User createUser();

}
