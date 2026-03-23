package com.capco.onboarding.adapter.outbound.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.capco.onboarding.adapter.outbound.entity.AutoVerificationEntity;
import com.capco.onboarding.domain.exception.InvalidIdentTypeException;
import com.capco.onboarding.domain.model.Verification;
import com.capco.onboarding.domain.model.VerificationResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class VerificationEntityMapper {
	
	private final List<VerificationEntityMapperStrategy> verificationEntityMapperStrategies;
	
	public AutoVerificationEntity toEntity(Verification verification) {
		return verificationEntityMapperStrategies.stream()
				.filter(strategy -> strategy.checkIdentType(verification.identType()))
				.findFirst()
				.orElseThrow(() -> new InvalidIdentTypeException("Invalid identification type: to entity"))
				.toEntity(verification);
	}
	
	public VerificationResponse toDomain(AutoVerificationEntity entity) {
		return verificationEntityMapperStrategies.stream()
				.filter(strategy -> strategy.checkIdentType(entity.getIdentType()))
				.findFirst()
				.orElseThrow(() -> new InvalidIdentTypeException("Invalid identification type:to domain"))
				.toDomain(entity);
	}
	
}