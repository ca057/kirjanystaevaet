package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.Book;
import appl.data.items.Category;
import appl.enums.Searchfields;
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

	public void updateCategory(Book book, Map<Searchfields, String> map);
	
	public void deleteCategory(int id);

}
