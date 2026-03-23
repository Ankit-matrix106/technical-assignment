package com.capco.onboarding.adapter.outbound.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.capco.onboarding.domain.exception.InvalidIdentTypeException;
import com.capco.onboarding.domain.model.Verification;
import com.capco.onboarding.domain.port.outbound.IdentityVerificationAPIIntegration;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class IdentificationTypeMapperStrategy {
	
	
	private final List<IdentityVerificationAPIIntegration> verificationAPIIntegrations;
	
	public String verificationTypeIntegration(Verification verification, String txnNumber) {
		return verificationAPIIntegrations.stream()
					.filter(integration -> integration.checkIdentType(verification.identType()))
					.findFirst()
					.orElseThrow(() -> new InvalidIdentTypeException("Invalid Identfication type"))
					.startVerificationSession(verification, txnNumber);
	}
}
