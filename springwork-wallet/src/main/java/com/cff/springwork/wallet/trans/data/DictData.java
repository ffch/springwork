package com.cff.springwork.wallet.trans.data;

import java.util.Map;
import com.cff.springwork.wallet.trans.xml.TransDataFormat;

public class DictData {
	
	public static Map<String, TransDataFormat> transFormatDataReq = null;
	public static Map<String, TransDataFormat> transFormatDataRes = null;
	
	public static TransDataFormat getReqTransDataFormat(String transCode){
		return transFormatDataReq.get(transCode);
	}
	
	public static TransDataFormat getResTransDataFormat(String transCode){
		return transFormatDataRes.get(transCode);
	}
}
