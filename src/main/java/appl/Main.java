package appl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import conf.RootConfig;
import conf.SecurityConfig;
import exceptions.data.CategoryExistsException;
import exceptions.data.DatabaseException;
import exceptions.data.PrimaryKeyViolationException;

public class Main {

	public static void main(String[] args)
			throws PrimaryKeyViolationException, CategoryExistsException, DatabaseException {
		// ApplicationContext ctx = new
		// AnnotationConfigApplicationContext(RootConfig.class);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class, SecurityConfig.class);

		// new QueryFun().doSomeTesting2(ctx);
		// new QueryFun().doSomeTesting(sessionFactory);
		// FIXME DB auch bei Serverbetrieb anlegen!

		// JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);
		// new QueryFun().jdbcStuff(jdbc);

		// OrderTesting
		// new QueryFun().doSomeOrderTesting(ctx);

		// new QueryFun().testDao(ctx);
		// new QueryFun().testBookInsert(ctx);
		// new QueryFun().testExceptions(ctx);
		QueryFun qf = new QueryFun();

		// qf.testCategoryInsert(ctx);
		// qf.testCategoryDelete(ctx);

		// qf.testAuthorInsert(ctx);
		// qf.testAuthorDelete(ctx);

		//qf.testInsertBook(ctx);
		// qf.testDeleteBook(ctx);
		//qf.testInsertOrder(ctx);

		//qf.testUser(ctx);
		//qf.testStatistics(ctx);

		qf.testGetAuthorsByIsbn(ctx);;
	}

}