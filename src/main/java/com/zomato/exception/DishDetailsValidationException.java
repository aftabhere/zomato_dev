package com.zomato.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DishDetailsValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DishDetailsValidationException(String message){
		super(message);
	}
	
	public DishDetailsValidationException(String message,Throwable cause) {
		super(message,cause);
	}
}
