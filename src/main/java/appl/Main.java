package appl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import appl.databasemanagement.rowMapper.AuthorRowMapper;
import appl.databasemanagement.rowMapper.BookRowMapper;
import appl.items.Author;
import appl.items.Book;
import conf.RootConfig;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class);
		JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);

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
		// System.out.println(jdbc.queryForObject("select count(*) FROM
		// PUBLIC.bookauthors", Integer.class));
	}
}
