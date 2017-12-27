package com.cff.springwork.activemq.parser;

import com.cff.springwork.activemq.DefaultMqModel;
import com.cff.springwork.activemq.JmsMessage;

import net.sf.json.JSONObject;

public class JsonParse1001 implements JsonParser{

	@Override
	public JmsMessage parse(String msg) {
		System.out.println("消息体为：" + msg );  
		JmsMessage<DefaultMqModel> jm = new JmsMessage<DefaultMqModel>();
		JSONObject json = JSONObject.fromObject(msg);
		String msgType = json.getString("msgType");
		String time = json.getString("time");
		String orderNo = json.getString("orderNo");
		String body = json.getString("msgBody");
		jm.setMsgType(msgType);
		jm.setTime(time);
		jm.setOrderNo(orderNo);
		JSONObject jsonBody = JSONObject.fromObject(body);
		DefaultMqModel dm = new DefaultMqModel();
		dm.setTitle(jsonBody.getString("title"));
		dm.setContent(jsonBody.getString("content"));
		jm.setBody(dm);
		return jm;
	}
	
}
