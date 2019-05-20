package com.cff.springwork.activemq.handler.bussiness;

import com.cff.springwork.activemq.model.JmsMessage;

public interface Handler {
	public void hander(JmsMessage msg);
}
