package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Category;
import exceptions.data.EntityDoesNotExistException;

@Transactional
public interface CategoryDAO {

	public List<Category> getCategories();

	public List<Category> getCategoriesByName(String categoryName);
	/**
	 * Needs exact Name but is still case-insensitive
	 * @param name
	 * @return
	 * @throws EntityDoesNotExistException
	 */
	
	public Category getCategoriesByExactName(String name) throws EntityDoesNotExistException;
	
	public Category getCategoryById(int id) throws EntityDoesNotExistException;

	public int insertCategory(Category category);

	public void updateCategory(Category category);
	
	public void deleteCategory(int id);

}
