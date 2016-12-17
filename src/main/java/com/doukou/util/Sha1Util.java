package com.doukou.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Sha1Util {
	
	private static final String token = "doukou";
	
	/**
	 * 获取加密签名       .<br>
	 *
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return  String 
	 */
	public static String getSha1(String timestamp, String nonce) {
		String[] array = new String[]{token,timestamp,nonce};
		Arrays.sort(array); 
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			str.append(array[i]);
		}
		try {  
			MessageDigest digest = MessageDigest.getInstance("SHA-1");  
			digest.update(str.toString().getBytes());  
			byte messageDigest[] = digest.digest();  
			StringBuffer hexString = new StringBuffer();  
			// 字节数组转换为 十六进制 数  
			for (int i = 0; i < messageDigest.length; i++) {  
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
				if (shaHex.length() < 2) {  
					hexString.append(0);  
				}  
				hexString.append(shaHex);  
			}  
			return hexString.toString();  
			
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		}  
		return ""; 
	}
}
