package appl.logic.admin.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import appl.logic.admin.DataKraken;

@Aspect
public class DataKrakenImpl implements DataKraken {

	@Override
	@Before("execution(* web.controllers.SingleBookController.getBook(..))")
	public void logRequestedIsbn() {
		System.out.println("Kraken");
	}

}
