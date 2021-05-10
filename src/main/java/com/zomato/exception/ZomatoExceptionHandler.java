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
		ResponseMessageVO exceptionMessage = new ResponseMessageVO();
		exceptionMessage.setCode("400");
		exceptionMessage.setSuccess(false);
		exceptionMessage.setMessage(ex.getMessage());
		exceptionMessage.setDetails(new ArrayList(Arrays.asList(exceptionMessage.getMessage())));
		return new ResponseEntity<>(exceptionMessage,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=DishDetailsUpdationException.class)
	public ResponseEntity<ResponseMessageVO> handleDishDetailsUpdationException(DishDetailsUpdationException ex){
		ResponseMessageVO exceptionMessage = new ResponseMessageVO();
		exceptionMessage.setCode("400");
		exceptionMessage.setSuccess(false);
		exceptionMessage.setMessage(ex.getMessage());
		exceptionMessage.setDetails(new ArrayList(Arrays.asList(exceptionMessage.getMessage())));
		return new ResponseEntity<>(exceptionMessage,HttpStatus.BAD_REQUEST);
		
	}

}
