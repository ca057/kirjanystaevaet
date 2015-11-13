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
	}
}
