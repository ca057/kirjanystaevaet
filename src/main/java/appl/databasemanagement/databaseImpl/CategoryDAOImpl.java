package appl.databasemanagement.databaseImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.databasemanagement.CategoryDAO;
import appl.databasemanagement.Searchfields;
import appl.items.Book;
import appl.items.Category;

@Service
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> getCategories() {
		return sessionFactory.getCurrentSession().createQuery("select distinct categoryName from Category").list();
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
