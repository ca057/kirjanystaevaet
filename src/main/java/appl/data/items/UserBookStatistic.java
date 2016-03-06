package appl.data.items;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import exceptions.data.ErrorMessageHelper;

/**
 * An object of this class tracks several statistics concerning one specific
 * user and one specific book.
 * 
 * It is possible to save and gain following information:
 * <ol>
 * <li>the last time the {@code user} visited the {@code book}</li>
 * <li>the number of times the {@code user} visited the {@code book}</li>
 * </ol>
 * 
 * @author Johannes
 *
 */
@Entity
@Table(name = "userbookstatistic", schema = "public")
public class UserBookStatistic {
	private int id;
	private Calendar date;
	private int watchCount;
	private User user;
	private Book book;

	private UserBookStatistic() {
	}

	/**
	 * Constructor and only way to create a new instance of
	 * {@link UserBookStatistic} .
	 * 
	 * {@code User} and {@code book} must not be null!
	 * 
	 * @param user
	 *            the user who visited
	 * @param book
	 *            the book visited by the user
	 * @param date
	 *            the time the book was last visited by the user
	 * @param watchCount
	 *            the number of times the book was visited by the user
	 */
	public UserBookStatistic(User user, Book book, Calendar date, int watchCount) {
		setUser(user);
		setBook(book);
		setDate(date);
		setWatchCount(watchCount);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statisticId")
	public int getId() {
		return id;
	}

	@Column(name = "date", unique = false, nullable = true)
	public Calendar getDate() {
		return date;
	}

	@Column(name = "watchCount", unique = false, columnDefinition = "int default 0")
	public int getWatchCount() {
		return watchCount;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return user;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ISBN", nullable = false)
	public Book getBook() {
		return book;
	}

	public void setId(int id) {
		this.id = id;
	}

	private void setDate(Calendar date) {
		this.date = date;
	}

	private void setWatchCount(int watchCount) {
		this.watchCount = watchCount;
	}

	private void setUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("User"));
		}
		this.user = user;
	}

	private void setBook(Book book) {
		if (book == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("Book"));
		}
		this.book = book;
	}

	@Override
	public String toString() {
		return "UserBookStatistic [id=" + id + ", date=" + date.getTime().toString() + ", watchCount=" + watchCount
				+ ", user=" + user.getEmail() + ", book=" + book.getTitle() + "]";
	}

}
