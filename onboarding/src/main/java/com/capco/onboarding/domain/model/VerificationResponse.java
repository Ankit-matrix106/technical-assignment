package com.capco.onboarding.domain.model;

public record VerificationResponse(int id, String txnNumber, String identId, String name, String last) {}
