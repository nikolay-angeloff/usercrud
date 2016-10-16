package com.usercrud.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {
	@ExceptionHandler(value = Exception.class) 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void emptyResult() {
		
	}
}
