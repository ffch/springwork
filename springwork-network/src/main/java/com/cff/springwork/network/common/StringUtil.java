package com.cff.springwork.network.common;

public class StringUtil {
	public static Boolean isEmpty(String data){
		if(data == null || "".equals(data.trim()))return true;
		return false;
	}
	
	public static Boolean isEmpty(Object data){
		if(data == null || "".equals(data.toString().trim()))return true;
		return false;
	}
	
	public static Boolean isNotEmpty(String data){
		return !isEmpty(data);
	}
	
	public static Boolean isNotEmpty(Object data){
		return !isEmpty(data);
	}

	public static String format(String value, int length) {
		if(value.length() > length){
			value = value.substring(0, length);
		}
		return String.format("%-"+length+"s", value);
	}
}
