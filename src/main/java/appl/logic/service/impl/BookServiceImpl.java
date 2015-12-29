package appl.logic.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.dao.BookDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Book;
import appl.logic.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO dao;

	@Override
	public List<Book> getAllBooks() {
		return dao.getAllBooks();
	}

	// ToDo die MEthode funktioniert nur darüber, dass man über CategoryNAme bekommt, nicht über die ID, -> Umbenennen!
	@Override
	public List<Book> getBooksByCategory(String category) {
		Map<Searchfields, String> map = new HashMap<Searchfields, String>();
		map.put(Searchfields.categoryName, category);
		return dao.getBooksByMetadata(map);
		//return dao.getBooksByCategory(category);
		//return null;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Map<Searchfields, String> map = new HashMap<Searchfields, String>();
		map.put(Searchfields.isbn, isbn);

		List<Book> bookList = dao.getBooksByMetadata(map);
		if (bookList.size() > 1){
			// todo Fehlerbehandlung
			System.out.println("Something went totally wrong");
			System.out.println("Listsize is: " + bookList.size());
			System.out.println("See whole content:");
			
			for (Book b : bookList){
				System.out.println(b.toString());
			}
		}
		return bookList.get(0);
	}

	@Override
	public List<Book> getBooksByOpenSearch(String searchTerm) {
		// Erstmal String splitten
		Set<String> searchTerms = splitTermByWhitespaces(searchTerm);
		
		return null;
	}

	@Override
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map) {
		return dao.getBooksByMetadata(map);
	}
	
	private Set<String> splitTermByWhitespaces (String term){
		Set<String> terms = new HashSet<String>(Arrays.asList(term.split("\\s+")));
		System.out.println("Search Terms\n");
		for (String s : terms){
			System.out.println(s);
		}
		return terms;
	}

}
