package com.zomato.exception;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zomato.response.ResponseMessageVO;

@ControllerAdvice
public class ZomatoExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value=InvalidDishRegistrationException.class)
	public ResponseEntity<ResponseMessageVO> handleInvalidDishRegistrationException(InvalidDishRegistrationException ex){
		ResponseMessageVO exceptionMessage = createExceptionResponse(ex);
		exceptionMessage.setCode("400");
		return new ResponseEntity<>(exceptionMessage,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=DishDetailsValidationException.class)
	public ResponseEntity<ResponseMessageVO> handleDishDetailsUpdationException(DishDetailsValidationException ex){
		ResponseMessageVO exceptionMessage = createExceptionResponse(ex);
		exceptionMessage.setCode("400");
		return new ResponseEntity<>(exceptionMessage,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=DishNotFoundException.class)
	public ResponseEntity<ResponseMessageVO> handleDishNotFoundException(DishNotFoundException ex){
		ResponseMessageVO exceptionMessage = createExceptionResponse(ex);				
		exceptionMessage.setCode("400");
		return new ResponseEntity<>(exceptionMessage,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value=InvalidDetailsException.class)
	public ResponseEntity<ResponseMessageVO> handledeleteDishById(InvalidDetailsException ex){
		ResponseMessageVO exceptionMessage = createExceptionResponse(ex);
		exceptionMessage.setCode("406");
		return new ResponseEntity<>(exceptionMessage,HttpStatus.NOT_ACCEPTABLE);
	}
	
	public ResponseMessageVO createExceptionResponse(Exception ex){
		ResponseMessageVO exceptionMessage = new ResponseMessageVO();
		exceptionMessage.setSuccess(false);
		exceptionMessage.setMessage(ex.getMessage());
		exceptionMessage.setDetails(new ArrayList<>(Arrays.asList(exceptionMessage.getMessage())));
		return exceptionMessage;
	}
	

}
