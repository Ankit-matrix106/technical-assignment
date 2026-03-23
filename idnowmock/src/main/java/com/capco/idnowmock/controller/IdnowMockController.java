package com.capco.idnowmock.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.idnowmock.model.AuthApiKey;
import com.capco.idnowmock.model.CustomerDetails;
import com.capco.idnowmock.utils.IdnowMockUtils;

@RestController
@RequestMapping("api")
public class IdnowMockController {
	
	@PostMapping("v1/login")
	public ResponseEntity<Map<String, String>> authToken(@RequestBody AuthApiKey apikey,
			@RequestHeader("content-type") String contentType) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("authToken", IdnowMockUtils.generateHexToken());
		
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
	@PostMapping("v1/identifications/{transactionNumber}/start")
	public ResponseEntity<Map<String, String>> createIdentification(@RequestBody CustomerDetails customer,
				@PathVariable("transactionNumber") String txnNumber,
				@RequestHeader("X-API-LOGIN-TOKEN") String loginToken,
				@RequestHeader("content-type") String contentType) {
		
		String identId = IdnowMockUtils.uniqueIdentId(IdnowMockUtils.SAMPLE_IDENTID);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", identId);
		
		IdnowMockUtils.identIdStore.put(identId, txnNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
	@GetMapping("v1/welcome")
	public String checkApp() {
		return "Welcome to idnow mock service";
	}

}
