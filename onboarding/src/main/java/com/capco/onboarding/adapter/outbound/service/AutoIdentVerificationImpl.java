package com.capco.onboarding.adapter.outbound.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capco.onboarding.adapter.inbound.model.request.IdentificationType;
import com.capco.onboarding.domain.exception.SessionCreationFailedException;
import com.capco.onboarding.domain.model.Verification;
import com.capco.onboarding.domain.port.outbound.IdentityVerificationAPIIntegration;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class AutoIdentVerificationImpl implements IdentityVerificationAPIIntegration {

	@Autowired
	private Environment environment;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${idnow.api.key}")
	private String apiKey;
	
	@Value("${auth.uri.path}")
	private String authUriPath;
	
	private String authToken;
	
	@Override
	public String authenticate(String country) {
		
		String url = getApiBaseUrl(country) + authUriPath;
		
		Map<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("apikey", apiKey);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
		
		ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);
		
		if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			authToken = (String) response.getBody().get("authToken");
		} else {
			throw new SessionCreationFailedException("Failed to receive the authToken");
		}
		return authToken;
	}

	@Override
	@CircuitBreaker(name = "startVerificationSession", fallbackMethod = "FallbackForStartVerificationSession")
	public String startVerificationSession(Verification entity, String txnNumber) {
		String identId;
		String authToken = authenticate(entity.country());
		
		System.out.println("value of the AuthToken "+authToken);
		
		//String txnNumber = entity.getTxnNumber();
		String url = getApiBaseUrl(entity.country()) + "/api/v1/identifications/" + txnNumber + "/start";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-API-LOGIN-TOKEN", authToken);
		
		HttpEntity<Verification> requestEntity = new HttpEntity<>(entity, headers);
		
		ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);
		
		if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			identId = (String) response.getBody().get("id");
		} else {
			throw new SessionCreationFailedException("Failed to create IDnow Session");
		}
		
		System.out.println("value of the verificaition url "+getIdentificationUrl(entity.country())+ "/" +identId);
		
		return getIdentificationUrl(entity.country())+"/"+identId;
	}
	
	private String getApiBaseUrl(String country) {
		return switch (country) {
		case "CH" -> environment.getProperty("idnow.gateway.host_CH");
		case "AE" -> environment.getProperty("idnow.gateway.host_AE");
		default -> environment.getProperty("idnow.gateway.host_DE");
		};
	}
	
	private String getIdentificationUrl(String country) {
		return switch (country) {
		case "CH" -> environment.getProperty("idnow.identification.host_CH");
		case "AE" -> environment.getProperty("idnow.identification.host_AE");
		default -> environment.getProperty("idnow.identification.host_DE");
		};
	}

	@Override
	public boolean checkIdentType(String identType) {
		return Objects.equals(identType, IdentificationType.AUTOIDNOW.name());
	}
	
	public String FallbackForStartVerificationSession(Throwable throwable) {
        throw new SessionCreationFailedException("Fallback Response: Service is currently unavailable or circuit is open");
    }
}
