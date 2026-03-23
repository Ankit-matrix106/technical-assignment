package com.capco.onboarding.adapter.inbound.model.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.EXISTING_PROPERTY, 
		  property = "identificationType",
		  visible = true
		)

@JsonSubTypes({
		    @JsonSubTypes.Type(value = AutoVerificationRequest.class, name = "AUTOIDNOW")
		})

public sealed interface VerificationRequest permits AutoVerificationRequest{
	String identificationType();
}
