package com.doukou.util;

/**
 * 字符串工具类             .<br>
 *
 * @author sunkai
 * @version 2016年12月12日
 */
public class StringUtil {
	
	
	/**
	 * 字符串是否为空           .<br>
	 *
	 * @param str
	 * @return  boolean 
	 */
	public static boolean isEmpty(String str) {
		if (null == str || str.trim().length() == 0)
			return true;
		return false;
	}
	
	/**
	 * 字符串是否不为空           .<br>
	 *
	 * @param str
	 * @return  boolean 
	 */
	public static boolean  isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
