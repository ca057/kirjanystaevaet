package appl.data.builder;

import appl.data.items.Category;

public interface CategoryBuilder {
	public CategoryBuilder setCategoryName(String categoryName);
	public Category createCatgory();

}
