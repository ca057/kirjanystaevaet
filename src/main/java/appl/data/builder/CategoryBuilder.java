package appl.data.builder;

import appl.data.items.Category;

/**
 * Builder to create a new object of the {@link Category} class.
 * 
 * @author Madeleine
 *
 */
public interface CategoryBuilder {
	
	public CategoryBuilder setCategoryId(int categoryId);

	public CategoryBuilder setCategoryName(String categoryName);

	public Category createCategory();

}
