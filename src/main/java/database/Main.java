package database;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);
		
		System.out.println(jdbc.queryForObject("select count(*) from nameF", Integer.class));
	}

}
