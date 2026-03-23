package com.capco.idnowmock.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capco.idnowmock.model.WebhookPayload;
import com.capco.idnowmock.utils.IdnowMockUtils;

@RestController
public class IdnowWebhookMockController {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/{identId}")
	public String triggerStatusEvent(@PathVariable("identId") final String identId) {
		
		System.out.println("Calling the status event function for sending status via webhook for id "+identId);
		String txnNumber = IdnowMockUtils.identIdStore.get(identId);
		
		WebhookPayload payload = new WebhookPayload(identId, "Success", txnNumber);
		
		String webhookUrl = "http://localhost:8082/api/webhook/status";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<WebhookPayload> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, httpEntity, String.class);
		
		return "'user.created' webhook sent!";
	}
	
}
