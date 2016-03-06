
package appl.data.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.UserDAO;
import appl.data.items.Book;
import appl.data.items.User;
import appl.data.items.UserBookStatistic;
import appl.enums.Userfields;
import exceptions.data.DatabaseException;
import exceptions.data.ErrorMessageHelper;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Criteria setupAndGetCriteria() {
		if (sessionFactory == null) {
			throw new RuntimeException("[Error] SessionFactory is null");
		}
		Criteria cr = getSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// return cr.createAlias("PLZ", "p");
		return cr;
	}

	@Override
	public List<User> getUsers() throws DatabaseException {
		try {
			return setupAndGetCriteria().list();
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public List<User> getUserByMetadata(Map<Userfields, String> map) throws DatabaseException {
		Criteria cr = setupAndGetCriteria();
		map.forEach((field, data) -> {
			cr.add(Restrictions.ilike(field.toString(), data));
		});
		try {
			return cr.list();
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public Optional<User> getUserByUniqueField(Userfields field, String value) throws DatabaseException {
		User user = null;
		try {
			switch (field) {
			case userId:
				user = (User) setupAndGetCriteria().add(Restrictions.idEq(Integer.valueOf(value))).uniqueResult();
				break;
			case email:
				user = (User) setupAndGetCriteria().add(Restrictions.eq(Userfields.email.toString(), value))
						.uniqueResult();
			default:
				break;
			}

		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
		return Optional.ofNullable(user);
	}

	@Override
	public int insertUser(User user) {
		System.out.println(user.toString());
		return (Integer) getSession().save(user);
	}

	@Override
	public boolean deleteUser(User user) throws DatabaseException {
		try {
			getSession().delete(user);
			return true;
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public boolean updateUser(User updatedUser) throws DatabaseException {
		try {
			System.out.println("Neues Passwort: " + updatedUser.getPassword());
			getSession().update(updatedUser);
			return true;
		} catch (HibernateException e) {
			throw new DatabaseException(
					ErrorMessageHelper.updateError("User", String.valueOf(updatedUser.getUserId()), e.getMessage()));
		}
	}

	@Override
	public List<UserBookStatistic> getUserBookStatistics(int userId) throws DatabaseException {
		User user = getUserByUniqueField(Userfields.userId, String.valueOf(userId))
				.orElseThrow(() -> new DatabaseException(ErrorMessageHelper.entityDoesNotExist("User")));
		List<UserBookStatistic> result = new ArrayList<UserBookStatistic>();
		return getSession().createCriteria(UserBookStatistic.class).list();
		// TODO Methode für get() anbieten?
		// result.addAll(user.getUserBookStatistics());
		// return result;
	}

	@Override
	public boolean updateUserBookStatistic(User user, Book book, Calendar date) throws DatabaseException {
		Optional<UserBookStatistic> statistic = Optional.ofNullable((UserBookStatistic) getSession()
				.createCriteria(UserBookStatistic.class).add(Restrictions.like("user.userId", user.getUserId()))
				.add(Restrictions.like("book.isbn", book.getIsbn())).uniqueResult());

		System.out.println("Ergebnis von Criteria: " + getSession().createCriteria(UserBookStatistic.class)
				.add(Restrictions.like("user.userId", user.getUserId()))
				.add(Restrictions.like("book.isbn", book.getIsbn())).uniqueResult());

		if (statistic.isPresent()) {
			System.out.println("WatchCount erhoeht sich um 1.");
			return updateUserBookStatistic(
					new UserBookStatistic(user, book, date, statistic.get().getWatchCount() + 1));
		} else {
			System.out.println("WatchCount wird mit 1 angelegt");
			return updateUserBookStatistic(new UserBookStatistic(user, book, date, 1));
		}
	}

	private boolean updateUserBookStatistic(UserBookStatistic statistic) throws DatabaseException {
		try {
			getSession().saveOrUpdate(statistic);
			return true;
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	// private boolean saveUserBookStatistic(UserBookStatistic statistic) throws
	// DatabaseException {
	// try {
	// getSession().save(statistic);
	// return true;
	// } catch (HibernateException e) {
	// throw new
	// DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
	// }
	// }

}
