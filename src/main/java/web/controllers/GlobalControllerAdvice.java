package web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * This {@code ControllerAdvice} handles thrown exceptions to show a nice and
 * helpful error view to the user.
 * 
 * @author Christian
 *
 */
@ControllerAdvice
public class GlobalControllerAdvice {

	/**
	 * Handles the {@link NoHandlerFoundException} and returns the corresponding
	 * view as a response with a HTTP status code of 404.
	 * 
	 * @param request
	 * @param e
	 * @return the {@link ModelAndView} with the name of the error view for 404
	 *         set as view name
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleHandlerNotFound(HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView("");
		mav.setViewName("error/404");
		return mav;
	}

	/**
	 * Handles all exceptions and returns a default error view as a response
	 * with a HTTP status code of 500.
	 * 
	 * @param request
	 * @param e
	 * @return the {@link ModelAndView} with the name of the default error view
	 *         set as view name
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ModelAndView defaultHandler(HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView("");
		mav.setViewName("error");
		return mav;
	}
}
