 package appl.logic.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
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
import exceptions.data.AuthorNotFoundException;
import exceptions.data.CategoryExistsException;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;
import exceptions.data.IsbnAlreadyExistsException;
import exceptions.data.PrimaryKeyViolationException;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO bookDao; 
	@Autowired
	AuthorDAO authorDao;
	@Autowired 
	CategoryDAO categoryDao;
	

	@Override
	public Category getCategoryByExactName(String name) throws EntityDoesNotExistException {
		Category category = categoryDao.getCategoriesByExactName(name);
		return category;
	}

	@Override
	public Category getCategoryById(int id) throws EntityDoesNotExistException {
		Category category = categoryDao.getCategoryById(id);
		return category;
	}

	@Override
	public List<String> getAllCategoryNames() {
		
		List<Category> categories = categoryDao.getCategories();
		List<String> names = new LinkedList<String>();
		for (Category ct : categories) {
			names.add(ct.getCategoryName());
			//System.out.println(ct.getCategoryName());
		}
		if (names.isEmpty()) {
			// TODO
			//throw new RuntimeException("aösdlkfj");
		}
		return names;
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getCategories();
	}

	@Override
	public int insertCategory(String name) throws CategoryExistsException {
		// Prüfen, ob es Category schon gibt
		
		try{
			Category cat = categoryDao.getCategoriesByExactName(name);
		} catch(EntityDoesNotExistException e){
			System.out.println("Service.inserCategory im catch-Block");
			CategoryBuilder cb = new CategoryBuilderImpl();
			Category cat = cb.setCategoryName(name).createCatgory();
			List<Category> categories = categoryDao.getCategories();
			System.out.println("List of all Categories\n\n");
			for (Category c : categories){
				System.out.println(c.getCategoryName() + " " + c.getCategoryID());
			}
			int id = categoryDao.insertCategory(cat);
			System.out.println("In Bookservice.insertCtaegory name = " + name + " id = " + id);
			return id;
			
		}
		throw new CategoryExistsException();

	}
	public void deleteCategory(String name){
		// Prüfen, ob Category vorhanden
		/*try {
			categoryDao.getCategoriesByExactName(name);
		}
		*/
		
		// Kategorie holen
		try {
			Category category = categoryDao.getCategoriesByExactName(name);
			categoryDao.deleteCategory(category.getCategoryID());
		} catch (EntityDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/*
	@Override
	public void insertBook(Map<Searchfields, String> map, boolean newAuthor)  {
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
	*/
	
	/*
	@Override
	public void insertBook(Map<Searchfields, String> map, boolean newAuthor)  {
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
	*/
	
	@Override
	public List<Author> getAuthorByExactName(String nameF, String nameL) {
		List<Author> authors = authorDao.getAuthorByExactNames(nameF, nameL);
		
		return authors;
	}

	@Override
	public Author getAuthorById(int id) throws EntityDoesNotExistException {
		Author author;
		author = authorDao.getAuthorByID(id);
		return author;
	
	}

	@Override
	public List<Author> getAllAuthors() {
	
		return authorDao.getAuthors();
	}

	@Override
	public int insertAuthor(String nameF, String nameL, boolean newAuthor) throws AuthorMayExistException {
		
		// Wenn noch kein bestimmter Autor ausgewählt wurde
		if(!newAuthor){
			List<Author> authors = authorDao.getAuthorByExactNames(nameF, nameL);
			// Testen ob es schon Autoren mit dem Namen gibt
			if(authors.size() > 0){
				throw new AuthorMayExistException("This Author " + nameF + " " + nameL + " may already exist");
			}
		}
	
		// Wenn ein neuer Autor eingefügt werden soll
		AuthorBuilder ab = new AuthorBuilderImpl();
		Author author = ab.setNameF(nameF).setNameL(nameL).createAuthor();
		System.out.println("\n\nEinmal den Autor checken\n\n" + author.toString());
		int id = authorDao.insertAuthor(author);
		// TODO in der Datenbank speichern? -> Ja und dann nur die ID zurück geben
		System.out.println("BookserviceImpl. insert Book nach insertAuthor\n\n\n id = " + id);
		return id;
		
	}
	@Override
	public void deleteAuthor(Author author){
		authorDao.deleteAuthor(author);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	// ToDo die MEthode funktioniert nur darüber, dass man über CategoryNAme bekommt, nicht über die ID, -> Umbenennen!
	@Override
	public List<Book> getBooksByCategory(String category) {
		Map<Searchfields, String> map = new HashMap<Searchfields, String>();
		map.put(Searchfields.categoryName, category);
		return bookDao.getBooksByMetadata(map);
		//return dao.getBooksByCategory(category);
		//return null;
	}

	
	@Override
	public Book getBookByIsbn(String isbn) {
		// TODO So umbauen das mit UniqueResult gearbeitet wird
		// TODO alles entfernen, was nicht Zahl oder Buchstabe ist
		isbn = onlyLeaveLettersAndNumbers(isbn);
		Map<Searchfields, String> map = new HashMap<Searchfields, String>();
		map.put(Searchfields.isbn, isbn);

		List<Book> bookList = bookDao.getBooksByMetadata(map);
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
		return bookDao.getBooksByMetadata(map);
	}
	
	/*
	@Override
	public void insertBook(Map<Searchfields, String> map, boolean newAuthor)  {
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
	*/
	
	@Override
	//public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds) throws IsbnAlreadyExistsException {
	public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds) throws PrimaryKeyViolationException, EntityDoesNotExistException, DatabaseException {
	
		BookBuilder bb = new BookBuilderImpl();
		double price = Double.parseDouble(map.get(Searchfields.price));
		int pages = Integer.parseInt(map.get(Searchfields.pages));
		
		Set<Category> categories = new HashSet<Category>();
		for (int i : categoryIds){
			categories.add(categoryDao.getCategoryById(i));
		}
		System.out.println("in service.insertBook bevor die Autoren zusammen gesammelt werden\n\n" );
		
		Set<Author> authors = new HashSet<Author>();
	
		for (int i : authorIds){
			authors.add(authorDao.getAuthorByID(i));
		}
		Book newBook = bb.setAuthors(authors).setIsbn(map.get(Searchfields.isbn)).setTitle(map.get(Searchfields.title)).setDescription(map.get(Searchfields.description)).setPrice(price).setPublisher(map.get(Searchfields.publisher)).setPubdate(map.get(Searchfields.pubdate)).setEdition(map.get(Searchfields.edition)).setPages(pages).setCategories(categories).createBook();
	
		try {
			bookDao.insertBook(newBook);
		} catch (ConstraintViolationException e){
			throw new PrimaryKeyViolationException();
		} catch (HibernateException e){
			throw new DatabaseException();
		}
		
		
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

	/*
	@Override
	public void insertBook(Map<Searchfields, String> map, boolean newAuthor)  {
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
	*/

	

}

