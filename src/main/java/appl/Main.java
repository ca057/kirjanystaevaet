package appl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import appl.items.Author;
import appl.items.Book;
import appl.items.Category;
import conf.HibernateUtil;

public class Main {

	// public static void main(String[] args) {
	// ApplicationContext ctx = new
	// AnnotationConfigApplicationContext(RootConfig.class);
	// JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);
	//
	// int counter = 1;
	// List<Author> authors = jdbc.query("SELECT * FROM PUBLIC.bookauthors WHERE
	// nameF=? AND nameL=?",
	// new String[] { "Jason", "Gilmore" }, new AuthorRowMapper());
	// for (Author a : authors) {
	// System.out.println("Autor " + counter++ + ": " + a.getNameF() + ", " +
	// a.getNameL());
	// }
	//
	// List<Book> list = jdbc.query(
	// "SELECT * FROM PUBLIC.bookcategories, PUBLIC.bookcategoriesbooks,
	// PUBLIC.bookdescriptions, PUBLIC.bookauthorbooks, PUBLIC.bookauthors",
	// new BookRowMapper());
	// for (Book o : list) {
	// System.out.println(o.getTitle());
	// }
	// // System.out.println(jdbc.queryForObject("select count(*) FROM
	// // PUBLIC.bookauthors", Integer.class));
	// }

	public static void main(String[] args) {

		System.out.println("Hibernate many to many (Annotation)");
		// ApplicationContext ctx = new
		// AnnotationConfigApplicationContext(RootConfig.class);
		// Session session = ctx.getBean(Session.class);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();

		Book book = new Book("1213452", "title", "Desc", 10.00, "String publisher", "String pubdate", "String edition",
				10, null, null);

		Author author = new Author();
		author.setNameF("Hans");
		author.setNameL("Peter");

		Set<Book> books = new HashSet<>();

		Set<Author> authors = new HashSet<>();
		authors.add(author);

		books.add(new Book("1213452", "title", "Desc", 10.00, "String publisher", "String pubdate", "String edition",
				10, null, authors));
		books.add(new Book("12134394", "title", "Desc", 10.00, "String publisher", "String pubdate", "String edition",
				10, null, authors));

		Category category1 = new Category("Category1", books);
		Category category2 = new Category("Category2", null);

		Set<Category> categories = new HashSet<Category>();
		categories.add(category1);
		categories.add(category2);

		book.setCategories(categories);

		session.save(book);

		session.getTransaction().commit();
		System.out.println("Done");
	}

}
