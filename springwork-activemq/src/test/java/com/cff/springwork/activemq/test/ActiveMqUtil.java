package com.cff.springwork.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class ActiveMqUtil {
	public static MessageConsumer messageConsumer = null;
	public static MessageProducer messageProducer = null;
	public static Session messageProducerSession = null;
	
	public static String getMessage(){
		String result = "heih ";
        try{
        	result = ((TextMessage) messageConsumer.receive(2000)).getText();
        }catch (Exception e) {
			result = "暂无消息";
		}
        System.out.println(result);

		return result;	
	}
	
	public static String getMessageInteval(int inteval){
		String result = "";	
        try{
        	result = ((TextMessage) messageConsumer.receive(inteval*1000)).getText();
        }catch (Exception e) {
			result = "暂无消息";
		}
        System.out.println(result);

		return result;	
	}
	
	public static synchronized MessageConsumer getMessageConsumer(){
		if (messageConsumer !=null) return messageConsumer;
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
	            ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		Connection connection;
		try {
			connection = activeMQConnectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.FALSE,
	                Session.AUTO_ACKNOWLEDGE);
	                                                                                                                                                                                                                                                            
	        Destination destination = new ActiveMQQueue("destQueue");
	        messageConsumer = session.createConsumer(destination);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return messageConsumer;
	}
	
	public static synchronized boolean sendMessage(String msg){
		if(messageProducer != null && messageProducerSession != null){	
	         try {
	        	 TextMessage message = messageProducerSession.createTextMessage(msg);
				messageProducer.send(message);
				messageProducerSession.commit();
				return true;
			} catch (JMSException e) {
				
				e.printStackTrace();
			} 
	         return false;
	         
		}
		ConnectionFactory connectionFactory; // Connection ：JMS 客户端到JMS  
	     // Provider 的连接  
	     Connection connection = null; // Session： 一个发送或接收消息的线程  
	     Session session; // Destination ：消息的目的地;消息发送给谁.  
	     Destination destination; // MessageProducer：消息发送者  
	     MessageProducer producer; // TextMessage message;  
	     // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar  
	     connectionFactory = new ActiveMQConnectionFactory(  
	             ActiveMQConnection.DEFAULT_USER,  
	             ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
	     try { // 构造从工厂得到连接对象  
	         connection = connectionFactory.createConnection();  
	         // 启动  
	         connection.start();  
	         // 获取操作连接  
	         session = connection.createSession(Boolean.TRUE,  
	                 Session.AUTO_ACKNOWLEDGE);  
	         // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
	         destination = session.createQueue("listenQueue");  
	         // 得到消息生成者【发送者】  
	         producer = session.createProducer(destination);  
	         // 设置不持久化，此处学习，实际根据项目决定  
	         producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
	         messageProducer = producer;
	         messageProducerSession = session;
	         TextMessage message = messageProducerSession.createTextMessage(msg);
	         messageProducer.send(message); 
	         messageProducerSession.commit();
	         return true;
	     }
         catch(JMSException e){
        	 e.printStackTrace();
         }
	     return false;
	}
	
	public static boolean sendMessageSingle(String msg){
		System.out.println("发送jms消息："+msg);
		ConnectionFactory connectionFactory; // Connection ：JMS 客户端到JMS  
	     // Provider 的连接  
	     Connection connection = null; // Session： 一个发送或接收消息的线程  
	     Session session = null; // Destination ：消息的目的地;消息发送给谁.  
	     Destination destination; // MessageProducer：消息发送者  
	     MessageProducer producer = null; // TextMessage message;  
	     // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar  
	     connectionFactory = new ActiveMQConnectionFactory(  
	             ActiveMQConnection.DEFAULT_USER,  
	             ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
	     try { // 构造从工厂得到连接对象  
	         connection = connectionFactory.createConnection();  
	         // 启动  
	         connection.start();  
	         // 获取操作连接  
	         session = connection.createSession(Boolean.TRUE,  
	                 Session.AUTO_ACKNOWLEDGE);  
	         // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
	         destination = session.createQueue("listenQueue");  
	         // 得到消息生成者【发送者】  
	         producer = session.createProducer(destination);  
	         // 设置不持久化，此处学习，实际根据项目决定  
	         producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
	         TextMessage message = session.createTextMessage(msg);
	         producer.send(message); 
	         session.commit();
	         session.close();
	         producer.close();
	         return true;
	     }
         catch(JMSException e){
        	 e.printStackTrace();
        	 try {
        		 session.close();
				 producer.close();
	 	         connection.close();
			} catch (JMSException e1) {
				e1.printStackTrace();
			}  
         }
	     return false;
	}
}
