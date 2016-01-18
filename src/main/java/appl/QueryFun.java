package appl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import appl.data.builder.UserBuilder;
import appl.data.builder.impl.UserBuilderImpl;
import appl.data.dao.OrderDAO;
import appl.data.enums.Searchfields;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import appl.data.items.Order;
import appl.data.items.PLZ;
import appl.data.items.User;
import appl.logic.service.BookService;
import appl.logic.service.CategoryService;

public class QueryFun {

	public void doSomeTesting2(ApplicationContext ctx) {
		BookService service = ctx.getBean(BookService.class);
		CategoryService ct = ctx.getBean(CategoryService.class);
		System.out.println("Kategorien: " + ct.getAllCategoryNames());
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

	public void doSomeOrderTesting(ApplicationContext ctx) {
		SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
		sessionFactory.getCurrentSession().beginTransaction();
		Order order = createOrderTestData();
		OrderDAO oDao = ctx.getBean(OrderDAO.class);
		oDao.insertOrder(order);

		// ToDo Orderabfragen

	}

	public Order createOrderTestData() {
		Set<Book> book = createTestData();
		User user = createUserTestData();
		Order order = new Order(book, user, 1990, 8, 6, 0, 4, 12);
		return order;

	}

	public void doSomeTesting(SessionFactory sessionFactory) {
		// Transaction: "Allows the application to define units of work, while
		// maintaining abstraction from the underlying transaction
		// implementation"
		sessionFactory.getCurrentSession().beginTransaction();

		System.out.println(sessionFactory.getCurrentSession()
				.createQuery("from Book where category.name like php and author.nameF like Jason").uniqueResult());

		// Bücher speichern. save() oder persist()?
		// for (Book b : createTestData()) {
		// session.save(b);
		// }

		// Testabfrage
		Query query = sessionFactory.getCurrentSession().createQuery("from Book");
		List<Book> list = query.list();
		for (Book o : list) {
			System.out.println("\n---------------------");
			System.out.println(o.getTitle());
			System.out.println(o.getIsbn());
			System.out.println(o.getCategories().iterator().next().toString());
			System.out.println(o.getAuthors().iterator().next().toString());
			System.out.println("---------------------\n");
		}

		// Das geht nicht: System.out.println(session.createQuery("from
		// author").list());

		// Test von fetch type
		Book usualBook = (Book) sessionFactory.getCurrentSession().createQuery("from Book where isbn='0131428985'")
				.uniqueResult();
		System.out.println("---------------------\n" + usualBook.getCategories() + "\n"
				+ usualBook.getAuthors().iterator().next().getBooks() + "\n---------------------");

		// Select -> nicht das ganze Objekt bekommen, sondern nur nen String
		// oder so
		System.out.println("\n --------- SELECT ----------------------------- \n");
		// Query querySelect = session.createQuery("select nameF from Book where
		// nameL=Gilmore");
		Query querySelect = sessionFactory.getCurrentSession()
				.createQuery("select description from Book where isbn='0131428985'");
		List<String> name = querySelect.list();
		System.out.println("Anzahl der ELement: " + name.size());
		// System.out.println("The desc is: " + name.getDescription());
		// System.out.println("The isbn is: " + name.getIsbn());
		for (String n : name) {
			// System.out.println("The isbn is: " + n.getIsbn());
			// System.out.println("The name is: " + n.getDescription());
			System.out.println("Desc: " + n);
		}

		// Kategorien suchen -> für getCategories
		System.out.println("\n ---------------------------- KATEGORIEN -------------------------");
		Query query2 = sessionFactory.getCurrentSession().createQuery("select distinct categoryName from Category");
		List<String> cats = query2.list();
		System.out.println("Anzahl der ELement: " + cats.size());
		// System.out.println("The desc is: " + name.getDescription());
		// System.out.println("The isbn is: " + name.getIsbn());
		for (String n : cats) {
			// System.out.println("The isbn is: " + n.getIsbn());
			// System.out.println("The name is: " + n.getDescription());
			System.out.println("Desc: " + n);
		}

		// Versuche eines Joins. Für Autoren funktioniert es, aber nicht für
		// categorien, was mega seltsam ist, da es exakt diesselbe Struktur ist
		// Query query3 = session.createQuery("select a.CategoryName from Book b
		// join b.categories a where b.isbn ='0131428985'");
		System.out.println("\n ------------------ getCategoryOfBook-----\n");
		Query query3 = sessionFactory.getCurrentSession()
				.createQuery("select a.categoryName from Book b join b.categories a where b.isbn ='0131428985'");
		// query.setString("name", "MySQL");
		List<String> books = query3.list();
		for (String n : books) {
			System.out.println("Cat: " + n);
		}

		// Select Book by Category

		System.out.println("\n ------------------ getBookByCategory-----\n");
		Query query4 = sessionFactory.getCurrentSession()
				.createQuery("select b from Book b join b.categories a where a.categoryName='PHP'");
		// query.setString("name", "MySQL");
		List<Book> books1 = query4.list();
		for (Book n : books1) {
			System.out.println("Titel: " + n.getTitle() + " Cat: " + n.getCategories());
			// System.out.println("Titel: " + n);
		}

		sessionFactory.getCurrentSession().getTransaction().commit();
		System.out.println("Done");
		System.exit(0);
	}

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

	public void testDao(ApplicationContext ctx) {
		SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
		BookService bookService = ctx.getBean(BookService.class);
		CategoryService categoryService = ctx.getBean(CategoryService.class);

		List<String> allCategories = categoryService.getAllCategoryNames();
		System.out.println("GetAllCategories-----------------------\n\n\n");
		for (String s : allCategories) {
			System.out.println(s);
		}

		Book book = bookService.getBookByIsbn("0201-433 3-62");
		System.out.println("GetBookByIsbn-------------------------------\n\n\n");
		System.out.println(book.toString());

		List<Book> booksByCat = bookService.getBooksByCategory("PHP");

		System.out.println("Get books by Category------------------------------\n\n\n");
		for (Book b : booksByCat) {
			System.out.println(b.toString());
		}
		List<Book> allBooks = bookService.getAllBooks();
		System.out.println("Get all books --------------------\n\n\n");
		for (Book b : allBooks) {
			System.out.println(b.toString());
		}
		Map<Searchfields, String> map = new HashMap<Searchfields, String>();

		map.put(Searchfields.categoryName, "PHP");
		map.put(Searchfields.title, "Learning PHP");
		List<Book> results = bookService.getBooksByMetadata(map);
		System.out.println("GetBooksByMetadata _-------------------------------------\n\n\n");
		System.out.println("Size: " + results.size());
		for (Book b : results) {
			System.out.println(b.toString());
		}

		System.out.println("Test OpenSearch------------------\n\n\n");
		bookService.getBooksByOpenSearch("I love my cat");
	}

	public Set<Book> createTestData() {
		Set<Book> books = new HashSet<>();
		Set<Author> authors = new HashSet<>();

		Author author = new Author();
		author.setNameF("Hans");
		author.setNameL("Peter");
		authors.add(author);

		Book book = new Book("1213452", "title", "Desc", 11.00, "String publisher", "String pubdate", "String edition",
				10, null, null);

		books.add(new Book("1213452", "title", "Desc", 12.00, "String publisher", "String pubdate", "String edition",
				10, null, authors));
		books.add(new Book("12134394", "title", "Desc", 10.00, "String publisher", "String pubdate", "String edition",
				10, null, authors));

		// books.add(book);
		Category category1 = new Category("Category1", books);
		Category category2 = new Category("Category2", null);

		Set<Category> categories = new HashSet<Category>();
		categories.add(category1);
		categories.add(category2);

		book.setCategories(categories);
		return books;
	}

	public User createUserTestData() {
		PLZ plz = new PLZ();
		plz.setPlace("Buxtehude");
		plz.setPostcode("000000");

		UserBuilder b = new UserBuilderImpl();
		b.setEmail("admin@kirjanystaevaet.de");
		b.setName("admin");
		b.setPassword("admin");
		return null;
	}

}
