package com.example.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String>handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach((error) -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return errors;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFoundException(ResourceNotFoundException resourceNotFoundException) {
		System.out.println("inside global exception handling");
		return new ResponseEntity<String>(resourceNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
