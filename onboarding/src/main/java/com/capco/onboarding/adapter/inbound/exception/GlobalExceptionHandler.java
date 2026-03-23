package com.capco.onboarding.adapter.inbound.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capco.onboarding.adapter.inbound.model.response.ErrorResponse;
import com.capco.onboarding.domain.exception.InvalidIdentTypeException;
import com.capco.onboarding.domain.exception.SessionCreationFailedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IdempotencyKeyNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleIdempotencyKeyNotFoundException(IdempotencyKeyNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ErrorResponse(ex.getMessage()));
	}
	
	@ExceptionHandler(InvalidIdentTypeException.class)
	public ResponseEntity<ErrorResponse> handleInvalidIdentTypeException(InvalidIdentTypeException ex){
		return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ErrorResponse(ex.getMessage()));
	}
	
	@ExceptionHandler(SessionCreationFailedException.class)
	public ResponseEntity<ErrorResponse> handleSessionCreationFailedException(SessionCreationFailedException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorResponse(ex.getMessage()));
	}
}
