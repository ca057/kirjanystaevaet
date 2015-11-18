package conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import database.BookDataAdmin;
import databaseImpl.BookDataAdminImpl;

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

}
