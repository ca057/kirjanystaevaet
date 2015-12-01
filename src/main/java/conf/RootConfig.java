package conf;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import appl.databasemanagement.BookDataAdmin;
import appl.databasemanagement.BookDataQuery;
import appl.databasemanagement.databaseImpl.BookDataAdminImpl;
import appl.databasemanagement.databaseImpl.BookDataQueryImpl;
import appl.ordermanagement.OrderAdmin;
import appl.ordermanagement.ordermanagementImpl.OrderAdminImpl;
import appl.queryManagement.QueryCreator;
import appl.queryManagement.QueryCreatorImpl;

@Configuration
public class RootConfig {

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(new DataSourceInitializer().getDataSource());
	}

	@Bean
	public Session getSession() throws HibernateException, SQLException {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Bean
	public BookDataAdmin getBookDataAdmin() {
		return new BookDataAdminImpl();
	}

	@Bean
	public BookDataQuery getBookDataQuery() {
		return new BookDataQueryImpl();
	}

	// Beans for Controller
	@Bean
	public QueryCreator queryCreatorBean() {
		return new QueryCreatorImpl();
	}

	/*
	 * @Bean public Order orderBean(){ return new OrderImpl(); }
	 */
	// Beans for Model
	@Bean
	public BookDataAdmin bookDataAdminBean() {
		return new BookDataAdminImpl();

	}

	@Bean
	public BookDataQuery bookDataQueryBean() {
		return new BookDataQueryImpl();
	}

	@Bean
	public OrderAdmin orderAdminBean() {
		return new OrderAdminImpl();
	}

}
