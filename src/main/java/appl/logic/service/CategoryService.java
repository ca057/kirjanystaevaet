package appl.logic.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

	public List<String> getAllCategories();

	List<String> getAllCategoryNames();

}
