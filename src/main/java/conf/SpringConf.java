package conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import appl.databasemanagement.BookDataAdmin;
import appl.databasemanagement.BookDataQuery;
import appl.databasemanagement.databaseImpl.BookDataAdminImpl;
import appl.databasemanagement.databaseImpl.BookDataQueryImpl;
import appl.ordermanagement.Order;
import appl.ordermanagement.ordermanagementImpl.OrderImpl;
import appl.queryManagement.QueryCreator;
import appl.queryManagement.QueryCreatorImpl;

@Configuration
public class SpringConf {
	// Beans for Controller
	@Bean
	public QueryCreator queryCreatorBean(){
		return new QueryCreatorImpl();
	}

	/*
	@Bean
	public Order orderBean(){
		return new OrderImpl();
	}
	*/
	// Beans for Model
	@Bean
	public BookDataAdmin bookDataAdminBean(){
		return new BookDataAdminImpl();
		
	}
	@Bean
	public BookDataQuery bookDataQueryBean(){
		return new BookDataQueryImpl();
	}
}
