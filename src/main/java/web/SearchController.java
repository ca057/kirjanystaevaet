package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class SearchController {

	@RequestMapping(value = "/suche", method = RequestMethod.GET)
	public String query(
			@RequestParam(value="title") String title,
			@RequestParam(value="author") String author,
			@RequestParam(value="isbn") String isbn,
			@RequestParam(value="year") String year,
			@RequestParam(value="category") String category,
			Model m
			) {
				return "search";
			}
}
