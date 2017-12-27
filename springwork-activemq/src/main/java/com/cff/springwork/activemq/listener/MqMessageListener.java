package com.cff.springwork.activemq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.cff.springwork.activemq.JmsMessage;
import com.cff.springwork.activemq.handler.Handler;

public class MqMessageListener implements MessageListener{
	private Handler handler;
	public void onMessage(Message message) {
		TextMessage textMsg = (TextMessage) message;   
        System.out.println("接收到一个纯文本消息。");   
        try {   
            System.out.println("消息内容是：" + textMsg.getText());   
        } catch (JMSException e) {   
            e.printStackTrace();   
        }   
        JmsMessage<String> jm = new JmsMessage<String>();
        try {
			jm.setBody(textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
        handler.hander(jm);
        
	}
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
