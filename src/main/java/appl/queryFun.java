package appl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import appl.data.dao.rowMapper.AuthorRowMapper;
import appl.data.dao.rowMapper.BookRowMapper;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;

public class queryFun {

	public void doSomeTesting(Session session) {
		// Transaction: "Allows the application to define units of work, while
		// maintaining abstraction from the underlying transaction
		// implementation"
		session.beginTransaction();

		// Bücher speichern. save() oder persist()?
		// for (Book b : createTestData()) {
		// session.save(b);
		// }

		// Testabfrage
		Query query = session.createQuery("from Book");
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
		Book usualBook = (Book) session.createQuery("from Book where isbn='0131428985'").uniqueResult();
		System.out.println("---------------------\n" + usualBook.getCategories() + "\n"
				+ usualBook.getAuthors().iterator().next().getBooks() + "\n---------------------");

		// Select -> nicht das ganze Objekt bekommen, sondern nur nen String
		// oder so
		System.out.println("\n --------- SELECT ----------------------------- \n");
		// Query querySelect = session.createQuery("select nameF from Book where
		// nameL=Gilmore");
		Query querySelect = session.createQuery("select description from Book where isbn='0131428985'");
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
		Query query2 = session.createQuery("select distinct categoryName from Category");
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
		Query query3 = session
				.createQuery("select a.categoryName from Book b join b.categories a where b.isbn ='0131428985'");
		// query.setString("name", "MySQL");
		List<String> books = query3.list();
		for (String n : books) {
			System.out.println("Cat: " + n);
		}

		// Select Book by Category

		System.out.println("\n ------------------ getBookByCategory-----\n");
		Query query4 = session.createQuery("select b from Book b join b.categories a where a.categoryName='PHP'");
		// query.setString("name", "MySQL");
		List<Book> books1 = query4.list();
		for (Book n : books1) {
			System.out.println("Titel: " + n.getTitle() + " Cat: " + n.getCategories());
			// System.out.println("Titel: " + n);
		}

		session.getTransaction().commit();
		System.out.println("Done");
		System.exit(0);
	}

	public void jdbcStuff(JdbcTemplate jdbc) {
		int counter = 1;
		List<Author> authors = jdbc.query("SELECT * FROM PUBLIC.bookauthors WHERE nameF=? AND nameL=?",
				new String[] { "Jason", "Gilmore" }, new AuthorRowMapper());
		for (Author a : authors) {
			System.out.println("Autor " + counter++ + ": " + a.getNameF() + ", " + a.getNameL());
		}

		List<Book> list = jdbc.query(
				"SELECT * FROM PUBLIC.bookcategories, PUBLIC.bookcategoriesbooks, PUBLIC.bookdescriptions, PUBLIC.bookauthorbooks, PUBLIC.bookauthors",
				new BookRowMapper());
		for (Book o : list) {
			System.out.println(o.getTitle());
		}
		System.out.println(jdbc.queryForObject("select count(*) FROM PUBLIC.bookauthors", Integer.class));
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

}
