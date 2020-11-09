package com.mbrdi.service.mbrdiassignment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice()
public class ExceptionHandlerControllerAdvice {
	Logger logger = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);	
	
	@SuppressWarnings( { "unchecked", "rawtypes" } )
	@ExceptionHandler(Exception.class)
	@ResponseStatus
	public ResponseEntity<Error> handleAllException(final Exception ex) {
		logger.error("Exception occurred: {}",ex);
		Error error = new Error();
		error.setErrorDescription("BAD_REQUEST_ERROR");
		error.setError("400");
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@SuppressWarnings( "unchecked" )
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus
	public ResponseEntity<Error> customExceptions(final ServiceException ex) {

		return (ResponseEntity<Error>) getResponseEntity(ex);
	}
	
	/*
	 * Handling of custom Exceptions private method
	 */
	private ResponseEntity<?> getResponseEntity(ServiceException serviceException) {

		ResponseEntity<Object> responseEntity = null;
		Error error = new Error();
		error.setErrorDescription(serviceException.getErrormessage());
		error.setError(serviceException.getErrorCode());
		if("400".equalsIgnoreCase(error.getError())) {
           responseEntity = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		if("204".equalsIgnoreCase(error.getError())) {
             responseEntity = new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
		}
		if("500".equalsIgnoreCase(error.getError())) {
           responseEntity = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

}
