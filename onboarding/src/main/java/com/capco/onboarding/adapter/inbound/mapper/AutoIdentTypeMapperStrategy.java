package com.capco.onboarding.adapter.inbound.mapper;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.capco.onboarding.adapter.inbound.model.request.AutoVerificationRequest;
import com.capco.onboarding.adapter.inbound.model.request.IdentificationType;
import com.capco.onboarding.adapter.inbound.model.request.VerificationRequest;
import com.capco.onboarding.domain.model.Verification;

@Component
public class AutoIdentTypeMapperStrategy implements IdentTypeMapperStrategy {

	@Override
	public boolean checkIdentType(String identificationType) {
		
		return Objects.equals(identificationType, IdentificationType.AUTOIDNOW.name());
	}

	@Override
	public Verification toDomain(VerificationRequest verificationRequest) {
		
		AutoVerificationRequest request = (AutoVerificationRequest) verificationRequest;
		
		
		 return new Verification(request.identificationType(),request.userId(), request.birthday(),
				  request.birthplace(), request.custom1(), request.custom2(),
				  request.custom3(), request.custom4(), request.email(), request.firstname(),
				  request.gender(), request.lastname(), request.mobilephone(),
				  request.nationality(), request.zipcode(), request.country(), request.city(),
				  request.street(), request.streetNumber());
	}

}
