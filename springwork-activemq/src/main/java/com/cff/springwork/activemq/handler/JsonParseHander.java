package com.cff.springwork.activemq.handler;

import java.util.Map;

import com.cff.springwork.activemq.JmsMessage;
import com.cff.springwork.activemq.parser.JsonParser;

import net.sf.json.JSONObject;

public class JsonParseHander implements Handler{
	private Handler nextHandler;
	private Map<String,JsonParser> parsers;
	
	@Override
	public void hander(JmsMessage msg) {
		String body = (String) msg.getBody();
		JSONObject json = JSONObject.fromObject(body);
		String msgType = json.getString("msgType");
		JsonParser jp = parsers.get(msgType);
		System.out.println("消息类型为：" + msgType + "处理类：" + jp.getClass().getName());   

		JmsMessage jm = jp.parse(body);
		System.out.println("消息为：" + jm.toString() );  
		if(nextHandler!=null){
			System.out.println("nextHandler为：" + nextHandler.getClass().getName());  
			nextHandler.hander(jm);
		}else{
			System.out.println("nextHandler为空");  
		}
	}

	public Map<String, JsonParser> getParsers() {
		return parsers;
	}

	public void setParsers(Map<String, JsonParser> parsers) {
		this.parsers = parsers;
	}

	public Handler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}
}
