package conf;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "appl.logic.service", "appl.data.dao", "appl.data.items" })
@EnableTransactionManagement
public class RootConfig {

	// @Bean
	// public SessionFactory getSessionFactory() throws HibernateException,
	// SQLException {
	// return HibernateUtil.getSessionFactory();
	// LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
	// lsfb.setDataSource(dataSource());
	// lsfb.setHibernateProperties(additionalProperties());
	// try {
	// lsfb.afterPropertiesSet();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// SessionFactory sessionFactory = lsfb.getObject();
	// if (lsfb.getObject() != null) {
	// System.out.println("lsfb: " + lsfb.getObject());
	// } else {
	// System.out.println("lsfb ist null");
	// }
	//
	// return lsfb.getObject();
	// }

	// @Bean
	// public SessionFactory getSessionFactory() throws HibernateException,
	// SQLException {
	// return new HibernateUtil().getSessionFactory();
	// }

	@Bean
	public PlatformTransactionManager txManager() throws HibernateException, SQLException {
		return new HibernateTransactionManager(getSessionFactory());
	}

	private SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
			// FIXME Pfad für Server anpassen
			if (Files.notExists(Paths.get("./database/kirjanystaevaet.mv.db"))) {
				cfg.setProperty("hibernate.hbm2ddl.auto", "create");
				cfg.setProperty("hibernate.hbm2ddl.import_files", "/import.sql");
			}
			return cfg.setProperties(createProperties()).buildSessionFactory(
			// new
			// StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).applySetting(Environment.DATASOURCE,
			// getDataSource()).build()
			);
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

	@Bean
	public SessionFactory getSessionFactory() {
		return buildSessionFactory();
	}

	/**
	 * @return
	 */
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DataSource dataSource = createDataSource();
		// DatabasePopulatorUtils.execute(createDatabasePopulator(),
		// dataSource);
		return dataSource;
	}

	private DatabasePopulator createDatabasePopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setContinueOnError(true);
		// TODO Ist das gut so?
		if (!(Files.exists(Paths.get("./database/kirjanystaevaet.mv.db")))) {
			databasePopulator.addScript(new ClassPathResource("./DataDump_h2.txt"));
		}
		return databasePopulator;
	}

	private SimpleDriverDataSource createDataSource() {
		SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
		simpleDriverDataSource.setDriverClass(org.h2.Driver.class);
		simpleDriverDataSource.setUrl("jdbc:h2:./database/kirjanystaevaet;AUTO_RECONNECT=TRUE");

		// TODO: Das muss dann ausgelagert werden
		// simpleDriverDataSource.setUsername("");
		// simpleDriverDataSource.setPassword("");
		return simpleDriverDataSource;
	}

	// @Bean
	// public EntityManager entityManagerFactory() {
	// return entityManagerFactoryBean().getObject().createEntityManager();
	// }

}
