package appl.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import appl.data.dao.CategoryDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Book;
import appl.data.items.Category;

@Component
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Category> getCategories() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	}

	@Override
	public List<Category> getCategoriesByName(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertCategory(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCategory(Book book, Map<Searchfields, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

}