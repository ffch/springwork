package com.cff.springwork.network.tcp.format;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cff.springwork.network.common.StringUtil;


public class TransDataFormat {

	private List<KeyValue> params = new ArrayList<KeyValue>();
	
	private Map<String,List<KeyValue>> paramsMap = new HashMap<String,List<KeyValue>>();

	private String transCode = null;

	private String transName = null;

	private String delimiter = "`";
	
	private String listDelimiter = "#";
	
	private String paramDelimiter = ",";
	
	private TransHeadFormat transHeadFormat = null;

	public TransDataFormat() {

	}
	
	public TransHeadFormat getTransHeadFormat() {
		return transHeadFormat;
	}

	public void setTransHeadFormat(TransHeadFormat transHeadFormat) {
		this.transHeadFormat = transHeadFormat;
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

	public String getListDelimiter() {
		return listDelimiter;
	}

	public void setListDelimiter(String listDelimiter) {
		this.listDelimiter = listDelimiter;
	}

	public String getParamDelimiter() {
		return paramDelimiter;
	}

	public void setParamDelimiter(String paramDelimiter) {
		this.paramDelimiter = paramDelimiter;
	}

	public void putParams(String varname, int serials, String defaultValue) {
		KeyValue kv = new KeyValue();
		kv.setKey(varname);
		kv.setValue(defaultValue);
		kv.setSerials(serials);
		kv.setHasChild(false);
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
	
	public void putParamsMap(String paras, String varname, int serials, String defaultValue) {
		KeyValue kv = new KeyValue();
		kv.setKey(varname);
		kv.setValue(defaultValue);
		kv.setSerials(serials);
		kv.setHasChild(false);
		
		List<KeyValue> lkv = null;
		if(paramsMap.get(paras) != null){
			lkv = paramsMap.get(paras);
			lkv.add(kv);
			if (serials < lkv.size()) {
				lkv.sort(new Comparator<KeyValue>() {
					@Override
					public int compare(KeyValue s1, KeyValue s2) {
						return Integer.compare(s1.getSerials(), s2.getSerials());
					}
				});
			}
		}else{
			lkv = new ArrayList<KeyValue>();
			lkv.add(kv);
			paramsMap.put(paras, lkv);
			if (serials < lkv.size()) {
				lkv.sort(new Comparator<KeyValue>() {
					@Override
					public int compare(KeyValue s1, KeyValue s2) {
						return Integer.compare(s1.getSerials(), s2.getSerials());
					}
				});
			}
		}
	}
	
	public void putParams(String varname, int serials, String defaultValue,Boolean hasChild) {
		KeyValue kv = new KeyValue();
		kv.setKey(varname);
		kv.setValue(defaultValue);
		kv.setSerials(serials);
		kv.setHasChild(hasChild);
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
	
	public List<KeyValue> getParamsMap(String key){
		return paramsMap.get(key);
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
		public Boolean hasChild = false;

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

		public Boolean getHasChild() {
			return hasChild;
		}

		public void setHasChild(Boolean hasChild) {
			this.hasChild = hasChild;
		}

		@Override
		public String toString() {
			return "KeyValue [key=" + key + ", value=" + value + ", serials=" + serials + ", hasChild=" + hasChild
					+ "]";
		}

	}
	
	@Override
	public String toString() {
		return "TransDataFormat [params=" + params + ", paramsMap=" + paramsMap + ", transCode=" + transCode
				+ ", transName=" + transName + ", delimiter=" + delimiter + ", listDelimiter=" + listDelimiter
				+ ", paramDelimiter=" + paramDelimiter + ", transHeadFormat=" + transHeadFormat + "]";
	}

	public void test(){
		String cha = "12as'asdasd''we'wqe''";
		String[] params = cha.split("'");
		for(int i=0;i<params.length;i++){
			System.out.println(params[i]+"____");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("sasd");
		sb.append("asdasd");
		add(sb);
		System.out.println(sb);
		
		System.out.println(StringUtil.format("asdasd", 10) + "++++");
	}
	
	public void add(StringBuilder sb){
		sb.append(1213);
	}

}
