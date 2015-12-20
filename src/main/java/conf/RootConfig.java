package conf;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import appl.data.items.Order;
import appl.data.items.PLZ;
import appl.data.items.User;

@Configuration
@ComponentScan({ "appl.logic.service", "appl.data.dao" })
@EnableTransactionManagement
public class RootConfig {

	@Bean
	public PlatformTransactionManager txManager() throws HibernateException, SQLException {
		return new HibernateTransactionManager(getSessionFactory());
	}

	@Bean
	public SessionFactory getSessionFactory() {
		return buildSessionFactory();
	}

	private SessionFactory buildSessionFactory() {
		try {
			org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
			// FIXME Pfad für Server anpassen
			if (Files.notExists(Paths.get("./database/kirjanystaevaet.mv.db"))) {
				cfg.setProperty("hibernate.hbm2ddl.auto", "create");
				cfg.setProperty("hibernate.hbm2ddl.import_files", "/import.sql");
			}
			cfg.addPackage("appl.data.items");
			cfg.addAnnotatedClass(Author.class);
			cfg.addAnnotatedClass(Book.class);
			cfg.addAnnotatedClass(Category.class);
			cfg.addAnnotatedClass(Order.class);
			cfg.addAnnotatedClass(PLZ.class);
			cfg.addAnnotatedClass(User.class);
			return cfg.setProperties(createProperties()).buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	private Properties createProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		prop.setProperty("hibernate.connection.url", "jdbc:h2:./database/kirjanystaevaet");
		// prop.setProperty("hibernate.connection.username", "");
		// prop.setProperty("hibernate.connection.password", "");
		prop.setProperty("hibernate.c3p0.min_size", "5");
		prop.setProperty("hibernate.c3p0.max_size", "20");
		prop.setProperty("hibernate.c3p0.timeout", "300");
		prop.setProperty("hibernate.c3p0.max_statements", "50");
		prop.setProperty("hibernate.c3p0.idle_test_period", "3000");
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		prop.setProperty("hibernate.current_session_context_class",
				"org.springframework.orm.hibernate5.SpringSessionContext");
		prop.setProperty("show_sql", "true");
		return prop;
	}

	// @Bean
	// public EntityManager entityManagerFactory() {
	// return entityManagerFactoryBean().getObject().createEntityManager();
	// }

}
