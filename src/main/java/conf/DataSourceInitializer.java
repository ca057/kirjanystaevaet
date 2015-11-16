package conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DataSourceInitializer {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DataSource dataSource = createDataSource();
		DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
		return dataSource;
	}

	private DatabasePopulator createDatabasePopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setContinueOnError(true);
		databasePopulator.addScript(new ClassPathResource("./resources/DataDump_h2.txt"));
		return databasePopulator;
	}

	private SimpleDriverDataSource createDataSource() {
		SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
		simpleDriverDataSource.setDriverClass(org.h2.Driver.class);
		simpleDriverDataSource.setUrl("jdbc:h2:./database/kirjanystaevaet;AUTO_RECONNECT=TRUE");

		simpleDriverDataSource.setUsername("");
		simpleDriverDataSource.setPassword("");
		return simpleDriverDataSource;
	}

	// @Bean
	// public DataSource getDataSource() {
	// // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
	// EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	// EmbeddedDatabase db =
	// builder.setType(EmbeddedDatabaseType.H2).addScript("classpath:resources/DataDump_h2.txt")
	// .build();
	//
	// try {
	// Class.forName("org.h2.Driver");
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// String url = "jdbc:h2:" + "./testdb.db";
	// String user = "aeter";
	// String password = "aeter";
	// Connection conn;
	// try {
	// conn = DriverManager.getConnection(url, user, password);
	// PreparedStatement ps2 = conn.prepareStatement("Show tables;");
	// ResultSet rs = ps2.executeQuery();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return db;
	// }
}
