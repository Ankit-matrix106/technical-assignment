package com.capco.onboarding.adapter.inbound.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutoVerificationRequest(
		@NotBlank String identificationType,
	    String userId,
		@NotBlank String birthday,
		String birthplace,
		String custom1,
		String custom2,
		String custom3,
		String custom4,
		@Email@NotBlank String email,
		@NotBlank String firstname,
		@NotBlank String gender,
		@NotBlank String lastname,
		@NotBlank String mobilephone,
		@NotBlank String nationality,
		String zipcode,
		String country,
		String city,
		String street,
		String streetNumber
		) implements VerificationRequest {}
