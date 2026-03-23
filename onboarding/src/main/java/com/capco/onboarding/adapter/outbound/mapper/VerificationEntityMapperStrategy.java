package com.capco.onboarding.adapter.outbound.mapper;

import com.capco.onboarding.adapter.outbound.entity.AutoVerificationEntity;
import com.capco.onboarding.domain.model.Verification;
import com.capco.onboarding.domain.model.VerificationResponse;

public interface VerificationEntityMapperStrategy {
	
	boolean checkIdentType(String identType);
	
	public AutoVerificationEntity toEntity(Verification verification);
	
	public VerificationResponse toDomain(AutoVerificationEntity entity);
}
