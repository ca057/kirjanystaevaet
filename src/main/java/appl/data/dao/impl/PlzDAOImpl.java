package appl.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.PlzDAO;
import appl.data.items.PLZ;
import appl.enums.Userfields;
import exceptions.data.DatabaseException;
import exceptions.data.ErrorMessageHelper;

@Repository
public class PlzDAOImpl implements PlzDAO {

	@Autowired
	SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Criteria setupAndGetCriteria() {
		if (sessionFactory == null) {
			throw new RuntimeException("[Error] SessionFactory is null");
		}
		Criteria cr = getSession().createCriteria(PLZ.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return cr;
	}

	@Override
	public boolean insertPLZ(String postalCode, String place) throws DatabaseException {
		try {
			getSession().save(new PLZ(postalCode, place));
			return true;
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public boolean updatePLZ(int plzId, String postalCode, String place) throws DatabaseException {
		try {
			getSession().update(new PLZ(plzId, postalCode, place));
			return true;
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public Optional<PLZ> getPLZ(int plzId) {
		return Optional.ofNullable((PLZ) setupAndGetCriteria().add(Restrictions.idEq(plzId)).uniqueResult());
	}

	@Override
	public Optional<PLZ> getPLZ(String postalCode, String place) {
		return Optional.ofNullable(
				(PLZ) setupAndGetCriteria().add(Restrictions.ilike(Userfields.postcode.toString(), postalCode))
						.add(Restrictions.ilike(Userfields.place.toString(), place)).uniqueResult());
	}

	@Override
	public Optional<Integer> getPLZId(String postalCode, String place) {
		return Optional.ofNullable(
				((PLZ) setupAndGetCriteria().add(Restrictions.ilike(Userfields.postcode.toString(), postalCode))
						.add(Restrictions.ilike(Userfields.place.toString(), place)).uniqueResult()).getPlzId());
	}

	@Override
	public List<PLZ> getPLZByPostalCode(String postalCode) {
		return setupAndGetCriteria().add(Restrictions.ilike(Userfields.postcode.toString(), postalCode)).list();
	}

	@Override
	public List<PLZ> getPLZByPlace(String place) {
		return setupAndGetCriteria().add(Restrictions.ilike(Userfields.place.toString(), place)).list();
	}

}
