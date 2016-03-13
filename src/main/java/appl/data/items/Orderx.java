package appl.data.items;

/**
 * @author Madeleine
 */
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Orderx is a POJO marked as persistent entity with table name "orderx". It
 * represents an order object with different variables:
 * <ul>
 * <li>orderId</li>
 * <li>date</li>
 * </ul>
 * {@link Set} of {@link OrderItem} is joined via a one-to-many connection.
 * {@link User} is joined via a many-to-one connection.
 * 
 * The class is not called "Order" because it might cause problems in
 * sql-statements, even if preparedStatements are used
 * 
 * @author Madeleine
 *
 */
@Entity
@Table(name = "orderx", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "orderId") })
public class Orderx {
	private int orderId;
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	private User user;

	private Calendar date;

	/**
	 * Required Constructor, may be private
	 */
	private Orderx() {

	}

	/**
	 * 
	 * @param date
	 */
	public Orderx(Calendar date) {
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId", unique = true, nullable = false)
	public int getOrderId() {
		return this.orderId;
	}

	@Column(name = "date", nullable = false, length = 256)
	public Calendar getDate() {
		return this.date;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USERID", nullable = true)
	public User getUser() {
		return this.user;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	private void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	private void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private void setDate(Calendar date) {
		this.date = date;
	}

	@Override
	public String toString() {
		// FIXME Madeleine: Stringbuffer von Johannes eingebaut.
		StringBuffer buffer = new StringBuffer();
		buffer.append("OrderId + " + orderId + " UserId" + user.getUserId() + " UserSurName " + user.getSurname() + "\n"
				+ "Amount of Ordered Items " + orderItems.size() + "\n");
		for (OrderItem a : orderItems) {
			buffer.append("Isbn " + a.getBook().getIsbn() + " Title" + a.getBook().getTitle() + " Ordering-Price "
					+ a.getPrice() + "\n");
		}
		return buffer.toString();
	}

}
