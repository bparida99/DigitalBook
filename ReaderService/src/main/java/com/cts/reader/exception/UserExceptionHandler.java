package com.cts.reader.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DigitalBooksException.class)
	public ResponseEntity<Object> handleUserException(DigitalBooksException exp,WebRequest request)
	{
		ErrorDetails error=new ErrorDetails(new Date(), exp.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exp)
	{
		Map<String,String> errors =new HashMap<>();
		exp.getConstraintViolations().forEach(constv->{
			errors.put("message",constv.getMessage() );
			errors.put("path", (constv.getPropertyPath()).toString());
		}
		);
		
		return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
