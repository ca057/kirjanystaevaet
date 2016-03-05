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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import appl.data.builder.AuthorBuilder;
import appl.data.builder.BookBuilder;
import appl.data.builder.BuilderFactory;
import appl.data.builder.CategoryBuilder;
import appl.data.dao.AuthorDAO;
import appl.data.dao.BookDAO;
import appl.data.dao.CategoryDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import appl.logic.service.BookService;
import exceptions.data.AuthorMayExistException;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;
import exceptions.data.ErrorMessageHelper;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO bookDao;
	@Autowired
	AuthorDAO authorDao;
	@Autowired
	CategoryDAO categoryDao;
	@Autowired
	BuilderFactory builderFactory;

	@Override
	public Category getCategoryByExactName(String name) throws DatabaseException {
		try {
			Category category = categoryDao.getCategoriesByExactName(name);
			return category;
		} catch (EntityDoesNotExistException e) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Category"));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public Category getCategoryById(int id) throws DatabaseException {
		try {
			Category category = categoryDao.getCategoryById(id);
			return category;

		} catch (EntityDoesNotExistException e) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Category"));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}

	}

	@Override
	public List<String> getAllCategoryNames() throws DatabaseException {
		try {
			List<Category> categories = categoryDao.getCategories();
			List<String> names = new LinkedList<String>();
			for (Category ct : categories) {
				names.add(ct.getCategoryName());
				// System.out.println(ct.getCategoryName());
			}
			return names;
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	/*
	 * @Override public List<String> getAllCategoryNames() {
	 * 
	 * List<Category> categories = categoryDao.getCategories(); List<String>
	 * names = new LinkedList<String>(); for (Category ct : categories) {
	 * names.add(ct.getCategoryName());
	 * //System.out.println(ct.getCategoryName()); } return names;
	 * 
	 * 
	 * }
	 */
	/*
	 * @Override public List<Category> getAllCategories() { return
	 * categoryDao.getCategories(); }
	 */

	@Override
	public List<Category> getAllCategories() throws DatabaseException {
		try {
			return categoryDao.getCategories();
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public int insertCategory(String name) throws DatabaseException {
		// Prüfen, ob es Category schon gibt

		try {
			Category cat = categoryDao.getCategoriesByExactName(name);
		} catch (EntityDoesNotExistException e) {
			try {
				CategoryBuilder cb = builderFactory.getCategoryBuilder();
				Category cat = cb.setCategoryName(name).createCategory();
				List<Category> categories = categoryDao.getCategories();
				System.out.println("List of all Categories\n\n");
				for (Category c : categories) {
					System.out.println(c.getCategoryName() + " " + c.getCategoryID());
				}
				int id = categoryDao.insertCategory(cat);
				return id;
			} catch (HibernateException f) {
				throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(f.getMessage()));
			}

		}
		throw new DatabaseException(ErrorMessageHelper.entityDoesAlreadyExist("Category"));

	}

	public void deleteCategory(String name) throws DatabaseException {
		// Prüfen, ob Category vorhanden
		/*
		 * try { categoryDao.getCategoriesByExactName(name); }
		 */

		// Kategorie holen
		try {
			Category category = categoryDao.getCategoriesByExactName(name);
			categoryDao.deleteCategory(category.getCategoryID());
		} catch (EntityDoesNotExistException e) {

			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Category"));
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(ErrorMessageHelper.DataIntegrityViolation("Category", "Book", e.getMessage()));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}

	}

	/*
	 * @Override public void insertBook(Map<Searchfields, String> map, boolean
	 * newAuthor) { BookBuilder bb = new BookBuilderImpl(); double price =
	 * Double.parseDouble(map.get(Searchfields.price)); int pages =
	 * Integer.parseInt(map.get(Searchfields.pages)); // Testen, ob es diese
	 * Kategorie schon gibt List<Category> cats =
	 * categoryDao.getCategoriesByName(map.get(Searchfields.categoryName));
	 * Category category = null; for (Category cat : cats){ if
	 * (cat.getCategoryName().equals(map.get(Searchfields.categoryName))){
	 * category = cat; break; } }
	 * 
	 * // Wenn es die Kategorie noch nicht gab if (category == null){
	 * CategoryBuilder cb = new CategoryBuilderImpl(); category =
	 * cb.setCategoryName(map.get(Searchfields.categoryName)).createCategory();
	 * } // TODO Testen, ob ich hier save(category) category sagen muss, oder ob
	 * das über Cascade funktioniert
	 * 
	 * // Abfragen, ob sich die View sicher ist, ob es sich um einen neuen Autor
	 * handelt if ( !newAuthor){
	 * 
	 * 
	 * // Abfragen, ob es Autor mit diesem Vor- und Nachnamen schon gibt
	 * List<Author> authors =
	 * authorDao.getAuthorByExactNames(map.get(Searchfields.nameF),
	 * map.get(Searchfields.nameL)); // Wenn es mehr als einen Autor mit dem
	 * Namen gibt: // Hier mit als Parameter übergeben: Einen boolean,
	 * Standardmäßig auf false -> Person gibt es noch nicht. if(authors.size() >
	 * 0){ throw new AuthorMayExistException("This Author " +
	 * map.get(Searchfields.nameF) + " " + map.get(Searchfields.nameL) +
	 * " may already exist"); } } AuthorBuilder ab = new AuthorBuilderImpl();
	 * Author author =
	 * ab.setNameF(map.get(Searchfields.nameF)).setNameL(map.get(Searchfields.
	 * nameL)).createAuthor();
	 * 
	 * // TODO Testen, ob ich hier save(author) sagen muss
	 * 
	 * // TODO Wo wird Form von PubDate überprüft? Muss hier übeprüft werden ->
	 * Illegal ArgumentException, ganz am Anfang
	 * 
	 * Book newBook =
	 * bb.setAuthors(authors).set.setIsbn(map.get(Searchfields.isbn)).setTitle(
	 * map.get(Searchfields.title)).setDescription(map.get(Searchfields.
	 * description)).setPrice(price).setPublisher(map.get(Searchfields.publisher
	 * )).setPubdate(map.get(Searchfields.pubdate)).setEdition(map.get(
	 * Searchfields.edition)).setPages(pages).createBook();
	 * 
	 * dao.insertBook(newBook);
	 * 
	 * }
	 */

	/*
	 * @Override public void insertBook(Map<Searchfields, String> map, boolean
	 * newAuthor) { BookBuilder bb = new BookBuilderImpl(); double price =
	 * Double.parseDouble(map.get(Searchfields.price)); int pages =
	 * Integer.parseInt(map.get(Searchfields.pages)); // Testen, ob es diese
	 * Kategorie schon gibt List<Category> cats =
	 * categoryDao.getCategoriesByName(map.get(Searchfields.categoryName));
	 * Category category = null; for (Category cat : cats){ if
	 * (cat.getCategoryName().equals(map.get(Searchfields.categoryName))){
	 * category = cat; break; } }
	 * 
	 * // Wenn es die Kategorie noch nicht gab if (category == null){
	 * CategoryBuilder cb = new CategoryBuilderImpl(); category =
	 * cb.setCategoryName(map.get(Searchfields.categoryName)).createCategory();
	 * } // TODO Testen, ob ich hier save(category) category sagen muss, oder ob
	 * das über Cascade funktioniert
	 * 
	 * // Abfragen, ob sich die View sicher ist, ob es sich um einen neuen Autor
	 * handelt if ( !newAuthor){
	 * 
	 * 
	 * // Abfragen, ob es Autor mit diesem Vor- und Nachnamen schon gibt
	 * List<Author> authors =
	 * authorDao.getAuthorByExactNames(map.get(Searchfields.nameF),
	 * map.get(Searchfields.nameL)); // Wenn es mehr als einen Autor mit dem
	 * Namen gibt: // Hier mit als Parameter übergeben: Einen boolean,
	 * Standardmäßig auf false -> Person gibt es noch nicht. if(authors.size() >
	 * 0){ throw new AuthorMayExistException("This Author " +
	 * map.get(Searchfields.nameF) + " " + map.get(Searchfields.nameL) +
	 * " may already exist"); } } AuthorBuilder ab = new AuthorBuilderImpl();
	 * Author author =
	 * ab.setNameF(map.get(Searchfields.nameF)).setNameL(map.get(Searchfields.
	 * nameL)).createAuthor();
	 * 
	 * // TODO Testen, ob ich hier save(author) sagen muss
	 * 
	 * // TODO Wo wird Form von PubDate überprüft? Muss hier übeprüft werden ->
	 * Illegal ArgumentException, ganz am Anfang
	 * 
	 * Book newBook =
	 * bb.setAuthors(authors).set.setIsbn(map.get(Searchfields.isbn)).setTitle(
	 * map.get(Searchfields.title)).setDescription(map.get(Searchfields.
	 * description)).setPrice(price).setPublisher(map.get(Searchfields.publisher
	 * )).setPubdate(map.get(Searchfields.pubdate)).setEdition(map.get(
	 * Searchfields.edition)).setPages(pages).createBook();
	 * 
	 * dao.insertBook(newBook);
	 * 
	 * }
	 */

	@Override
	public List<Author> getAuthorByExactName(String nameF, String nameL) throws DatabaseException {

		try {
			List<Author> authors = authorDao.getAuthorByExactNames(nameF, nameL);
			return authors;

		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}

	}

	@Override
	public Author getAuthorById(int id) throws DatabaseException {
		try {
			Author author;
			author = authorDao.getAuthorByID(id);
			return author;
		} catch (EntityDoesNotExistException e) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Author"));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}

	}

	@Override
	public List<Author> getAllAuthors() throws DatabaseException {
		try {
			return authorDao.getAuthors();

		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}
	@Override
	public List<Author> getAuthorByIsbn(String isbn) throws DatabaseException {
		try{
			List<Author> authors = authorDao.getAuthorsByIsbn(isbn);
			return authors;

			
		} catch(HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public int insertAuthor(String nameF, String nameL, boolean newAuthor) throws AuthorMayExistException {

		// Wenn noch kein bestimmter Autor ausgewählt wurde
		if (!newAuthor) {
			List<Author> authors = authorDao.getAuthorByExactNames(nameF, nameL);
			// Testen ob es schon Autoren mit dem Namen gibt
			if (authors.size() > 0) {
				throw new AuthorMayExistException("This Author " + nameF + " " + nameL + " may already exist");
			}
		}

		// Wenn ein neuer Autor eingefügt werden soll
		AuthorBuilder ab = builderFactory.getAuthorBuilder();
		Author author = ab.setNameF(nameF).setNameL(nameL).createAuthor();
		System.out.println("\n\nEinmal den Autor checken\n\n" + author.toString());
		int id = authorDao.insertAuthor(author);

		return id;

	}

	@Override
	public void deleteAuthor(int id) throws DatabaseException {
		try {
			Author author = authorDao.getAuthorByID(id);
			authorDao.deleteAuthor(author);
		} catch (EntityDoesNotExistException e) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Author"));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	/*
	 * @Override public List<Book> getAllBooks() { return bookDao.getAllBooks();
	 * }
	 */
	@Override
	public List<Book> getAllBooks() throws DatabaseException {
		try {
			return bookDao.getAllBooks();

		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	// ToDo die MEthode funktioniert nur darüber, dass man über CategoryNAme
	// bekommt, nicht über die ID, -> Umbenennen!
	/*
	 * @Override public List<Book> getBooksByCategory(String category) {
	 * Map<Searchfields, String> map = new HashMap<Searchfields, String>();
	 * map.put(Searchfields.categoryName, category); return
	 * bookDao.getBooksByMetadata(map); //return
	 * dao.getBooksByCategory(category); //return null; }
	 */
	@Override
	public List<Book> getBooksByCategory(String category) throws DatabaseException {
		try {
			Map<Searchfields, String> map = new HashMap<Searchfields, String>();
			map.put(Searchfields.categoryName, category);
			return bookDao.getBooksByMetadata(map);
			// return dao.getBooksByCategory(category);
			// return null;
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public Book getBookByIsbn(String isbn) throws DatabaseException {
		// TODO So umbauen das mit UniqueResult gearbeitet wird
		// TODO alles entfernen, was nicht Zahl oder Buchstabe ist
		isbn = onlyLeaveLettersAndNumbers(isbn);
		try {
			Book book = bookDao.getBookByIsbn(isbn);
			return book;
		} catch (EntityDoesNotExistException e) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Book"));
		}
		/*
		 * Map<Searchfields, String> map = new HashMap<Searchfields, String>();
		 * map.put(Searchfields.isbn, isbn);
		 * 
		 * List<Book> bookList = bookDao.getBooksByMetadata(map); if
		 * (bookList.size() > 1){
		 * 
		 * 
		 * }
		 */
	}

	@Override
	public List<Book> getBooksByOpenSearch(String searchTerm) {
		// Erstmal String splitten
		Set<String> searchTerms = splitTermByWhitespaces(searchTerm);

		return null;
	}

	@Override
	public List<Book> getBooksByMetadata(Map<Searchfields, String> map) throws DatabaseException {
		try {
			return bookDao.getBooksByMetadata(map);

		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	/*
	 * @Override public void insertBook(Map<Searchfields, String> map, boolean
	 * newAuthor) { BookBuilder bb = new BookBuilderImpl(); double price =
	 * Double.parseDouble(map.get(Searchfields.price)); int pages =
	 * Integer.parseInt(map.get(Searchfields.pages)); // Testen, ob es diese
	 * Kategorie schon gibt List<Category> cats =
	 * categoryDao.getCategoriesByName(map.get(Searchfields.categoryName));
	 * Category category = null; for (Category cat : cats){ if
	 * (cat.getCategoryName().equals(map.get(Searchfields.categoryName))){
	 * category = cat; break; } }
	 * 
	 * // Wenn es die Kategorie noch nicht gab if (category == null){
	 * CategoryBuilder cb = new CategoryBuilderImpl(); category =
	 * cb.setCategoryName(map.get(Searchfields.categoryName)).createCategory();
	 * } // TODO Testen, ob ich hier save(category) category sagen muss, oder ob
	 * das über Cascade funktioniert
	 * 
	 * // Abfragen, ob sich die View sicher ist, ob es sich um einen neuen Autor
	 * handelt if ( !newAuthor){
	 * 
	 * 
	 * // Abfragen, ob es Autor mit diesem Vor- und Nachnamen schon gibt
	 * List<Author> authors =
	 * authorDao.getAuthorByExactNames(map.get(Searchfields.nameF),
	 * map.get(Searchfields.nameL)); // Wenn es mehr als einen Autor mit dem
	 * Namen gibt: // Hier mit als Parameter übergeben: Einen boolean,
	 * Standardmäßig auf false -> Person gibt es noch nicht. if(authors.size() >
	 * 0){ throw new AuthorMayExistException("This Author " +
	 * map.get(Searchfields.nameF) + " " + map.get(Searchfields.nameL) +
	 * " may already exist"); } } AuthorBuilder ab = new AuthorBuilderImpl();
	 * Author author =
	 * ab.setNameF(map.get(Searchfields.nameF)).setNameL(map.get(Searchfields.
	 * nameL)).createAuthor();
	 * 
	 * // TODO Testen, ob ich hier save(author) sagen muss
	 * 
	 * // TODO Wo wird Form von PubDate überprüft? Muss hier übeprüft werden ->
	 * Illegal ArgumentException, ganz am Anfang
	 * 
	 * Book newBook =
	 * bb.setAuthors(authors).set.setIsbn(map.get(Searchfields.isbn)).setTitle(
	 * map.get(Searchfields.title)).setDescription(map.get(Searchfields.
	 * description)).setPrice(price).setPublisher(map.get(Searchfields.publisher
	 * )).setPubdate(map.get(Searchfields.pubdate)).setEdition(map.get(
	 * Searchfields.edition)).setPages(pages).createBook();
	 * 
	 * dao.insertBook(newBook);
	 * 
	 * }
	 */

	@Override
	// public void insertBook(Map<Searchfields, String> map, Set<Integer>
	// authorIds, Set<Integer> categoryIds) throws IsbnAlreadyExistsException {
	public void insertBook(Map<Searchfields, String> map, Set<Integer> authorIds, Set<Integer> categoryIds)
			throws DatabaseException {

		// Map abfragen, ob alle obligatorischen Angaben vorhanden sind
		if (map.get(Searchfields.isbn) == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("Isbn"));
		}
		if (map.get(Searchfields.title) == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("Title"));
		}
		if (map.get(Searchfields.price) == null) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("Price"));
		}
		if (authorIds == null || authorIds.size() == 0) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("AuthorIds"));

		}
		if (categoryIds == null || categoryIds.size() == 0) {
			throw new IllegalArgumentException(ErrorMessageHelper.nullOrEmptyMessage("CategoryIds"));

		}

		if (map.get(Searchfields.pages) != null) {
			if (!containsOnlyNumbers(map.get(Searchfields.pages))) {
				throw new IllegalArgumentException(ErrorMessageHelper.mayContainOnlyNumbers("Pages"));
			}
		}

		int stock = 0;
		if (map.get(Searchfields.stock) != null) {
			if (!containsOnlyNumbers(map.get(Searchfields.stock))) {
				throw new IllegalArgumentException(ErrorMessageHelper.mayContainOnlyNumbers("Stock"));
			}
			stock = Integer.parseInt(map.get(Searchfields.stock));
		}

		BookBuilder bb = builderFactory.getBookBuilder();
		double price = Double.parseDouble(map.get(Searchfields.price));

		Set<Category> categories = new HashSet<Category>();
		for (int i : categoryIds) {
			try {
				categories.add(categoryDao.getCategoryById(i));
			} catch (EntityDoesNotExistException e) {
				throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Category"));
			}
		}

		Set<Author> authors = new HashSet<Author>();

		for (int i : authorIds) {
			try {
				authors.add(authorDao.getAuthorByID(i));
			} catch (EntityDoesNotExistException e) {
				throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Author"));
			}
		}
		Book newBook = bb.setAuthors(authors).setIsbn(map.get(Searchfields.isbn)).setTitle(map.get(Searchfields.title))
				.setDescription(map.get(Searchfields.description)).setPrice(price)
				.setPublisher(map.get(Searchfields.publisher)).setPubdate(map.get(Searchfields.pubdate))
				.setEdition(map.get(Searchfields.edition)).setPages(map.get(Searchfields.pages))
				.setCategories(categories).setStock(stock).createBook();

		try {
			bookDao.insertBook(newBook);
		} catch (ConstraintViolationException e) {
			throw new DatabaseException(ErrorMessageHelper.primaryKeyViolation());
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError("Book could not be inserted"));
		}

	}
	
	@Override
	public void updateBook(Map<Searchfields, String> data) throws DatabaseException {
		
	}
	
	@Override
	public void deleteCategoryOfBook(String isbn, int categoryId) throws DatabaseException {
		Book book = bookDao.getBookByIsbn(isbn);
		Set<Category> categories = book.getCategories();
		boolean flag = false;
		Category toBeDeleted = null;
		for(Category c : categories){
			if (c.getCategoryID()== categoryId){
				flag = true;
				toBeDeleted = c;
				break;
			}
			
		}
		if (flag){
			categories.remove(toBeDeleted);
		} else {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Category"));
		}
		try{  
		bookDao.updateBook(book);
		}catch(HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}
	
	@Override
	public void addCategoryToBook(String isbn, int categoryId) throws DatabaseException {
		try{
		Book book = bookDao.getBookByIsbn(isbn);
		Category toBeAdded = categoryDao.getCategoryById(categoryId);
		book.getCategories().add(toBeAdded);
		bookDao.updateBook(book);
		}
		catch(HibernateException e){
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public int updateStock(String isbn, int additional) throws DatabaseException {
		Book book = bookDao.getBookByIsbn(isbn);
		int newStock = book.addToStock(additional);
		bookDao.updateBook(book);
		return newStock;

	}

	@Override
	public void deleteBook(String isbn) throws DatabaseException {
		try {
			Book book = getBookByIsbn(isbn);
			//bookDao.deleteBook(isbn);
			// Es wird nicht gelöscht, sondern der Stock auf -1 gesetzt, so bleibt die Archivierungsfunktion der Bestellungen erhalten
			bookDao.setStockToNegative(isbn);

		} catch (EntityDoesNotExistException e) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Book"));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}

	}

	private Set<String> splitTermByWhitespaces(String term) {
		Set<String> terms = new HashSet<String>(Arrays.asList(term.split("\\s+")));
		System.out.println("Search Terms\n");
		for (String s : terms) {
			System.out.println(s);
		}
		return terms;
	}

	private String onlyLeaveLettersAndNumbers(String s) {
		// System.out.println(s);
		s = s.replaceAll("[^a-zA-Z0-9]", "");
		// System.out.println(s);
		return s;
	}

	private boolean containsOnlyNumbers(String s) {
		if (s.matches("[0-9]+")) {
			return true;
		}
		return false;
	}

	@Override
	public int getVisitCount(String isbn) throws DatabaseException {
		try {
			Book book = bookDao.getBookByIsbn(isbn);
			return book.getVisitCount();
		} catch (EntityDoesNotExistException e) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Book"));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}

	}

	@Override
	public int increaseVisitCount(String isbn, int additional) throws DatabaseException {
		try {
			Book book = bookDao.getBookByIsbn(isbn);
			book.setVisitCount(book.getVisitCount() + additional);
			return bookDao.getBookByIsbn(isbn).getVisitCount();
		} catch (EntityDoesNotExistException e) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Book"));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	





	

	/*
	 * @Override public void insertBook(Map<Searchfields, String> map, boolean
	 * newAuthor) { BookBuilder bb = new BookBuilderImpl(); double price =
	 * Double.parseDouble(map.get(Searchfields.price)); int pages =
	 * Integer.parseInt(map.get(Searchfields.pages)); // Testen, ob es diese
	 * Kategorie schon gibt List<Category> cats =
	 * categoryDao.getCategoriesByName(map.get(Searchfields.categoryName));
	 * Category category = null; for (Category cat : cats){ if
	 * (cat.getCategoryName().equals(map.get(Searchfields.categoryName))){
	 * category = cat; break; } }
	 * 
	 * // Wenn es die Kategorie noch nicht gab if (category == null){
	 * CategoryBuilder cb = new CategoryBuilderImpl(); category =
	 * cb.setCategoryName(map.get(Searchfields.categoryName)).createCategory();
	 * } // TODO Testen, ob ich hier save(category) category sagen muss, oder ob
	 * das über Cascade funktioniert
	 * 
	 * // Abfragen, ob sich die View sicher ist, ob es sich um einen neuen Autor
	 * handelt if ( !newAuthor){
	 * 
	 * 
	 * // Abfragen, ob es Autor mit diesem Vor- und Nachnamen schon gibt
	 * List<Author> authors =
	 * authorDao.getAuthorByExactNames(map.get(Searchfields.nameF),
	 * map.get(Searchfields.nameL)); // Wenn es mehr als einen Autor mit dem
	 * Namen gibt: // Hier mit als Parameter übergeben: Einen boolean,
	 * Standardmäßig auf false -> Person gibt es noch nicht. if(authors.size() >
	 * 0){ throw new AuthorMayExistException("This Author " +
	 * map.get(Searchfields.nameF) + " " + map.get(Searchfields.nameL) +
	 * " may already exist"); } } AuthorBuilder ab = new AuthorBuilderImpl();
	 * Author author =
	 * ab.setNameF(map.get(Searchfields.nameF)).setNameL(map.get(Searchfields.
	 * nameL)).createAuthor();
	 * 
	 * // TODO Testen, ob ich hier save(author) sagen muss
	 * 
	 * // TODO Wo wird Form von PubDate überprüft? Muss hier übeprüft werden ->
	 * Illegal ArgumentException, ganz am Anfang
	 * 
	 * Book newBook =
	 * bb.setAuthors(authors).set.setIsbn(map.get(Searchfields.isbn)).setTitle(
	 * map.get(Searchfields.title)).setDescription(map.get(Searchfields.
	 * description)).setPrice(price).setPublisher(map.get(Searchfields.publisher
	 * )).setPubdate(map.get(Searchfields.pubdate)).setEdition(map.get(
	 * Searchfields.edition)).setPages(pages).createBook();
	 * 
	 * dao.insertBook(newBook);
	 * 
	 * }
	 */

}
