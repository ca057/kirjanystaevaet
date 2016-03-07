package appl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import appl.data.enums.Searchfields;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import appl.data.items.User;
import appl.logic.service.BookService;
import appl.logic.service.OrderService;
import appl.logic.service.UserService;
import exceptions.data.AuthorMayExistException;
import exceptions.data.CategoryExistsException;
import exceptions.data.DatabaseException;
import exceptions.data.EntityDoesNotExistException;

public class QueryFun {

	public void doSomeTesting2(ApplicationContext ctx) throws DatabaseException {
		BookService service = ctx.getBean(BookService.class);
		// CategoryService ct = ctx.getBean(CategoryService.class);
		// System.out.println("Kategorien: " + ct.getAllCategoryNames());
		HashMap<Searchfields, String> map = new HashMap<Searchfields, String>();
		map.put(Searchfields.categoryName, "php");
		// map.put(Searchfields.title, "Architecture");
		// map.put(Searchfields.nameF, "Thomas");
		List result = service.getBooksByMetadata(map);
		System.out.println("Criteria:" + result);
		System.out.println("Alle Bücher: " + service.getAllBooks());

		System.out.println();
		System.exit(0);
	}

	public void testStatistics(ApplicationContext ctx) {
		UserService service = ctx.getBean(UserService.class);
		Calendar date = new Calendar.Builder().setDate(2016, 3, 1).build();
		try {
			service.updateUserBookStatistic(2, "0201433362", date);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Statistiken: " + service.getUserBookStatistics(2));
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	public void testExceptions(ApplicationContext ctx) throws DatabaseException {
		BookService service = ctx.getBean(BookService.class);

		service.getCategoryByExactName("Some Shit");
	}
	
	public void testGetAuthorsByIsbn(ApplicationContext ctx)throws DatabaseException{
		BookService service = ctx.getBean(BookService.class);
		List<Author> authors = service.getAuthorByIsbn("0131428985");
		System.out.println("Authors must be Thomas Somenthing");
		for(Author a : authors){
			System.out.println(a.toString());
		}
	}

	public void testCategoryInsert(ApplicationContext ctx) throws CategoryExistsException, DatabaseException {
		BookService service = ctx.getBean(BookService.class);
		service.insertCategory("stricken");
		service.insertCategory("häkeln");
		List<String> categoryNames = service.getAllCategoryNames();
		System.out.println("Nach insert");
		for (String s : categoryNames) {
			System.out.println(s);
		}
	}

	public void testCategoryDelete(ApplicationContext ctx) throws DatabaseException {
		BookService service = ctx.getBean(BookService.class);
		service.deleteCategory("stricken");
		List<String> categoryNames = service.getAllCategoryNames();
		System.out.println("Nach Delete\n\n");
		for (String s : categoryNames) {
			System.out.println(s);
		}
	}

	public void testAuthorInsert(ApplicationContext ctx) throws DatabaseException {
		BookService service = ctx.getBean(BookService.class);
		try {
			service.insertAuthor("Madeleine", "Rosenhagen", true);
		} catch (AuthorMayExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		List<Author> authorNames = service.getAllAuthors();
		System.out.println("Inserted Author\n\n");
		for (Author a : authorNames) {
			System.out.println(a.getNameF() + " " + a.getNameL());
		}
	}

	public void testAuthorDelete(ApplicationContext ctx) throws DatabaseException {
		BookService service = ctx.getBean(BookService.class);
		List<Author> author = service.getAuthorByExactName("Madeleine", "Rosenhagen");
		service.deleteAuthor(author.get(0).getAuthorId());
		List<Author> authorNames = service.getAllAuthors();
		System.out.println("Deleted Author\n\n");
		for (Author a : authorNames) {
			System.out.println(a.getNameF() + " " + a.getNameL());
		}
	}

	public void testInsertBook(ApplicationContext ctx) throws DatabaseException {
		BookService service = ctx.getBean(BookService.class);
		List<Book> bookList = service.getAllBooks();
		System.out.println(" \n\nBooklist before insert");
		for (Book b : bookList) {
			System.out.println(b.toString());
		}
		try {
			int authorId = service.insertAuthor("Joan K.", "Rowling", true);
			Map<Searchfields, String> bookMap = new HashMap<Searchfields, String>();
			bookMap.put(Searchfields.title, "Harry Potter");
			bookMap.put(Searchfields.description, "Fantasy Children's book");
			bookMap.put(Searchfields.price, "34.56");
			bookMap.put(Searchfields.isbn, "0101010101");
			bookMap.put(Searchfields.pages, "1234");
			bookMap.put(Searchfields.stock, "8");

			try {
				int categoryId = service.insertCategory("Children's Fantasy");
				int categoryId2 = service.insertCategory("Bestseller");
				Set<Integer> catSet = new HashSet<Integer>();
				catSet.add(categoryId);
				catSet.add(categoryId2);
				Set<Integer> authorSet = new HashSet<Integer>();
				authorSet.add(authorId);

				service.insertBook(bookMap, authorSet, catSet);

			} catch (EntityDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);

			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);

			}

			/*
			 * } catch (AuthorMayExistException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); System.exit(1);
			 * 
			 * }
			 */
			bookList = service.getAllBooks();
			System.out.println(" \n\nBooklist after insert");
			for (Book b : bookList) {
				System.out.println(b.toString());
			}
			List<Category> categoryList = service.getAllCategories();
			System.out.println("\n\nCategories after insert");
			for (Category cat : categoryList) {
				System.out.println(cat.getCategoryName());
			}

			/*
			 * System.out.println("\n\nDelete Category\n\n");
			 * 
			 * service.deleteCategory("Children's Fanatsy"); bookList =
			 * service.getAllBooks(); System.out.println(
			 * " \n\nBooklist after Category Delete"); for (Book b : bookList){
			 * System.out.println(b.toString()); }
			 */

			int authorId2 = service.insertAuthor("Michael", "Ende", true);
			Map<Searchfields, String> bookMap2 = new HashMap<Searchfields, String>();
			bookMap2.put(Searchfields.title, "Momo");
			bookMap2.put(Searchfields.description, "Fantasy Children's book");
			bookMap2.put(Searchfields.price, "34.56");
			bookMap2.put(Searchfields.isbn, "9101010101");
			bookMap2.put(Searchfields.pages, "1234");
			bookMap2.put(Searchfields.stock, "8");
			Category category = service.getCategoryByExactName("Children's Fantasy");
			try {

				Set<Integer> catSet = new HashSet<Integer>();
				catSet.add(category.getCategoryID());
				Set<Integer> authorSet2 = new HashSet<Integer>();
				authorSet2.add(authorId2);

				service.insertBook(bookMap2, authorSet2, catSet);

			} catch (EntityDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);

			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);

			}

		} catch (AuthorMayExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);

		}
		bookList = service.getAllBooks();
		System.out.println(" \n\nBooklist after insert2");
		for (Book b : bookList) {
			System.out.println(b.toString());
		}
		/*
		 * List<Category> categoryList = service.getAllCategories();
		 * System.out.println("\n\nCategories after insert2"); for (Category cat
		 * : categoryList) { System.out.println(cat.getCategoryName()); }
		 */

		/*
		 * 
		 * 
		 * >>>>>>> refs/remotes/origin/master Category cat =
		 * service.getCategoryByExactName("Children's Fantasy"); Set<Book>
		 * bookOfCat = cat.getBooks(); System.out.println("\nTest getBooks\n");
		 * for (Book b : bookOfCat) { System.out.println(b.toString()); }
		 * <<<<<<< HEAD
		 */
	}

	public void testDeleteBook(ApplicationContext ctx) throws DatabaseException {
		BookService service = ctx.getBean(BookService.class);
		service.deleteBook("0101010101");
		List<Book> bookList = service.getAllBooks();
		System.out.println(" \n\nBooklist after delete");
		for (Book b : bookList) {
			System.out.println(b.toString());
		}
		List<Category> categoryList = service.getAllCategories();
		System.out.println("\n\nCategories after delete");
		for (Category cat : categoryList) {
			System.out.println(cat.getCategoryName());
		}

	}

	public void testInsertOrder(ApplicationContext ctx) throws DatabaseException {
		BookService dataService = ctx.getBean(BookService.class);
		OrderService orderService = ctx.getBean(OrderService.class);
		UserService userService = ctx.getBean(UserService.class);

		List<User> users = userService.getUsers();
		System.out.println("List of Users");
		for (User u : users) {
			System.out.println(u.toString());
		}

		User user = userService.findByID(2).get();
		List<Book> books = dataService.getAllBooks();
		
		 for (Book b : books){ System.out.println(b.toString()); }
		 
		 Book book = dataService.getBookByIsbn("9101010101");
		 //Set<String> isbns = new HashSet<String>();
		 //isbns.add("9101010101");
		Map<String, Integer> isbns = new HashMap<String, Integer>();
		isbns.put("9101010101", 2);
		Calendar cal = Calendar.getInstance();
		int orderId = orderService.createOrder(isbns, user.getUserId(), cal);
//		List<Book> books4 = dataService.getAllBooks();
//		
		// for (Book b : books4){ System.out.println(b.toString()); }
		//System.out.println("OrderId " + orderId + " ");
		//Set<Orderx> ordersOfThisUser = userService.findByID(2).get().getOrders();
		// Set<Orderx> ordersOfThisUser = user.getOrders();
		//System.out.println("\nOrder of this User amunt \n" + ordersOfThisUser.size());
//		for (Orderx o : ordersOfThisUser) {
//			Set<OrderItem> items = o.getOrderItems();
//			//System.out.println("Größe der Bestellung" + o.getOrderItems().size());
//			for (OrderItem a : items) {
//				System.out.println("Title " + a.getBook().getTitle() + "Stock " + a.getBook().getStock());
//			}
//		}

//		// Zweite Order
		Map<String, Integer> isbns2 = new HashMap<String, Integer>();
		isbns2.put("9101010101", 1);
		isbns2.put("0101010101", 5);
		isbns2.put("1590595726", 1);

		Calendar cal2 = Calendar.getInstance();
		int orderId2 = orderService.createOrder(isbns2, 1, cal2);
		//System.out.println("OrderId " + orderId2 + " ");
		List<Book> orderedBooks = orderService.getOrderedBooksOfUser(1);
		System.out.println("\nOrdered Books of User 1\n");
		for (Book b : orderedBooks){
			System.out.println(b.toString());
		}
		//Set<Orderx> userOrders = userService.findByID(1).get().getOrders();
//		System.out.println("\n\nGet Order of user\nSize " + userOrders.size() + "\n");
//		for (Orderx o : userOrders) {
//			System.out.println(o.toString());
//		}

//		List<Orderx> allOrders = orderService.getAllOrders();
//		System.out.println("\n\nGet all orders\n\n");
//		System.out.println("Size of Orderlist: " + allOrders.size());
//		for (Orderx o : allOrders) {
//			System.out.println(o.toString());
//		}

//		List<OrderItem> allOrderItems = orderService.getAllOrderItems();
//		System.out.println("\n all order items size: \n" + allOrderItems.size());
//		for (OrderItem o : allOrderItems) {
//			System.out.println(o.toString());
//		}

	}
	public void testBestsellers(ApplicationContext ctx) throws DatabaseException{
		BookService dataService = ctx.getBean(BookService.class);
		OrderService orderService = ctx.getBean(OrderService.class);
		UserService userService = ctx.getBean(UserService.class);
		
		List<Map.Entry<String, Integer>> bestseller = orderService.getBestsellers();
		System.out.println("\nBestsellers\n");
		for (Map.Entry<String, Integer> m : bestseller){
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}
	
	public void testDeleteCategoryOfBook(ApplicationContext ctx) throws DatabaseException{
		BookService dataService = ctx.getBean(BookService.class);
		Book book = dataService.getBookByIsbn("0101010101");
		Set<Category> cats = book.getCategories();
		System.out.println("Categories of Book\n");
		for (Category c : cats){
			System.out.println(c.toString());
		}
		List<Book> books = dataService.getBooksByCategory("Bestseller");
		System.out.println("Books with that Category");	
		for(Book b: books){
			System.out.println(b.getTitle());
		}
		
//		
		
		Category cat = dataService.getCategoryByExactName("Bestseller");
		dataService.deleteCategoryOfBook("0101010101", cat.getCategoryID());
		
//		book = dataService.getBookByIsbn("0101010101");
//		cats = book.getCategories();
//		System.out.println("Categories of Book\n");
//		for (Category c : cats){
//			System.out.println(c.toString());
//		}
		
//		List<Book> books = dataService.getBooksByCategory("Bestseller");
//		System.out.println("Books with that Category");	
//		for(Book b: books){
//			System.out.println(b.getTitle());
//		}
		
	}
	public void testAddCategoryToBook(ApplicationContext ctx) throws DatabaseException{
		BookService dataService = ctx.getBean(BookService.class);
		OrderService orderService = ctx.getBean(OrderService.class);
		UserService userService = ctx.getBean(UserService.class);
		
		Category category = dataService.getCategoryByExactName("PHP");
		dataService.addCategoryToBook("0101010101", category.getCategoryID());
		Book book = dataService.getBookByIsbn("0101010101");
		Set<Category> cats = book.getCategories();
		System.out.println("Categories of Book\n");
		for (Category c : cats){
			System.out.println(c.toString());
		}
	}
	public void testUpdateBook(ApplicationContext ctx) throws DatabaseException{
		BookService dataService = ctx.getBean(BookService.class);
		OrderService orderService = ctx.getBean(OrderService.class);
		UserService userService = ctx.getBean(UserService.class);
		
		Map<Searchfields, String> map = new HashMap<Searchfields, String>();
		map.put(Searchfields.description, "New Description");
		map.put(Searchfields.price, "100,11");
		dataService.updateBook("0101010101", map);
		Book book = dataService.getBookByIsbn("0101010101");
		System.out.println(book.toString());
		
	}
	/*
	 * 
	 * public void doSomeOrderTesting(ApplicationContext ctx) { SessionFactory
	 * sessionFactory = ctx.getBean(SessionFactory.class);
	 * sessionFactory.getCurrentSession().beginTransaction(); Order order =
	 * createOrderTestData(); OrderDAO oDao = ctx.getBean(OrderDAO.class);
	 * oDao.insertOrder(order);
	 * 
	 * //ToDo Orderabfragen
	 * 
	 * }
	 */
	/*
	 * public Order createOrderTestData() { Set<Book> book = createTestData();
	 * User user = createUserTestData(); Order order = new Order(book, user,
	 * 1990, 8, 6, 0, 4, 12); return order;
	 * 
	 * 
	 * } /* public void doSomeOrderTesting(ApplicationContext ctx) {
	 * SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
	 * sessionFactory.getCurrentSession().beginTransaction(); Order order =
	 * createOrderTestData(); OrderDAO oDao = ctx.getBean(OrderDAO.class);
	 * oDao.insertOrder(order);
	 * 
	 * //ToDo Orderabfragen
	 * 
	 * }
	 */
	/*
	 * public Order createOrderTestData() { Set<Book> book = createTestData();
	 * User user = createUserTestData(); Order order = new Order(book, user,
	 * 1990, 8, 6, 0, 4, 12); return order;
	 * 
	 * }
	 */

	/*
	 * public void doSomeTesting(SessionFactory sessionFactory) { //
	 * Transaction: "Allows the application to define units of work, while //
	 * maintaining abstraction from the underlying transaction //
	 * implementation" sessionFactory.getCurrentSession().beginTransaction();
	 * 
	 * System.out.println(sessionFactory.getCurrentSession() .createQuery(
	 * "from Book where category.name like php and author.nameF like Jason"
	 * ).uniqueResult());
	 * 
	 * // Bücher speichern. save() oder persist()? // for (Book b :
	 * createTestData()) { // session.save(b); // }
	 * 
	 * // Testabfrage Query query =
	 * sessionFactory.getCurrentSession().createQuery("from Book"); List<Book>
	 * list = query.list(); for (Book o : list) {
	 * System.out.println("\n---------------------");
	 * System.out.println(o.getTitle()); System.out.println(o.getIsbn());
	 * System.out.println(o.getCategories().iterator().next().toString());
	 * System.out.println(o.getAuthors().iterator().next().toString());
	 * System.out.println("---------------------\n"); }
	 * 
	 * // Das geht nicht: System.out.println(session.createQuery("from //
	 * author").list());
	 * 
	 * // Test von fetch type Book usualBook = (Book)
	 * sessionFactory.getCurrentSession().createQuery(
	 * "from Book where isbn='0131428985'") .uniqueResult();
	 * System.out.println("---------------------\n" + usualBook.getCategories()
	 * + "\n" + usualBook.getAuthors().iterator().next().getBooks() +
	 * "\n---------------------");
	 * 
	 * // Select -> nicht das ganze Objekt bekommen, sondern nur nen String //
	 * oder so System.out.println(
	 * "\n --------- SELECT ----------------------------- \n"); // Query
	 * querySelect = session.createQuery("select nameF from Book where //
	 * nameL=Gilmore"); Query querySelect = sessionFactory.getCurrentSession()
	 * .createQuery("select description from Book where isbn='0131428985'");
	 * List<String> name = querySelect.list(); System.out.println(
	 * "Anzahl der ELement: " + name.size()); // System.out.println(
	 * "The desc is: " + name.getDescription()); // System.out.println(
	 * "The isbn is: " + name.getIsbn()); for (String n : name) { //
	 * System.out.println("The isbn is: " + n.getIsbn()); // System.out.println(
	 * "The name is: " + n.getDescription()); System.out.println("Desc: " + n);
	 * }
	 * 
	 * // Kategorien suchen -> für getCategories System.out.println(
	 * "\n ---------------------------- KATEGORIEN -------------------------");
	 * Query query2 = sessionFactory.getCurrentSession().createQuery(
	 * "select distinct categoryName from Category"); List<String> cats =
	 * query2.list(); System.out.println("Anzahl der ELement: " + cats.size());
	 * // System.out.println("The desc is: " + name.getDescription()); //
	 * System.out.println("The isbn is: " + name.getIsbn()); for (String n :
	 * cats) { // System.out.println("The isbn is: " + n.getIsbn()); //
	 * System.out.println("The name is: " + n.getDescription());
	 * System.out.println("Desc: " + n); }
	 * 
	 * // Versuche eines Joins. Für Autoren funktioniert es, aber nicht für //
	 * categorien, was mega seltsam ist, da es exakt diesselbe Struktur ist //
	 * Query query3 = session.createQuery("select a.CategoryName from Book b //
	 * join b.categories a where b.isbn ='0131428985'"); System.out.println(
	 * "\n ------------------ getCategoryOfBook-----\n"); Query query3 =
	 * sessionFactory.getCurrentSession() .createQuery(
	 * "select a.categoryName from Book b join b.categories a where b.isbn ='0131428985'"
	 * ); // query.setString("name", "MySQL"); List<String> books =
	 * query3.list(); for (String n : books) { System.out.println("Cat: " + n);
	 * }
	 * 
	 * // Select Book by Category
	 * 
	 * System.out.println("\n ------------------ getBookByCategory-----\n");
	 * Query query4 = sessionFactory.getCurrentSession() .createQuery(
	 * "select b from Book b join b.categories a where a.categoryName='PHP'");
	 * // query.setString("name", "MySQL"); List<Book> books1 = query4.list();
	 * for (Book n : books1) { System.out.println("Titel: " + n.getTitle() +
	 * " Cat: " + n.getCategories()); // System.out.println("Titel: " + n); }
	 * 
	 * sessionFactory.getCurrentSession().getTransaction().commit();
	 * System.out.println("Done"); System.exit(0); }
	 */

	public void jdbcStuff(JdbcTemplate jdbc) {
		// int counter = 1;
		// List<Author> authors = jdbc.query("SELECT * FROM PUBLIC.bookauthors
		// WHERE nameF=? AND nameL=?",
		// new String[] { "Jason", "Gilmore" }, new AuthorRowMapper());
		// for (Author a : authors) {
		// System.out.println("Autor " + counter++ + ": " + a.getNameF() + ", "
		// + a.getNameL());
		// }
		//
		// List<Book> list = jdbc.query(
		// "SELECT * FROM PUBLIC.bookcategories, PUBLIC.bookcategoriesbooks,
		// PUBLIC.bookdescriptions, PUBLIC.bookauthorbooks, PUBLIC.bookauthors",
		// new BookRowMapper());
		// for (Book o : list) {
		// System.out.println(o.getTitle());
		// }
		// System.out.println(jdbc.queryForObject("select count(*) FROM
		// PUBLIC.bookauthors", Integer.class));
	}
	/*
	 * public void testDao(ApplicationContext ctx){ SessionFactory
	 * sessionFactory = ctx.getBean(SessionFactory.class); BookService
	 * bookService = ctx.getBean(BookService.class); CategoryService
	 * categoryService = ctx.getBean(CategoryService.class);
	 * 
	 * List<String> allCategories = categoryService.getAllCategoryNames();
	 * System.out.println("GetAllCategories-----------------------\n\n\n"); for
	 * (String s : allCategories){ System.out.println(s); }
	 * 
	 * 
	 * Book book = bookService.getBookByIsbn("0201-433 3-62");
	 * System.out.println("GetBookByIsbn-------------------------------\n\n\n");
	 * System.out.println(book.toString());
	 * 
	 * List<Book> booksByCat = bookService.getBooksByCategory("Linguistik");
	 * 
	 * System.out.println(
	 * "Get books by Category------------------------------\n\n\n"); for (Book b
	 * : booksByCat){ System.out.println(b.toString()); } List<Book> allBooks =
	 * bookService.getAllBooks(); System.out.println(
	 * "Get all books --------------------\n\n\n"); for (Book b : allBooks){
	 * System.out.println(b.toString()); } Map<Searchfields, String> map = new
	 * HashMap<Searchfields, String>();
	 * 
	 * map.put(Searchfields.categoryName, "Linguistik");
	 * map.put(Searchfields.title, "Learning PHP"); List<Book> results =
	 * bookService.getBooksByMetadata(map); System.out.println(
	 * "GetBooksByMetadata _-------------------------------------\n\n\n");
	 * System.out.println("Size: " + results.size()); for(Book b : results){
	 * System.out.println(b.toString()); }
	 * 
	 * 
	 * System.out.println("Test OpenSearch------------------\n\n\n");
	 * bookService.getBooksByOpenSearch("I love my cat"); }
	 */
	/*
	 * public void testBookInsert (ApplicationContext ctx) throws
	 * PrimaryKeyViolationException{ SessionFactory sessionFactory =
	 * ctx.getBean(SessionFactory.class); BookService bookService =
	 * ctx.getBean(BookService.class); /*List<String> allCats =
	 * bookService.getAllCategoryNames(); for (String s : allCats){
	 * System.out.println(allCats); } System.exit(1);
	 */
	/*
	 * List<Author> allauthors = bookService.getAllAuthors(); for (Author a :
	 * allauthors){
	 * 
	 * System.out.println(a.toString()); }
	 * 
	 * Author singleAuthor = bookService.getAuthorById(42);
	 * System.out.println(singleAuthor.toString()); System.exit(1);
	 * 
	 * // Testen, wenn ich weiß, dass es ein neuer Autor ist try {
	 * System.out.println("Get all Books\n\n"); List<Book> allBook =
	 * bookService.getAllBooks(); for (Book b: allBook){
	 * System.out.println(b.toString()); }
	 * 
	 * 
	 * int authorId = bookService.insertAuthor("Jason", "Gilmore", true);
	 * System.out.println("QueryFun nach bookService.insertAuthor");
	 * Set<Integer> authors = new HashSet<Integer>(); authors.add(authorId);
	 * 
	 * Set<Integer> categories = new HashSet<Integer>();
	 * 
	 * Category category = bookService.getCategoryByExactName("PHP");
	 * System.out.println("Got Category: " + category.toString() + "\n\n");
	 * categories.add(category.getCategoryID());
	 * 
	 * Map<Searchfields, String> dataMap = new HashMap<Searchfields, String>();
	 * dataMap.put(Searchfields.isbn, "08006700");
	 * dataMap.put(Searchfields.description, "This is a TestTextBook");
	 * dataMap.put(Searchfields.edition, "1"); dataMap.put(Searchfields.pages,
	 * "555"); dataMap.put(Searchfields.price, "9.99");
	 * dataMap.put(Searchfields.pubdate, "January 2016");
	 * dataMap.put(Searchfields.publisher, "Springer");
	 * dataMap.put(Searchfields.title, "PHP fuer Dummies");
	 * 
	 * System.out.println(
	 * "QueryFun, bevor service.insertBook\n Gib die Author Ids aus dem Set aus"
	 * ); for (int s : authors){ System.out.println("Author id : " + s + "\n");
	 * } bookService.insertBook(dataMap, authors, categories);
	 * 
	 * System.out.println("Get all Books\n\n"); List<Book> allBook2 =
	 * bookService.getAllBooks(); for (Book b: allBook2){
	 * System.out.println(b.toString()); }
	 * 
	 * 
	 * 
	 * 
	 * } catch (AuthorMayExistException e) { // HIer nochmal Exception in try
	 * catch Block -> ignore // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	public void testUser(ApplicationContext ctx) {
		UserService service = ctx.getBean(UserService.class);
		File file = new File("WebContent/resources/img/cover/0131428985.jpg");
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			service.updateAccount(service.findbyMail("admin@ky.de").get().getUserId(), new HashMap<>(), bFile);
			fileInputStream.close();
			FileOutputStream fos = new FileOutputStream("output.jpg");
			User user = (User) service.findbyMail("admin@ky.de")
					.orElseThrow(() -> new EntityDoesNotExistException("User nicht gefunden"));
			System.out.println(user);
			fos.write(user.getImage());
			fos.close();

		} catch (DatabaseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * public Set<Book> createTestData() { Set<Book> books = new HashSet<>();
	 * Set<Author> authors = new HashSet<>();
	 * 
	 * Author author = new Author(); author.setNameF("Hans");
	 * author.setNameL("Peter"); authors.add(author);
	 * 
	 * Book book = new Book("1213452", "title", "Desc", 11.00,
	 * "String publisher", "String pubdate", "String edition", 10, null, null);
	 * 
	 * books.add(new Book("1213452", "title", "Desc", 12.00, "String publisher",
	 * "String pubdate", "String edition", 10, null, authors)); books.add(new
	 * Book("12134394", "title", "Desc", 10.00, "String publisher",
	 * "String pubdate", "String edition", 10, null, authors));
	 * 
	 * // books.add(book); Category category1 = new Category("Category1",
	 * books); Category category2 = new Category("Category2", null);
	 * 
	 * Set<Category> categories = new HashSet<Category>();
	 * categories.add(category1); categories.add(category2);
	 * 
	 * book.setCategories(categories); return books; }
	 */
	/*
	 * public User createUserTestData() { PLZ plz = new PLZ();
	 * plz.setPlace("Buxtehude"); plz.setPostcode("000000");
	 * 
	 * User user1 = new User("krabbe", "Rosenhagen", "Madeleine", "xyz@ihp.de",
	 * "streetstrett", "23a", plz); return user1;
	 * 
	 * }
	 */

}
