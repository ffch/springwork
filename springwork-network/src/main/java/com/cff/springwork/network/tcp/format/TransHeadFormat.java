package com.cff.springwork.network.tcp.format;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.cff.springwork.network.tcp.format.TransDataFormat.KeyValue;

public class TransHeadFormat {	
	private List<HeadKeyValue> headParams = new ArrayList<HeadKeyValue>();

	private String headType = "";
	
	private String headDelimiter = "`";
	
	public static String HEADTYPE_FIXED="fixed";
	public static String HEADTYPE_VAR="var";

	public TransHeadFormat() {

	}

	public String getHeadType() {
		return headType;
	}

	public void setHeadType(String headType) {
		this.headType = headType;
	}

	public String getHeadDelimiter() {
		return headDelimiter;
	}

	public void setHeadDelimiter(String headDelimiter) {
		this.headDelimiter = headDelimiter;
	}
	public int getParamSize(){
		return headParams.size();
	}
	
	public HeadKeyValue get(int i){
		return headParams.get(i);
	}

	public void putParams(String varname, int serials, int length, String defaultValue) {
		HeadKeyValue kv = new HeadKeyValue();
		kv.setKey(varname);
		kv.setLength(length);
		kv.setSerials(serials);
		kv.setDefaultValue(defaultValue);
		headParams.add(kv);
		if (serials < headParams.size()) {
			headParams.sort(new Comparator<HeadKeyValue>() {
				@Override
				public int compare(HeadKeyValue s1, HeadKeyValue s2) {
					return Integer.compare(s1.getSerials(), s2.getSerials());
				}
			});
		}

	}
	
	@Override
	public String toString() {
		return "TransHeadFormat [headParams=" + headParams + ", headType=" + headType + ", headDelimiter="
				+ headDelimiter + "]";
	}

	public class HeadKeyValue{
		public String key;
		public int serials;
		public int length;
		public String defaultValue;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public int getSerials() {
			return serials;
		}
		public void setSerials(int serials) {
			this.serials = serials;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public String getDefaultValue() {
			return defaultValue;
		}
		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
		@Override
		public String toString() {
			return "HeadKeyValue [key=" + key + ", serials=" + serials + ", length=" + length + ", defaultValue="
					+ defaultValue + "]";
		}
	}
}
