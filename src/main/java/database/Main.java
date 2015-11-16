package database;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import conf.RootConfig;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class);
		JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);

		System.out.println(jdbc.queryForObject("select count(*) FROM PUBLIC.bookauthors", Integer.class));
		// jdbc.execute("INSERT INTO PUBLIC.bookauthors (`AuthorID`, `nameF`,
		// `nameL`) VALUES (1, 'Jason', 'Gilmore');"); -> Funktioniert
		// (Fehlermeldung kommt)
		jdbc.execute("DELETE FROM PUBLIC.bookauthors WHERE `nameF`='Jason' AND `nameL`='Gilmore';");
		jdbc.execute("DELETE FROM PUBLIC.bookauthors WHERE `nameF`='Tschäisen' AND `nameL`='Gilmore';");
		jdbc.execute("INSERT INTO PUBLIC.bookauthors (`nameF`, `nameL`) VALUES ('Jason', 'Gilmore');");
		jdbc.execute("INSERT INTO PUBLIC.bookauthors (`nameF`, `nameL`) VALUES ('Tschäisen', 'Gilmore');");
		System.out.println(jdbc.queryForObject("select count(*) FROM PUBLIC.bookauthors", Integer.class));
	}
}
