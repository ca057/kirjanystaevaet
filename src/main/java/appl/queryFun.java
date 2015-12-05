package appl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import appl.databasemanagement.rowMapper.AuthorRowMapper;
import appl.databasemanagement.rowMapper.BookRowMapper;
import appl.items.Author;
import appl.items.Book;
import appl.items.Category;

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
		Book usualBook = (Book) session.createQuery("from Book where ISBN='0131428985'").uniqueResult();
		System.out.println("---------------------\n" + usualBook.getCategories() + "\n"
				+ usualBook.getAuthors().iterator().next().getBooks() + "\n---------------------");

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
