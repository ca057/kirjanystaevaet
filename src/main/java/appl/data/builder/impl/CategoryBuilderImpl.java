package appl.data.builder.impl;

import appl.data.builder.CategoryBuilder;
import appl.data.items.Category;

public class CategoryBuilderImpl implements CategoryBuilder{

	private String categoryName;
	public CategoryBuilderImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CategoryBuilder setCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}

	@Override
	public Category createCatgory() {
		return new Category(this.categoryName);
	}

}
