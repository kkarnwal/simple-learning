/* **********************************************************************
 * Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
 * **********************************************************************
 * $Id:
 * $DateTime:
 * $Change:
 * $Author:
 * *********************************************************************/
package quick.poll.app.misc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Inject
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
	        HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(status.value());
		errorDetail.setTitle("Message Not Readable");
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());
		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Server Error", details);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
		// List<String> details = new ArrayList<>();
		// details.add(ex.getLocalizedMessage());
		// ErrorResponse error = new ErrorResponse("Record Not Found", details);
		//
		//
		// return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetail.setTitle("Resource Not Found");
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}

	@Override
	// @ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve,
	        HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
		// String requestPath = ;//(String)
		// request.getAttribute("javax.servlet.error.request_uri");
//		if (requestPath == null) {

			String requestPath =	 request.getContextPath();//getRequestURI();
//		}
		errorDetail.setTitle("Validation Failed");
		errorDetail.setDetail("Input validation failed");
		errorDetail.setDeveloperMessage(manve.getClass().getName());

		manve.getBindingResult().getFieldErrors().forEach(fe -> {
			List<ValidationError> list = errorDetail.getErrors().get(fe.getField());
			if (list == null) {
				list = new ArrayList<ValidationError>();
				errorDetail.getErrors().put(fe.getField(), list);
			}
			ValidationError validationError = new ValidationError();
			validationError.setCode(fe.getCode());
			validationError.setMessage(messageSource.getMessage(fe, null));// fe.getDefaultMessage());
			list.add(validationError);
		});
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}

	


}
