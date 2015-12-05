package conf;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration cfg = new Configuration();
			if (Files.notExists(Paths.get("./database/kirjanystaevaet.mv.db"))) {
				cfg.setProperty("hibernate.hbm2ddl.auto", "create");
				cfg.setProperty("hibernate.hbm2ddl.import_files", "/import.sql");
			}
			return cfg.configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
