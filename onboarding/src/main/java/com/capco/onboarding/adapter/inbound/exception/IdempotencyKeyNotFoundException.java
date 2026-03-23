package com.capco.onboarding.adapter.inbound.exception;

public class IdempotencyKeyNotFoundException extends RuntimeException{

	public IdempotencyKeyNotFoundException(String message) {
		super(message);
	}

	
}
