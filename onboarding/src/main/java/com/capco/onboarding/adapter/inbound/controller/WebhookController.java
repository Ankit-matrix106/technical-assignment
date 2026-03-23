package com.capco.onboarding.adapter.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.onboarding.adapter.inbound.mapper.WebhookMapper;
import com.capco.onboarding.adapter.inbound.model.request.WebhookPayloadRequest;
import com.capco.onboarding.adapter.inbound.model.response.WebhookPayloadResponse;
import com.capco.onboarding.domain.model.WebhookPayload;
import com.capco.onboarding.domain.port.inbound.WebhookProcessService;

@RestController
@RequestMapping("/api/webhook/")
public class WebhookController {
	
	@Autowired
	private WebhookMapper mapper; 
	
	@Autowired
	private WebhookProcessService processService;

	@PostMapping("/status")
	public ResponseEntity<WebhookPayloadResponse> receiveStatus(@RequestBody WebhookPayloadRequest payload) {
		System.out.println("Getting the webhook request");
		System.out.println("value of payload "+payload.status());
		 WebhookPayload webhookPayload = mapper.toWebhookDomain(payload);
		 WebhookPayloadResponse response = mapper.toClient(processService.processStatus(webhookPayload));
		 return ResponseEntity.ok(response);
	}
}
