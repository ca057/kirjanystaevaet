package web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import appl.logic.service.BookService;

/**
 * The {@code HandlerInterceptorImpl} is used to set global attributes to the
 * model for the views.
 * 
 * @author Christian
 *
 */
@Component
public class HandlerInterceptorImpl extends HandlerInterceptorAdapter {

	@Autowired
	private BookService bookService;

	/*
	 * Used to set the category names to the model to display them in the
	 * navigation. (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
	 * postHandle(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			modelAndView.addObject("navigation", bookService.getAllCategoryNames());
		}
	}
}
