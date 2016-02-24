
package appl.data.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.UserDAO;
import appl.data.enums.Searchfields;
import appl.data.enums.Userfields;
import appl.data.items.User;
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
		} catch (Exception e) {
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
		} catch (Exception e) {
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

		} catch (Exception e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
		return Optional.ofNullable(user);
	}

	@Override
	public int insertUser(User user) {
		return (Integer) getSession().save(user);
	}

	@Override
	public boolean deleteUser(User user) throws DatabaseException {
		try {
			getSession().delete(user);
			return true;
		} catch (Exception e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public boolean updateUser(User user, Map<Searchfields, String> map) throws DatabaseException {
		// TODO update(USER)? Eine Ebene drüber müsste das
		// jeweilige zu ändernde Feld via USER.set() geändert werden
		try {
			// TODO implement this.
			return true;
		} catch (Exception e) {
			throw new DatabaseException(
					ErrorMessageHelper.updateError("User", String.valueOf(user.getUserId()), e.getMessage()));
		}
	}

}
