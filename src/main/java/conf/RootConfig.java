package conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import appl.database.BookDataAdmin;
import appl.database.BookDataQuery;
import appl.database.databaseImpl.BookDataAdminImpl;
import appl.database.databaseImpl.BookDataQueryImpl;

@Configuration
public class RootConfig {

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(new DataSourceInitializer().getDataSource());
	}

	@Bean
	public BookDataAdmin getBookDataAdmin() {
		return new BookDataAdminImpl();
	}

	@Bean
	public BookDataQuery getBookDataQuery() {
		return new BookDataQueryImpl();
	}

}
