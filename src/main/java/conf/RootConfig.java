package conf;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import appl.data.items.ArchiveBook;
import appl.data.items.Author;
import appl.data.items.Book;
import appl.data.items.Category;
import appl.data.items.Orderx;
import appl.data.items.PLZ;
import appl.data.items.User;
import appl.logic.admin.Initialization;
import exceptions.data.DatabaseInitializationException;

/**
 * This class is responsible for the configuration of several beans. Mainly, it
 * ensures the availability of beans which help to handle database connections
 * and transactions.
 * 
 * In this class, the packages {@code appl.logic.service} and
 * {@code appl.data.dao} are scanned for components which are supposed to be
 * available as bean.
 * 
 * @author Johannes
 * @author Madeleine
 *
 */
@Configuration
@ComponentScan({ "appl.logic.service", "appl.data.dao", "appl.data.builder", "appl.logic.admin" })
@EnableTransactionManagement
public class RootConfig {

	@Autowired
	Initialization init;

	/**
	 * This bean is necessary for the performance of transactions on the
	 * database.
	 * 
	 * @return a {@link PlatformTransactionManager} with the
	 *         {@code SessionFactory} configured below.
	 * @throws DatabaseInitializationException
	 *             if an error occurs during set up of the
	 *             {@code SessionFactory}
	 * 
	 * @see {@link #getSessionFactory()}
	 */
	@Bean
	public PlatformTransactionManager txManager() throws DatabaseInitializationException {
		return new HibernateTransactionManager(getSessionFactory());
	}

	/**
	 * This bean provides substantial access to all database functions.
	 * 
	 * @return a configured {@link SessionFactory}
	 * @throws DatabaseInitializationException
	 *             if an error occurs during initialization of the database
	 */
	@Bean
	public SessionFactory getSessionFactory() throws DatabaseInitializationException {
		return buildSessionFactory();
	}

	private SessionFactory buildSessionFactory() throws DatabaseInitializationException {
		try {
			org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
			cfg.addPackage("appl.data.items");
			cfg.addAnnotatedClass(Author.class);
			cfg.addAnnotatedClass(Book.class);
			cfg.addAnnotatedClass(Category.class);
			cfg.addAnnotatedClass(Orderx.class);
			cfg.addAnnotatedClass(PLZ.class);
			cfg.addAnnotatedClass(User.class);
			cfg.addAnnotatedClass(ArchiveBook.class);
			return cfg.setProperties(createProperties()).buildSessionFactory();
		} catch (HibernateException e) {
			System.err.println("Initial SessionFactory creation failed." + e.getMessage());
			throw new DatabaseInitializationException(e.getMessage());
		}
	}

	private Properties createProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.hbm2ddl.auto", "create");
		// FIXME Johannes, you know what to do!
		prop.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		// TODO Autoserver (automatic mixed mode)
		prop.setProperty("hibernate.connection.url", "jdbc:h2:./database/kirjanystaevaet;AUTO_SERVER=TRUE");
		// prop.setProperty("hibernate.c3p0.idle_test_period", "30");
		prop.setProperty("hibernate.c3p0.testConnectionOnCheckout", "true");
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		prop.setProperty("hibernate.current_session_context_class",
				"org.springframework.orm.hibernate5.SpringSessionContext");
		prop.setProperty("show_sql", "true");
		return prop;
	}

	// @Bean
	// public PasswordEncoder encoder() {
	// return new BCryptPasswordEncoder(11);
	// }

}
