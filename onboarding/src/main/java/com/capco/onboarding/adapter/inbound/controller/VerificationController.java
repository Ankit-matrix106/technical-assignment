package com.capco.onboarding.adapter.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.capco.onboarding.adapter.inbound.exception.IdempotencyKeyNotFoundException;
import com.capco.onboarding.adapter.inbound.mapper.DomainMapper;
import com.capco.onboarding.adapter.inbound.model.request.VerificationRequest;
import com.capco.onboarding.adapter.inbound.model.response.StartVerificationResponse;
import com.capco.onboarding.domain.port.inbound.StartVerificationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class VerificationController {
	
	@Autowired
	private StartVerificationService verificationService;
	
	@Autowired
	private DomainMapper domainMapper;
	
	@PostMapping("/start-verification")
	public RedirectView processVerification(@RequestHeader("Idempotency-Key") String idempotencyKey, @Valid @RequestBody final VerificationRequest request) {
		
		
		/*
		 * Optional<StartVerificationResponse> response1 = Optional.ofNullable(request)
		 * .map(domainMapper::toDomain) .map(verification ->
		 * verificationService.processVerification(verification)) ;
		 */
		if (idempotencyKey == null || idempotencyKey.isEmpty()) {
			throw new IdempotencyKeyNotFoundException("Idempotency-Key header is required.");
        }
		
		StartVerificationResponse response =  verificationService.processVerification(domainMapper.toDomain(request),idempotencyKey);
		
		return new RedirectView(response.verificationUrl());
	}
	
	@GetMapping("/record")
	public ResponseEntity<String> checkDuplicateRecord(){
		
		return ResponseEntity.ok("User exist in record");
	}
}
