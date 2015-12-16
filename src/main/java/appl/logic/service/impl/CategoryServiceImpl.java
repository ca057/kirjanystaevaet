package appl.logic.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.dao.CategoryDAO;
import appl.data.items.Category;
import appl.logic.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO dao;

	@Override
	public List<String> getAllCategoryNames() {
		List<Category> categories = dao.getCategories();
		List<String> names = new LinkedList<String>();
		for (Category ct : categories) {
			names.add(ct.getCategoryName());
			System.out.println(ct.getCategoryName());
		}
		if (names.isEmpty()) {
			throw new RuntimeException("aösdlkfj");
		}
		return names;
	}

	@Override
	public List<String> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
