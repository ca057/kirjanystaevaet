package appl.data.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import appl.data.enums.Searchfields;
import appl.data.items.Book;
import appl.data.items.Category;

public interface CategoryDAO {

	@Transactional
	public List<Category> getCategories();

	@Transactional
	public List<Category> getCategoriesByName(String categoryName);

	@Transactional
	public boolean insertCategory(Category category);

	@Transactional
	public boolean updateCategory(Book book, Map<Searchfields, String> map);

}
