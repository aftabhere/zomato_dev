package com.zomato.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDishRegistrationException extends RuntimeException {
	public InvalidDishRegistrationException(String message){
		super(message);
	}
	
	InvalidDishRegistrationException(String message,Throwable cause){
		super(message,cause);
	}

}
