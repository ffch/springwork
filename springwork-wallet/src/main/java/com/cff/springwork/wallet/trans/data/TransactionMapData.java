package com.cff.springwork.wallet.trans.data;

import java.util.Date;
import java.util.HashMap;

public class TransactionMapData {
	private HashMap<String, Object> map = new HashMap();

	private String transCode = null;

	private Date transDate = null;
	
	private int type = 0;

	private String ip= "";
	
	public TransactionMapData() {
		this.transDate = new Date();
	}
	
	public TransactionMapData(int type) {
		this.transDate = new Date();
		this.type = type;
	}

	public Object get(String key) {
		return this.map.get(key);
	}

	public String getTransCode() {
		return this.transCode;
	}

	public void put(String key, Object value) {
		this.map.put(key, value);
	}

	public void setTransCode(String code) {
		this.transCode = code;
	}

	public String toString() {
		return this.map.toString();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public HashMap<String, Object> getMap() {
		return this.map;
	}

	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}

	public void putAll(HashMap<String, Object> map) {
		this.map.putAll(map);
	}

	public Date getTransDate() {
		return this.transDate;
	}
	
	
}
