package com.fanbakery.fancake.common.utils;

import java.security.MessageDigest;

public class AuthUtil {
	
	public static String encrypt_SHA256(String base){
		try{
			 
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            //System.out.println(hexString.toString());
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
	}
	
}//.class
