package com.cff.springwork.activemq.handler;

import java.util.Map;

import com.cff.springwork.activemq.JmsMessage;

public class BussiNessHander implements Handler{
	private Handler nextHandler;
	
	@Override
	public void hander(JmsMessage msg) {
		System.out.println(msg.getBody().toString());
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
}
