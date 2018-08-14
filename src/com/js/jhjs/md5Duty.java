package com.js.jhjs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;



public class md5Duty {
	MessageDigest md5;
	
	public String toMd5(String str){		
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BASE64Encoder base64en = new BASE64Encoder();
		String encoderString = base64en.encode(md5.digest(str.getBytes()));
		return encoderString;
		
	}
}
