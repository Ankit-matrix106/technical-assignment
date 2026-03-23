package com.capco.onboarding.adapter.inbound.mapper;

import org.springframework.stereotype.Component;

import com.capco.onboarding.adapter.inbound.model.request.WebhookPayloadRequest;
import com.capco.onboarding.adapter.inbound.model.response.WebhookPayloadResponse;
import com.capco.onboarding.domain.model.WebhookPayload;

@Component
public class WebhookMapper {

	public WebhookPayload toWebhookDomain(WebhookPayloadRequest payloadRequest) {
		return new WebhookPayload(payloadRequest.identId(), payloadRequest.status());
	}
	
	public WebhookPayloadResponse toClient(WebhookPayload payload) {
		
		return new WebhookPayloadResponse(payload.status());
	}
}
