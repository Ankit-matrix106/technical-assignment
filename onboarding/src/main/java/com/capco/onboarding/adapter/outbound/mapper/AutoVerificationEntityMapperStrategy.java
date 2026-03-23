package com.capco.onboarding.adapter.outbound.mapper;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.capco.onboarding.adapter.inbound.model.request.IdentificationType;
import com.capco.onboarding.adapter.outbound.entity.AutoVerificationEntity;
import com.capco.onboarding.domain.model.Verification;
import com.capco.onboarding.domain.model.VerificationResponse;
import com.capco.onboarding.domain.utils.AppUtils;

@Component
public class AutoVerificationEntityMapperStrategy implements VerificationEntityMapperStrategy {

	@Override
	public boolean checkIdentType(String identType) {
		return Objects.equals(identType, IdentificationType.AUTOIDNOW.name()); 
	}

	@Override
	public AutoVerificationEntity toEntity(Verification verification) {
		
		return AutoVerificationEntity.builder()
				.identType(verification.identType())
				.birthday(verification.birthday())
				.birthplace(verification.birthplace())
				.custom1(verification.custom1())
				.custom2(verification.custom2())
				.custom3(verification.custom3())
				.custom4(verification.custom4())
				.email(verification.email())
				.firstname(verification.firstname())
				.gender(verification.gender())
				.lastname(verification.lastname())
				.mobilephone(verification.mobilephone())
				.nationality(verification.nationality())
				.zipcode(verification.zipcode())
				.country(verification.country())
				.city(verification.city())
				.street(verification.street())
				.streetNumber(verification.streetNumber())
				.txnNumber(AppUtils.uniqueTxnNumber())
				.build();
	}

	@Override
	public VerificationResponse toDomain(AutoVerificationEntity entity) {
		return new VerificationResponse(entity.getCustomerId(),
				entity.getTxnNumber(), entity.getIdentId(), entity.getFirstname(),
				entity.getLastname());
	}

}
