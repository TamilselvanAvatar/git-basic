package com.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProductExceptionController {
	
	@ExceptionHandler (value = ProductNotFoundException.class)
	public ResponseEntity<Object> exception (){
		return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	
	}

}
