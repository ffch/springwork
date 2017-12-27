package com.cff.springwork.activemq.handler;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.cff.springwork.activemq.JmsMessage;

import net.sf.json.JSONObject;

public class JmsMsgHander implements Handler{
	private Handler nextHandler;
	private JmsTemplate jmsTemplate;
	public static String RETCODESUCCESS="200";
	
	@Override
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
		if(nextHandler!=null){
			nextHandler.hander(msg);
		}
	}
	

	public Handler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
}
