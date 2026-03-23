package com.capco.idnowmock.utils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class IdnowMockUtils {
	
	private static final SecureRandom secureRandom = new SecureRandom();
	public static final Map<String, String> identIdStore = new HashMap<String, String>();
	public static final String SAMPLE_IDENTID="TST-FXWF";
	private static int count;

	public static String generateHexToken() {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
	
	public static String uniqueIdentId(String ident) {
		char ch = ident.charAt(7);
    	char newCh = (char)(ch+(++count));
    	return ident.replace(ch, newCh);
	}
}
