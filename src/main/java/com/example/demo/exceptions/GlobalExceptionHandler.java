package com.example.demo.exceptions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> HandleException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new LinkedHashMap<>();
		List<FieldError> list = ex.getFieldErrors();
		for (FieldError f : list) {
			String fieldName = f.getField();
			String errorMsg = f.getDefaultMessage();
			errorMap.put(fieldName, errorMsg);
		}
		ResponseEntity<Map<String, String>> rEntity = new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		return rEntity;
	}
	
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<String> handlingException(AdminNotFoundException ex) {
		String msg = ex.getMessage();
		ResponseEntity<String> rEntity = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return rEntity;
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<String> handlingException(BookingNotFoundException ex) {
		String msg = ex.getMessage();
		ResponseEntity<String> rEntity = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return rEntity;
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handlingException(CustomerNotFoundException ex) {
		String msg = ex.getMessage();
		ResponseEntity<String> rEntity = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return rEntity;
	}
	
	@ExceptionHandler(DriverNotFoundException.class)
	public ResponseEntity<String> handlingException(DriverNotFoundException ex) {
		String msg = ex.getMessage();
		ResponseEntity<String> rEntity = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return rEntity;
	}
	
	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<String> handlingException(RouteNotFoundException ex) {
		String msg = ex.getMessage();
		ResponseEntity<String> rEntity = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return rEntity;
	}
	
	@ExceptionHandler(VehicleNotFoundException.class)
	public ResponseEntity<String> handlingException(VehicleNotFoundException ex) {
		String msg = ex.getMessage();
		ResponseEntity<String> rEntity = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		return rEntity;
	}
	
//
//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<String> handlingException(ConstraintViolationException ex) {
//		Throwable msg = ex.getCause();
//		ResponseEntity<String> rEntity = new ResponseEntity<>(msg, HttpStatus.LENGTH_REQUIRED);
//		return rEntity;
//	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> HandleException(ConstraintViolationException ex) {
		
		String msg = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.reduce((m1,m2)->m1+"\n"+m2).orElse("No errors");
		ResponseEntity<String> rEntity = new ResponseEntity<>(msg, HttpStatus.LENGTH_REQUIRED);
		return rEntity;
	}
	
	
}
