package appl.data.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Category;
import exceptions.data.EntityDoesNotExistException;

/**
 * @author Madeleine
 *
 */
@Transactional
public interface CategoryDAO {
	/**
	 * @return {@link List} of all existing Categories
	 */
	public List<Category> getCategories();

	/**
	 * @param categoryName
	 * @return {@link List} of Categories that contain the categoryName
	 */

	public List<Category> getCategoriesByName(String categoryName);

	/**
	 * Needs exact Name but is still case-insensitive
	 * 
	 * @param name
	 * @return
	 * @throws EntityDoesNotExistException
	 */

	public Category getCategoriesByExactName(String name) throws EntityDoesNotExistException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws EntityDoesNotExistException
	 */
	public Category getCategoryById(int id) throws EntityDoesNotExistException;

	/**
	 * 
	 * @param category
	 * @return
	 */
	public int insertCategory(Category category);

	/**
	 * Updates a category by the data in the parameter object
	 * 
	 * @param category
	 */
	public void updateCategory(Category category);

	/**
	 * Physically deletes {@link Category} with given Id
	 * 
	 * @param id
	 */

	public void deleteCategory(int id);

}
