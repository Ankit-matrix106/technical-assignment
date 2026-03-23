package com.capco.onboarding.domain.port.outbound;

import com.capco.onboarding.domain.model.Verification;

public interface IdentityVerificationAPIIntegration {

	boolean checkIdentType(String identType);
	public String authenticate(String country);
	public String startVerificationSession(Verification verification, String txnNumber);
}
