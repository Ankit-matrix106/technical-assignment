package com.capco.onboarding.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capco.onboarding.adapter.inbound.model.response.StartVerificationResponse;
import com.capco.onboarding.adapter.outbound.entity.IdempotencyKeyEntity;
import com.capco.onboarding.adapter.outbound.mapper.IdentificationTypeMapperStrategy;
import com.capco.onboarding.adapter.outbound.repository.IdempotencyKeyRepository;
import com.capco.onboarding.domain.model.Verification;
import com.capco.onboarding.domain.model.VerificationResponse;
import com.capco.onboarding.domain.port.inbound.StartVerificationService;
import com.capco.onboarding.domain.port.outbound.VerificationPersistence;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VerificationServiceImpl implements StartVerificationService {

	@Autowired
	private VerificationPersistence verificationPersistence;
	
	@Autowired
	private IdentificationTypeMapperStrategy identificationTypeMapperStrategy;
	
	@Autowired
	private IdempotencyKeyRepository idempotencyKeyRepository;
	
	
	@Override
	public StartVerificationResponse processVerification(Verification verification, String idempotencyKey) {
		
		IdempotencyKeyEntity savedKey = idempotencyKeyRepository.findByIdempotencyKey(idempotencyKey).orElse(null);
		String url;
		
		if (savedKey != null) {
	        if (savedKey.getExpiryDate().isBefore(LocalDateTime.now())) {
	            idempotencyKeyRepository.delete(savedKey);
	        } else {
	        	url = "http://localhost:8082/api/customer/record";
	            return new StartVerificationResponse(url);
	        }
	    }
			VerificationResponse response = verificationPersistence.save(verification);
	    	String idempotencyresponse = "Customer created with ID: " + response.id();
	    	
	    	IdempotencyKeyEntity newKey = new IdempotencyKeyEntity();
	        newKey.setIdempotencyKey(idempotencyKey);
	        newKey.setResponse(idempotencyresponse);
	        newKey.setExpiryDate(LocalDateTime.now().plusHours(24)); // 24-hour expiration
	        idempotencyKeyRepository.save(newKey);
	    
	    url = identificationTypeMapperStrategy.verificationTypeIntegration(verification, response.txnNumber());
		
		return new StartVerificationResponse(url);
	}
}