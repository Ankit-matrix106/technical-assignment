package com.capco.onboarding.adapter.outbound.mapper;

import org.springframework.stereotype.Component;

import com.capco.onboarding.adapter.outbound.entity.VerificationStatusEntity;
import com.capco.onboarding.domain.model.WebhookPayload;

@Component
public class WebhookEntityMapper {

	public VerificationStatusEntity toEntity(WebhookPayload payload) {
		return VerificationStatusEntity.builder()
									   .identId(payload.identId())
									   .status(payload.status())
									   .build();
	}
	
	public WebhookPayload toWebhookResponse(VerificationStatusEntity entity) {
		return new WebhookPayload(entity.getIdentId(), entity.getStatus());
	}
}
