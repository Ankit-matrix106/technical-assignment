package com.capco.onboarding.domain.port.outbound;

import java.util.Optional;

import com.capco.onboarding.adapter.outbound.entity.AutoVerificationEntity;
import com.capco.onboarding.domain.model.Verification;
import com.capco.onboarding.domain.model.VerificationResponse;

public interface VerificationPersistence {
	
	VerificationResponse save(Verification verification);
	Optional<VerificationResponse> findById(int id);
	Optional<VerificationResponse> findByTxnNumber(String txnNumber);
	Optional<VerificationResponse> findByProviderIdentId(String provideIdentId);

}
