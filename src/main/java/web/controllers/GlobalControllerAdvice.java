package web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleHandlerNotFound(HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView("");
		mav.setViewName("error/404");
		return mav;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ModelAndView defaultHandler(HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView("");
		mav.setViewName("error");
		return mav;
	}
}
