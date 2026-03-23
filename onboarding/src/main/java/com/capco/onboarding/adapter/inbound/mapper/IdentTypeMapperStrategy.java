package com.capco.onboarding.adapter.inbound.mapper;

import com.capco.onboarding.adapter.inbound.model.request.VerificationRequest;
import com.capco.onboarding.domain.model.Verification;

public interface IdentTypeMapperStrategy {
	
	boolean checkIdentType(String identificationType);
	
	public Verification toDomain(VerificationRequest request);
}
