package net.whydah.sso.user.helpers;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;


public class Base64Helper {

	public static boolean isBase64(String input) {
		boolean result = false;
		String test;
		try {
			test = convertStringFromBase64(input);
			if (input.equals(convertStringToBase64(test))) {
				result = true;
			} else {
				test = uuidFromBase64(input);
				if(input.equals(uuidToBase64(test))) {
					result = true;
				}
			}
			
		}
		catch (Exception ex) {
			result = false;
		}
		return result;
	}

	public static String convertStringToBase64(String input) {
		return Base64.getEncoder().encodeToString(input.getBytes());
	}

	public static String convertStringFromBase64(String input) {
		return new String(Base64.getDecoder().decode(input));
	}
	
	
	public static String uuidToBase64(String str) {
	    //Base64 base64 = new Base64();
	    UUID uuid = UUID.fromString(str);
	    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	    bb.putLong(uuid.getMostSignificantBits());
	    bb.putLong(uuid.getLeastSignificantBits());
	    return Base64.getUrlEncoder().withoutPadding().encodeToString(bb.array());
	    //return base64.encodeBase64URLSafeString(bb.array());
	}
	public static String uuidFromBase64(String str) {
	    //Base64 base64 = new Base64(); 
	    //byte[] bytes = base64.decodeBase64(str);
		byte[] bytes = Base64.getUrlDecoder().decode(str);
		ByteBuffer bb = ByteBuffer.wrap(bytes);
	    UUID uuid = new UUID(bb.getLong(), bb.getLong());
	    return uuid.toString();
	}

}
