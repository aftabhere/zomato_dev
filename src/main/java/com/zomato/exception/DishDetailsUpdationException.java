package com.zomato.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DishDetailsUpdationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DishDetailsUpdationException(String message){
		super(message);
	}
	
	public DishDetailsUpdationException(String message,Throwable cause) {
		super(message,cause);
	}
}
