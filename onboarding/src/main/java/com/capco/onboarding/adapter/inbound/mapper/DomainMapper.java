package com.capco.onboarding.adapter.inbound.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.capco.onboarding.adapter.inbound.model.request.VerificationRequest;
import com.capco.onboarding.domain.exception.InvalidIdentTypeException;
import com.capco.onboarding.domain.model.Verification;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DomainMapper {
	
	private final List<IdentTypeMapperStrategy> identTypeMapperStrategies;

	public Verification toDomain(VerificationRequest request) {
		
		return identTypeMapperStrategies.stream()
			.filter(strategy -> strategy.checkIdentType(request.identificationType()))
			.findFirst()
			.orElseThrow(() -> new InvalidIdentTypeException("Invaild Indentification Type."))
			.toDomain(request);	 
	}
}
