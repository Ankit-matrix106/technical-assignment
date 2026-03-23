package com.capco.onboarding.domain.port.inbound;

import com.capco.onboarding.domain.model.WebhookPayload;

public interface WebhookProcessService {

	public WebhookPayload processStatus(WebhookPayload payload);
}
