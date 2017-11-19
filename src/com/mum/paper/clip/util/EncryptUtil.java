package com.mum.paper.clip.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
	
	public static String getHashCodeFromString(String str) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-512");
	    md.update(str.getBytes());
	    byte byteData[] = md.digest();

	    //convert the byte to hex format method 1
	    StringBuffer hashCodeBuffer = new StringBuffer();
	    for (int i = 0; i < byteData.length; i++) {
	        hashCodeBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    return hashCodeBuffer.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		System.out.println(getHashCodeFromString("admin"));
	}
	

}
