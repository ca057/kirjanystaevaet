package appl.data.builder.impl;

import appl.data.builder.CategoryBuilder;
import appl.data.items.Category;
/**
 * @author Madeleine
 *
 */
public class CategoryBuilderImpl implements CategoryBuilder{

	private int categoryId = -1;
	private String categoryName;
	public CategoryBuilderImpl() {
	}

	@Override
	public CategoryBuilder setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	@Override
	public CategoryBuilder setCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}

	@Override
	public Category createCategory() {
		if(categoryId < 0 ){
			return new Category(this.categoryId, this.categoryName );
		} else {
			return new Category(this.categoryName);
	
		}
	}

	

}
