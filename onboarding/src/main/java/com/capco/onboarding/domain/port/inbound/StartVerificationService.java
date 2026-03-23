package com.capco.onboarding.domain.port.inbound;

import com.capco.onboarding.adapter.inbound.model.response.StartVerificationResponse;
import com.capco.onboarding.domain.model.Verification;

public interface StartVerificationService {
	
	StartVerificationResponse processVerification(Verification verificationVO, String idempotencyKey);
}
