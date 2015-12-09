package conf;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import appl.logic.orders.OrderAdmin;
import appl.logic.orders.impl.OrderAdminImpl;

@Configuration
@ComponentScan({ "appl.data.dao.impl", "appl.logic.service.impl" })
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

	@Bean
	public OrderAdmin orderAdminBean() {
		return new OrderAdminImpl();
	}

}
