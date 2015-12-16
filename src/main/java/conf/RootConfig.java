package conf;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@ComponentScan({ "appl.data.dao.impl", "appl.logic.service.impl" })
public class RootConfig {

	@Bean
	public SessionFactory getSessionFactory() throws HibernateException, SQLException {
		return HibernateUtil.getSessionFactory();
	}

	@Bean
	public PlatformTransactionManager txManager() throws HibernateException, SQLException {
		return new HibernateTransactionManager(getSessionFactory());
	}

	// @Bean
	// public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
	// {
	// return null;
	// }
	//
	// @Bean
	// public PlatformTransactionManager transactionManager() {
	// JpaTransactionManager transactionManager = new JpaTransactionManager();
	// transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
	// return transactionManager;
	// }

}
