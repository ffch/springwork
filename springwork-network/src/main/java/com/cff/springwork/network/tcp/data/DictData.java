package com.cff.springwork.network.tcp.data;

import java.util.HashMap;
import java.util.Map;

import com.cff.springwork.network.tcp.format.TransDataFormat;

public class DictData {
	
	public static Map<String, TransDataFormat> transFormatDataReq = new HashMap<String, TransDataFormat>();
	public static Map<String, TransDataFormat> transFormatDataRes = new HashMap<String, TransDataFormat>();
	
	public static TransDataFormat getReqTransDataFormat(String transCode){
		return transFormatDataReq.get(transCode);
	}
	
	public static TransDataFormat getResTransDataFormat(String transCode){
		return transFormatDataRes.get(transCode);
	}
}
