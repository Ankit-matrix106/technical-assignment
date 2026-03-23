package com.capco.onboarding.domain.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class AppUtils {

	private static final SecureRandom random = new SecureRandom();
	
	public static String uniqueTxnNumber() {
		byte[] bytes = new byte[8];
		random.nextBytes(bytes);
		return Base64.getUrlEncoder()
				.withoutPadding()
				.encodeToString(bytes)
				.substring(0, 10);
	}
}
