package appl.logic.service.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

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
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import appl.enums.SearchMode;
import appl.enums.Searchfields;
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
			}
			return names;
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public List<Category> getAllCategories() throws DatabaseException {
		try {
			return categoryDao.getCategories();
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public String getCategoryName(String name) throws DatabaseException {
		Category cat = getCategoryByExactName(name);

		return cat.getCategoryName();
	}

	@Override
	public boolean isExistingCategory(String category) throws DatabaseException {
		if (categoryDao.getCategoriesByExactName(category) != null) {
			return true;
		}
		return false;
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

	@Override
	public void updateCategory(int categoryId, String newCategoryName) throws DatabaseException {
		try {
			Category cat = categoryDao.getCategoryById(categoryId);
			cat.setCategoryName(newCategoryName);
			categoryDao.updateCategory(cat);

		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}

	}

	@Override
	public void deleteCategory(int id) throws DatabaseException {
		// Kategorie holen
		try {
			// To check if category exists
			Category cat = categoryDao.getCategoryById(id);
			categoryDao.deleteCategory(id);
		} catch (EntityDoesNotExistException e) {

			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Category"));
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(ErrorMessageHelper.DataIntegrityViolation("Category", "Book", e.getMessage()));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}

	}

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
		try {
			isbn = onlyLeaveLettersAndNumbers(isbn);
			List<Author> authors = authorDao.getAuthorsByIsbn(isbn);
			return authors;

		} catch (HibernateException e) {
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
	public void updateAuthor(int id, Map<Searchfields, String> newData) throws DatabaseException {
		try {
			Author author = authorDao.getAuthorByID(id);
			for (Searchfields s : newData.keySet()) {
				if (s == Searchfields.nameF) {
					author.setNameF(newData.get(s));
				} else if (s == Searchfields.nameL) {
					author.setNameL(newData.get(s));
				} else {
					throw new DatabaseException(ErrorMessageHelper.mayNotBeUpdated(s.toString()));
				}
			}
			authorDao.updateAuthor(author);
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}

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

	@Override
	public List<Book> getAllBooks(SearchMode mode) throws DatabaseException {
		try {
			return bookDao.getAllBooks(mode);

		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public List<Book> getAllBooks(SearchMode mode, int range) throws DatabaseException {
		List<Book> books = getAllBooks(mode);
		if (books.size() < range) {
			return books;
		}
		List<Book> smallList = books.subList(0, range);
		return smallList;
	}

	@Override
	public List<Book> getBooksByCategory(String category, SearchMode mode) throws DatabaseException {
		try {
			Map<Searchfields, String> map = new HashMap<Searchfields, String>();
			map.put(Searchfields.categoryName, category);
			return bookDao.getBooksByMetadata(map, mode);
			// return dao.getBooksByCategory(category);
			// return null;
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public List<Book> getBooksByCategory(String category, SearchMode mode, int range) throws DatabaseException {
		List<Book> books = getBooksByCategory(category, mode);
		if (books.size() < range) {
			return books;
		}
		List<Book> smallList = books.subList(0, range);
		return smallList;
	}

	@Override
	public Book getBookByIsbn(String isbn, SearchMode mode) throws DatabaseException {
		isbn = onlyLeaveLettersAndNumbers(isbn);
		Book book = bookDao.getBookByIsbn(isbn);
		switch (mode) {
		case ALL:
			break;
		case SELL:
			if (book.getStock() < 0) {
				throw new DatabaseException(ErrorMessageHelper.bookNotSold(isbn));
			}
			break;

		case AVAILABLE:
			if (book.getStock() == 0) {
				throw new DatabaseException(ErrorMessageHelper.bookNotAvailable(isbn));
			} else if (book.getStock() < 0) {
				throw new DatabaseException(ErrorMessageHelper.bookNotSold(isbn));
			}
			break;

		}
		return book;

	}

	@Override
	public List<Book> getBooksByOpenSearch(String searchTerm) {
		// Erstmal String splitten
		Set<String> searchTerms = splitTermByWhitespaces(searchTerm);

		return null;
	}

	@Override
	public List<Book> getBooksByMetdata(Map<Searchfields, String> map, SearchMode mode) throws DatabaseException {
		try {
			return bookDao.getBooksByMetadata(map, mode);

		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
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
		String isbn = onlyLeaveLettersAndNumbers(map.get(Searchfields.isbn));
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
		Book newBook = bb.setAuthors(authors).setIsbn(isbn).setTitle(map.get(Searchfields.title))
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
	public void updateBook(String isbn, Map<Searchfields, String> data, Set<Integer> authorIds,
			Set<Integer> categoryIds) throws DatabaseException {
		isbn = onlyLeaveLettersAndNumbers(isbn);

		Book book = bookDao.getBookByIsbn(isbn);
		Set<Author> authors = new HashSet<Author>();
		for (int id : authorIds){
			authors.add(authorDao.getAuthorByID(id));
		}
		book.setAuthors(authors);
		
		Set<Category> categories = new HashSet<Category>();
		for (int id : categoryIds){
			categories.add(categoryDao.getCategoryById(id));
		}
		book.setCategories(categories);
		
		for (Searchfields s : data.keySet()) {
			if (s == Searchfields.isbn) {
				throw new DatabaseException(ErrorMessageHelper.mayNotBeUpdated("isbn"));
			} else if (s == Searchfields.description) {
				book.setDescription(data.get(s));
			} else if (s == Searchfields.price) {
				double price = Double.parseDouble(data.get(s).replace(",", "."));
				book.setPrice(price);
			} else if (s == Searchfields.pages) {
				book.setPages(data.get(s));

			} else if (s == Searchfields.pubdate) {
				book.setPubdate(data.get(s));
			} else if (s == Searchfields.edition) {
				book.setEdition(data.get(s));
			} else if (s == Searchfields.publisher) {
				book.setPublisher(data.get(s));
			} else if (s == Searchfields.stock) {
				// TODO Christian fragen
			} else if (s == Searchfields.title) {
				book.setTitle(data.get(s));
			}
		}

		try {
			bookDao.updateBook(book);
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public void deleteCategoryOfBook(String isbn, int categoryId) throws DatabaseException {
		isbn = onlyLeaveLettersAndNumbers(isbn);

		Book book = bookDao.getBookByIsbn(isbn);
		Set<Category> categories = book.getCategories();
		boolean flag = false;
		Category toBeDeleted = null;
		for (Category c : categories) {
			if (c.getCategoryID() == categoryId) {
				flag = true;
				toBeDeleted = c;
				break;
			}

		}
		if (flag) {
			categories.remove(toBeDeleted);
		} else {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Category"));
		}
		try {
			bookDao.updateBook(book);
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public void addCategoryToBook(String isbn, int categoryId) throws DatabaseException {
		isbn = onlyLeaveLettersAndNumbers(isbn);

		try {
			Book book = bookDao.getBookByIsbn(isbn);
			Category toBeAdded = categoryDao.getCategoryById(categoryId);
			book.getCategories().add(toBeAdded);
			bookDao.updateBook(book);
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public int updateStock(String isbn, int additional) throws DatabaseException {
		isbn = onlyLeaveLettersAndNumbers(isbn);

		Book book = bookDao.getBookByIsbn(isbn);
		int newStock = book.addToStock(additional);
		bookDao.updateBook(book);
		return newStock;

	}

	@Override
	public void deleteBook(String isbn) throws DatabaseException {
		isbn = onlyLeaveLettersAndNumbers(isbn);

		try {
			Book book = getBookByIsbn(isbn, SearchMode.ALL);
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
		s = s.replaceAll("[^a-zA-Z0-9]", "");
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
		isbn = onlyLeaveLettersAndNumbers(isbn);

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
		isbn = onlyLeaveLettersAndNumbers(isbn);

		try {
			Book book = bookDao.getBookByIsbn(isbn);
			book.setVisitCount(book.getVisitCount() + additional);
			bookDao.updateBook(book);
			return bookDao.getBookByIsbn(isbn).getVisitCount();
		} catch (EntityDoesNotExistException e) {
			throw new DatabaseException(ErrorMessageHelper.entityDoesNotExist("Book"));
		} catch (HibernateException e) {
			throw new DatabaseException(ErrorMessageHelper.generalDatabaseError(e.getMessage()));
		}
	}

	@Override
	public SortedMap<Book, Integer> getMostVisitedBooks(int range) {
		if (range < 0) {
			throw new IllegalArgumentException("The passed range must be greater 0.");
		}
		SortedMap<Book, Integer> map = new TreeMap<>(new Comparator<Book>() {

			@Override
			public int compare(Book b1, Book b2) {
				return Integer.compare(b2.getVisitCount(), b1.getVisitCount());
			}
		});
		for (Book b : bookDao.getMostVisitedBooks(range)) {
			map.put(b, b.getVisitCount());
		}
		return map;
	}

	@Override
	public SortedMap<Book, Integer> getLeastVisitedBooks(int range) {
		if (range < 0) {
			throw new IllegalArgumentException("The passed range must be greater 0.");
		}
		SortedMap<Book, Integer> map = new TreeMap<>(new Comparator<Book>() {

			@Override
			public int compare(Book b1, Book b2) {
				return Integer.compare(b1.getVisitCount(), b2.getVisitCount());
			}
		});
		for (Book b : bookDao.getLeastVisitedBooks(range)) {
			map.put(b, b.getVisitCount());
		}
		return map;
	}
}
