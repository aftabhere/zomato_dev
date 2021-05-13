package com.zomato.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidDetailsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidDetailsException(String message){
		super(message);
	}
	
	public InvalidDetailsException(String message,Throwable cause){
		super(message,cause);
	}

}
