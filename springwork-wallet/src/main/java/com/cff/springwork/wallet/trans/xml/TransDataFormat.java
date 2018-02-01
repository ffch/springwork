package com.cff.springwork.wallet.trans.xml;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class TransDataFormat {

	private List<KeyValue> params = new ArrayList<KeyValue>();;

	private String transCode = null;

	private String transName = null;

	private String delimiter = "`";

	public TransDataFormat() {

	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public void putParams(String varname, int serials, String defaultValue) {
		KeyValue kv = new KeyValue();
		kv.setKey(varname);
		kv.setValue(defaultValue);
		kv.setSerials(serials);
		params.add(kv);
		if (serials < params.size()) {
			params.sort(new Comparator<KeyValue>() {
				@Override
				public int compare(KeyValue s1, KeyValue s2) {
					return Integer.compare(s1.getSerials(), s2.getSerials());
				}
			});
		}

	}
	
	public List<KeyValue> getParams(){
		return params;
	}
	
	public int getParamsSize(){
		return params.size();
	}

	public String getParamKey(int pos) {
		return params.get(pos).getKey();
	}

	public String getParamValue(int pos) {
		return params.get(pos).getValue();
	}

	public class KeyValue {
		public String key;
		public String value;
		public int serials;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public int getSerials() {
			return serials;
		}

		public void setSerials(int serials2) {
			this.serials = serials2;
		}

		@Override
		public String toString() {
			return "KeyValue [key=" + key + ", value=" + value + ", serials=" + serials + "]";
		}

	}

	@Override
	public String toString() {
		return "TransDataFormat [params=" + params + ", transCode=" + transCode + ", transName=" + transName
				+ ", delimiter=" + delimiter + "]";
	}
	
	@Test
	public void test(){
		String cha = "12as`asdasd``we``wqe``";
		String[] params = cha.split("`");
		for(int i=0;i<params.length;i++){
			System.out.println(params[i]+"____");
		}
	}

}
