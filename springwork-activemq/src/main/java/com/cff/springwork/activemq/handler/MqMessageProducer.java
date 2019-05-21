package com.cff.springwork.activemq.handler;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.cff.springwork.activemq.handler.bussiness.Handler;
import com.cff.springwork.activemq.model.JmsMessage;

import net.sf.json.JSONObject;

public class MqMessageProducer implements Handler{
	private JmsTemplate jmsTemplate;
	public static String RETCODESUCCESS="200";
	
	public void hander(JmsMessage msg) {
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("msgType", msg.getMsgType());
    	jSONObject.put("orderNo", msg.getOrderNo());
    	jSONObject.put("retCode", RETCODESUCCESS);
    	
		System.out.println("准备发送jms到["+jmsTemplate.getDefaultDestination()+"]...");				

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textmsg = session.createTextMessage();	
				textmsg.setText(jSONObject.toString());
				return textmsg;
			}
		});
		
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
}
