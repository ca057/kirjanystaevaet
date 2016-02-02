package appl.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import appl.data.dao.CategoryDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Book;
import appl.data.items.Category;

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
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		//return cr.createAlias("books", "b").createAlias("authors", "a");
		return cr.createAlias("books", "b"); // Category hat keinen Author -> kein Alias dafür angeben
	}


	@Override
	public List<Category> getCategories() {
		// TODO implement this!
		//Criteria cr = setupAndGetCriteria();
		//cr.add
		return getSession().createCriteria(Category.class).list();
	}
	@Override
	public Category getCategoriesByExactName(String name) {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq("categoryName", name));
		Object result = cr.uniqueResult();
		if ( result != null){
			Category cat = (Category) result;
			return cat;
		} else {
			//TODO Fehlerbehandlung -> Category does not exist exception
		}
		
		return null;
	}


	@Override
	public List<Category> getCategoriesByName(String categoryName) {
		
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.ilike("categoryName", "%" + categoryName + "%" ));
		return (List<Category>) cr.list();
	}

	@Override
	public void insertCategory(Category category) {
		// TODO implement this!
	}

	@Override
	public void updateCategory(Book book, Map<Searchfields, String> map) {
		// TODO Auto-generated method stub
	}


	@Override
	public Category getCategoryById(int id) {
		Criteria cr = setupAndGetCriteria();
		cr.add(Restrictions.eq("categoryID", id));
		Object result = cr.uniqueResult();
		if ( result != null){
			Category cat = (Category) result;
			return cat;
		} else {
			//TODO Fehlerbehandlung -> Category does not exist exception
		}
		return null;
	}


	
}

