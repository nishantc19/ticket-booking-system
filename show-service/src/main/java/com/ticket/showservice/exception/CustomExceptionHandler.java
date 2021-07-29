package com.ticket.showservice.exception;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler{
	
	private static final String BAD_REQUEST_MESSAGE = "Invalid request data.";
	private static final String CONFLICT_MESSAGE = "Resource access conflict.";
	private static final String SHOW_NOT_FOUND_MESSAGE = "Show not found.";
	private static final String DATE_TIME_PARSE_EXCEPTION_MESSAGE = "Invalid timestamp formate.";
	private static final String DATE_TIME_PARSE_EXCEPTION_DETAIL = "The timestamp format should be like: 2001-07-25T23:08:45";
	private static final String GENERIC_MESSAGE = "Some unknown error occured.";
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> detail = new ArrayList<String>();
		ex.getBindingResult().getAllErrors().forEach(
			(err)->{
				detail.add(err.getDefaultMessage()); 
			}
		);
		return new ErrorResponse(BAD_REQUEST_MESSAGE,detail);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public @ResponseBody ErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {
		List<String> detail = new ArrayList<String>();
		ex.getConstraintViolations().forEach(
			(err)->{
				detail.add(err.getMessage()); 
			}
		);
		return new ErrorResponse(BAD_REQUEST_MESSAGE,detail);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadSeatStatusException.class)
	public @ResponseBody ErrorResponse handleBadSeatStatusException(BadSeatStatusException ex) {
		List<String> detail = new ArrayList<String>();
		detail.add(ex.getMessage());
		return new ErrorResponse(BAD_REQUEST_MESSAGE,detail);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DateTimeParseException.class)
	public @ResponseBody ErrorResponse handleDateTimeParseException(DateTimeParseException ex) {
		List<String> detail = new ArrayList<String>();
		detail.add(DATE_TIME_PARSE_EXCEPTION_DETAIL);
		return new ErrorResponse(DATE_TIME_PARSE_EXCEPTION_MESSAGE,detail);
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(AlreadyModifiedException.class)
	public @ResponseBody ErrorResponse handleAlreadyModifiedException(AlreadyModifiedException ex) {
		List<String> detail = new ArrayList<String>();
		detail.add(ex.getMessage());
		return new ErrorResponse(CONFLICT_MESSAGE,detail);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ShowNotFoundException.class)
	public @ResponseBody ErrorResponse handleShowNotFoundException(ShowNotFoundException ex) {
		List<String> detail = new ArrayList<String>();
		detail.add(ex.getMessage());
		return new ErrorResponse(SHOW_NOT_FOUND_MESSAGE,detail);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorResponse handleAllExceptions(Exception ex) {
		List<String> detail = new ArrayList<String>();
		detail.add(ex.getMessage());
		return new ErrorResponse(GENERIC_MESSAGE,detail);
	}

}
