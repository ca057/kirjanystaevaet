package appl.data.dao.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public List<Category> getCategories() {
		// TODO implement this!
		return null;
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
