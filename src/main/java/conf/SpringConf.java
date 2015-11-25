package conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import appl.queryManagement.QueryCreator;
import appl.queryManagement.QueryCreatorImpl;

@Configuration
public class SpringConf {
	@Bean
	public QueryCreator queryCreatorBean(){
		return new QueryCreatorImpl();
	}

}
