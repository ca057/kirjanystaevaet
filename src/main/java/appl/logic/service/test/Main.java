package appl.logic.service.test;

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

		// JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);
		// new QueryFun().jdbcStuff(jdbc);

		// OrderTesting
		// new QueryFun().doSomeOrderTesting(ctx);

		// new QueryFun().testDao(ctx);
		// new QueryFun().testBookInsert(ctx);
		// new QueryFun().testExceptions(ctx);
		ServiceTest st = new ServiceTest();

//		st.testCategoryInsert(ctx);
//		st.testCategoryDelete(ctx);
//
//		st.testAuthorInsert(ctx);
//		st.testAuthorDelete(ctx);
//		//
//		st.testInsertBook(ctx);
		// qf.testDeleteBook(ctx);
		st.testInsertOrder(ctx);
//		st.testBestsellers(ctx);
//		st.testExistingCategory(ctx);
//		st.testDeleteCategoryOfBook(ctx);
//		st.testAddCategoryToBook(ctx);
//		st.testUpdateBook(ctx);
//		st.testGetBooksSell(ctx);
//		st.testGetShelveWarmers(ctx);
//		st.testUpdateCategory(ctx);
//		st.testUpdateAuthor(ctx);
//		st.testRangeOfBookList(ctx);
		//

//		st.testStatistics(ctx);

		// qf.testGetAuthorsByIsbn(ctx);;
	}

}