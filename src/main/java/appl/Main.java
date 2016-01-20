package appl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import conf.RootConfig;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class);
		//new QueryFun().doSomeTesting2(ctx);
		// new QueryFun().doSomeTesting(sessionFactory);
		// FIXME DB auch bei Serverbetrieb anlegen!

		// JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);
		// new QueryFun().jdbcStuff(jdbc);
		
		// OrderTesting
		//new QueryFun().doSomeOrderTesting(ctx);
		
		new QueryFun().testDao(ctx);
	}

}
