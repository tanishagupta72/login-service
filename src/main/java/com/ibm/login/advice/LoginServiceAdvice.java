package com.ibm.login.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ibm.login.exception.UserAlreadyExistsException;
import com.ibm.login.model.ErrorResponse;

@RestControllerAdvice
public class LoginServiceAdvice {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException e)
	{
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ErrorResponse("FAILED","UserName already exists",new Date()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception e)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("FAILED",e.getLocalizedMessage(),new Date()));
	}
}
