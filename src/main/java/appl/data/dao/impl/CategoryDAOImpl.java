package appl.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Criteria cr = s.createCriteria(Book.class);
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return cr.createAlias("categories", "c").createAlias("authors", "a");
	}


	@Override
	public List<Category> getCategories() {
		// TODO implement this!
		//Criteria cr = setupAndGetCriteria();
		//cr.add
		return getSession().createCriteria(Category.class).list();
	}

	@Override
	public List<Category> getCategoriesByName(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCategory(Category category) {
		// TODO implement this!
	}

	@Override
	public void updateCategory(Book book, Map<Searchfields, String> map) {
		// TODO Auto-generated method stub
	}

}
