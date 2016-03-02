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

	@Column(name = "date", unique = false, nullable = false)
	public Calendar getDate() {
		return date;
	}

	@Column(name = "watchCount", unique = false, columnDefinition = "int default 0")
	public int getWatchCount() {
		return watchCount;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = true)
	public User getUser() {
		return user;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ISBN", nullable = true)
	public Book getBook() {
		return book;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setWatchCount(int watchCount) {
		this.watchCount = watchCount;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "UserBookStatistic [id=" + id + ", date=" + date + ", watchCount=" + watchCount + ", user=" + user
				+ ", book=" + book + "]";
	}

}
