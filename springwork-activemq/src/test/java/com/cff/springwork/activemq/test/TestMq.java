package com.cff.springwork.activemq.test;

import java.net.UnknownHostException;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sf.json.JSONObject;


//@RunWith(SpringJUnit4ClassRunner.class)  
//@ContextConfiguration(locations={"classpath:spring-dubbo.xml"}) 
public class TestMq {

//	@Test
	public void testSave() {
		JSONObject jSONObject = new JSONObject();
    	JSONObject msgBody = new JSONObject();
    	msgBody.put("title", "search");
    	msgBody.put("content", "哈哈哈哈哈哈");

    	jSONObject.put("time", "20171227");
    	jSONObject.put("msgType", "1001");
    	jSONObject.put("orderNo", "00000111111");
    	jSONObject.put("msgBody", msgBody.toString());
    	ActiveMqUtil.sendMessageSingle(jSONObject.toString());
	}
	

}
