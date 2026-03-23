package com.capco.onboarding.domain.model;

public record Verification(
		String identType,
		String userId,
	    String birthday,
		String birthplace,
		String custom1,
		String custom2,
		String custom3,
		String custom4,
		String email,
		String firstname,
		String gender,
		String lastname,
		String mobilephone,
		String nationality,
		String zipcode,
		String country,
		String city,
		String street,
		String streetNumber
		) {}
