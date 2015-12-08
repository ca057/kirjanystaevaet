package appl.data.dao;

import java.util.List;
import java.util.Map;

import appl.data.items.Book;
import appl.data.items.Category;

public interface CategoryDAO {

	public List<Category> getCategories();

	public List<Category> getCategoriesByName(String categoryName);

	public boolean insertCategory(Category category);

	public boolean updateCategory(Book book, Map<Searchfields, String> map);

}
