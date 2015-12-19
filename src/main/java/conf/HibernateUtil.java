package conf;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private final SessionFactory sessionFactory = buildSessionFactory();

	private SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration cfg = new Configuration();
			// FIXME Pfad für Server anpassen
			if (Files.notExists(Paths.get("./database/kirjanystaevaet.mv.db"))) {
				cfg.setProperty("hibernate.hbm2ddl.auto", "create");
				cfg.setProperty("hibernate.hbm2ddl.import_files", "/import.sql");
				// cfg.setProperty(propertyName, value);
			}
			cfg.setProperties(createProperties());
			return cfg.buildSessionFactory();
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
		prop.setProperty("hibernate.current_session_context_class", "thread");
		prop.setProperty("show_sql", "true");
		return prop;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
