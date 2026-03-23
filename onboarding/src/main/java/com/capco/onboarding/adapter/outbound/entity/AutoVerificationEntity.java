package com.capco.onboarding.adapter.outbound.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "auto_verifications")
public class AutoVerificationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String identType;
	private String birthday;
	private String birthplace;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String email;
	private String firstname;
	private String gender;
	private String lastname;
	private String mobilephone;
	private String nationality;
	private String zipcode;
	private String country;
	private String city;
	private String street;
	private String streetNumber;
	private String identId;
	private String txnNumber;
}
