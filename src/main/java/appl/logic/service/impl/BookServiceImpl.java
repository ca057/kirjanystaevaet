package appl.logic.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appl.data.builder.AuthorBuilder;
import appl.data.builder.BookBuilder;
import appl.data.builder.CategoryBuilder;
import appl.data.builder.impl.AuthorBuilderImpl;
import appl.data.builder.impl.BookBuilderImpl;
import appl.data.builder.impl.CategoryBuilderImpl;
import appl.data.dao.AuthorDAO;
import appl.data.dao.BookDAO;
import appl.data.dao.CategoryDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import appl.logic.service.BookService;
import exceptions.data.AuthorMayExistException;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO dao; // TODO umbennen in bookDao
	@Autowired
	AuthorDAO authorDao;
	@Autowired 
	CategoryDAO categoryDao;
	

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
		// TODO alles entfernen, was nicht Zahl oder Buchstabe ist
		isbn = onlyLeaveLettersAndNumbers(isbn);
		Map<Searchfields, String> map = new HashMap<Searchfields, String>();
		map.put(Searchfields.isbn, isbn);

		List<Book> bookList = dao.getBooksByMetadata(map);
		if (bookList.size() > 1){
			// TODO Fehlerbehandlung
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
	private String onlyLeaveLettersAndNumbers(String s){
		//System.out.println(s);
		s = s.replaceAll("[^a-zA-Z0-9]", "");
		//System.out.println(s);
		return s;
	}

	@Override
	public void insertBook(Map<Searchfields, String> map, boolean newAuthor) throws AuthorMayExistException {
		BookBuilder bb = new BookBuilderImpl();
		double price = Double.parseDouble(map.get(Searchfields.price));
		int pages = Integer.parseInt(map.get(Searchfields.pages));
		// Testen, ob es diese Kategorie schon gibt
		List<Category> cats = categoryDao.getCategoriesByName(map.get(Searchfields.categoryName));
		Category category = null;
		for (Category cat : cats){
			if (cat.getCategoryName().equals(map.get(Searchfields.categoryName))){
				category = cat;
				break;
			}
		}
		
		// Wenn es die Kategorie noch nicht gab
		if (category == null){
			CategoryBuilder cb = new CategoryBuilderImpl();
			category = cb.setCategoryName(map.get(Searchfields.categoryName)).createCategory();
		}
		// TODO Testen, ob ich hier save(category) category sagen muss, oder ob das über Cascade funktioniert
		
		// Abfragen, ob sich die View sicher ist, ob es sich um einen neuen Autor handelt
		if ( !newAuthor){
			
		
			// Abfragen, ob es Autor mit diesem Vor- und Nachnamen schon gibt
			List<Author> authors = authorDao.getAuthorByExactNames(map.get(Searchfields.nameF), map.get(Searchfields.nameL));
			// Wenn es mehr als einen Autor mit dem Namen gibt: 
			// Hier mit als Parameter übergeben: Einen boolean, Standardmäßig auf false -> Person gibt es noch nicht. 
			if(authors.size() > 0){
				throw new AuthorMayExistException("This Author " + map.get(Searchfields.nameF) + " " + map.get(Searchfields.nameL) + " may already exist");
			}
		}
		AuthorBuilder ab = new AuthorBuilderImpl();
		Author author = ab.setNameF(map.get(Searchfields.nameF)).setNameL(map.get(Searchfields.nameL)).createAuthor();
		
		// TODO Testen, ob ich hier save(author) sagen muss
		
		// TODO Wo wird Form von PubDate überprüft? Muss hier übeprüft werden -> Illegal ArgumentException, ganz am Anfang
		
		Book newBook = bb.setAuthors(authors).set.setIsbn(map.get(Searchfields.isbn)).setTitle(map.get(Searchfields.title)).setDescription(map.get(Searchfields.description)).setPrice(price).setPublisher(map.get(Searchfields.publisher)).setPubdate(map.get(Searchfields.pubdate)).setEdition(map.get(Searchfields.edition)).setPages(pages).createBook();
		
		dao.insertBook(newBook);
		
	}

}
