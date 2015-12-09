package conf;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import appl.data.dao.BookDAO;
import appl.data.dao.impl.BookDAOImpl;
import appl.logic.orders.OrderAdmin;
import appl.logic.orders.impl.OrderAdminImpl;
import appl.logic.service.BookService;
import appl.logic.service.CategoryService;
import appl.logic.service.impl.BookServiceImpl;
import appl.logic.service.impl.CategoryServiceImpl;

@Configuration
public class RootConfig {

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		// return new JdbcTemplate(new DataSourceInitializer().getDataSource());
		return null;
	}

	@Bean
	public SessionFactory getSessionFactory() throws HibernateException, SQLException {
		return HibernateUtil.getSessionFactory();
	}

	// Beans for Controller
	@Bean
	public CategoryService categoryServiceBean() {
		return new CategoryServiceImpl();
	}

	@Bean
	public BookService bookServiceBean() {
		return new BookServiceImpl();
	}

	@Bean
	public BookDAO bookDataQueryBean() {
		return new BookDAOImpl();
	}

	@Bean
	public OrderAdmin orderAdminBean() {
		return new OrderAdminImpl();
	}

}
