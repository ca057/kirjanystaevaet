package appl;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import appl.data.items.Book;
import conf.RootConfig;

public class Main {

	public static void main(String[] args)throws InterruptedException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfig.class);
		//new QueryFun().doSomeTesting2(ctx);
		// new QueryFun().doSomeTesting(sessionFactory);
		// FIXME DB auch bei Serverbetrieb anlegen!

		// JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);
		// new QueryFun().jdbcStuff(jdbc);
		
		// OrderTesting
		//new QueryFun().doSomeOrderTesting(ctx);
		SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);

		// ToDo
		// Code um Index das erste Mal aufzubauen, wo muss der eigentlich hin??
		
		/*FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());

		if (Files.notExists(Paths.get("database/indexes/appl.data.itmes.Book"))) {
			System.out.println("\n\n-----------------------\n\n Baue den Index das erste Mal\n\n-----------------\n\n");
			fullTextSession.createIndexer().startAndWait();
		}
		
		*/
		/*
		EntityManager em = entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager =
		    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		em.getTransaction().begin();

		// create native Lucene query unsing the query DSL
		// alternatively you can write the Lucene query using the Lucene query parser
		// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
		    .buildQueryBuilder().forEntity(Book.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
		  .keyword()
		  .onFields("title", "subtitle", "authors.name")
		  .matching("Java rocks!")
		  .createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery =
		    fullTextEntityManager.createFullTextQuery(luceneQuery, Book.class);

		// execute search
		List result = jpaQuery.getResultList();

		em.getTransaction().commit();
		em.close();
		*/
		
		// Search Factory erstellen
		/*SearchFactory searchFactory = fullTextSession.getSearchFactory();
		// QueryBuilder
		QueryBuilder mythQB = searchFactory.buildQueryBuilder().forEntity( Book.class ).get();
		*/
		new QueryFun().testDao(ctx);
	}

}
