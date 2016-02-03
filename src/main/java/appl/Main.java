package appl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import conf.RootConfig;
import exceptions.data.EntityDoesNotExistException;
import exceptions.data.PrimaryKeyViolationException;

public class Main {

	public static void main(String[] args) throws PrimaryKeyViolationException, EntityDoesNotExistException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class);
		// new QueryFun().doSomeTesting2(ctx);
		// new QueryFun().doSomeTesting(sessionFactory);
		// FIXME DB auch bei Serverbetrieb anlegen!

		// JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);
		// new QueryFun().jdbcStuff(jdbc);

		// OrderTesting
		//new QueryFun().doSomeOrderTesting(ctx);
		
		//new QueryFun().testDao(ctx);
		//new QueryFun().testBookInsert(ctx);
		new QueryFun().testExceptions(ctx);

	}

}