package com.capco.onboarding.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capco.onboarding.adapter.outbound.entity.VerificationStatusEntity;
import com.capco.onboarding.adapter.outbound.mapper.WebhookEntityMapper;
import com.capco.onboarding.adapter.outbound.repository.SpringDataWebhookRepository;
import com.capco.onboarding.domain.model.WebhookPayload;
import com.capco.onboarding.domain.port.inbound.WebhookProcessService;

@Service
public class WebhookStatusProcessServiceImpl implements WebhookProcessService {

	@Autowired
	private SpringDataWebhookRepository repository;
	
	@Autowired
	private WebhookEntityMapper entityMapper;
	
	@Override
	public WebhookPayload processStatus(WebhookPayload payload) {
		VerificationStatusEntity statusEntity = entityMapper.toEntity(payload);
		WebhookPayload payloadResponse = entityMapper.toWebhookResponse(repository.save(statusEntity));
		return payloadResponse;
	}

}
