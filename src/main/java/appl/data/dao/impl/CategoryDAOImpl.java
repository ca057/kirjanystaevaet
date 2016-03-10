package appl.data.dao.impl;
/**
 * @author Madeleine
 */
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.CategoryDAO;
import appl.data.items.Category;
import exceptions.data.EntityDoesNotExistException;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	
	private Criteria setupAndGetCriteria() {
		if (sessionFactory == null) {
			throw new RuntimeException("[Error] SessionFactory is null");
		}
		Session s = getSession();
		Criteria cr = s.createCriteria(Category.class);
		cr.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return cr;
		//return cr.createAlias("books", "b").createAlias("authors", "a");
		//return cr.createAlias("books", "b"); // Category hat keinen Author -> kein Alias dafür angeben
	}


	@Override
	public List<Category> getCategories() {
		return getSession().createCriteria(Category.class).list();
	}
	@Override
	public Category getCategoriesByExactName(String name) throws EntityDoesNotExistException {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq("categoryName", name).ignoreCase());
		Object result = cr.uniqueResult();
		if ( result != null){
			Category cat = (Category) result;
			return cat;
		} else {
			throw new EntityDoesNotExistException();
		}
		
	}

	@Override
	public List<Category> getCategoriesByName(String categoryName) {
		
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.ilike("categoryName", "%" + categoryName + "%" ));
		return cr.list();
	}

	@Override
	public int insertCategory(Category category) {
		int id = (int) getSession().save(category);
		return id;
	}

	@Override
	public void updateCategory(Category category) {
		getSession().update(category);
	}


	@Override
	public Category getCategoryById(int id) throws EntityDoesNotExistException {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq("categoryID", id));
		Object result = cr.uniqueResult();
		if ( result != null){
			Category cat = (Category) result;
			return cat;
		} else {
			throw new EntityDoesNotExistException();
		}
	}
	@Override
	public void deleteCategory(int id){
		Category category = getSession().get(Category.class, id);			             
		getSession().delete(category);
	}



	
}

