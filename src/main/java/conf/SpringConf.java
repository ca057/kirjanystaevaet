package conf;

// TODO für was brauchen wir die?

//@Configuration
//@EnableTransactionManagement
//@ComponentScan({ "appl.data.dao.impl", "appl.logic.service.impl" })
public class SpringConf {

	// @Bean
	// public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
	// {
	// LocalContainerEntityManagerFactoryBean em = new
	// LocalContainerEntityManagerFactoryBean();
	// em.setDataSource(dataSource());
	// em.setPackagesToScan(new String[] { "appl.data.items" });
	//
	// JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	// em.setJpaVendorAdapter(vendorAdapter);
	// em.setJpaProperties(additionalProperties());
	//
	// return em;
	// }
	//
	// @Bean
	// public DataSource dataSource() {
	// DriverManagerDataSource dataSource = new DriverManagerDataSource();
	// dataSource.setDriverClass("org.h2.Driver");
	// dataSource.setJdbcUrl("jdbc:h2:./database/kirjanystaevaet");
	// // dataSource.setUser("tutorialuser");
	// // dataSource.setPassword("tutorialmy5ql");
	// return dataSource;
	// }
	//
	// Properties additionalProperties() {
	// Properties properties = new Properties();
	// if (Files.notExists(Paths.get("./database/kirjanystaevaet.mv.db"))) {
	// properties.setProperty("hibernate.hbm2ddl.auto", "create");
	// properties.setProperty("hibernate.hbm2ddl.import_files", "/import.sql");
	// // cfg.setProperty(propertyName, value);
	// }
	// properties.setProperty("hibernate.dialect",
	// "org.hibernate.dialect.H2Dialect");
	// return properties;
	// }
	//
	// @Bean
	// public PlatformTransactionManager transactionManager() {
	// JpaTransactionManager transactionManager = new JpaTransactionManager();
	// transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
	// return transactionManager;
	// }

}
