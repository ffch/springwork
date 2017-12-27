package com.cff.springwork.activemq.handler;

import com.cff.springwork.activemq.JmsMessage;

public interface Handler {
	public void hander(JmsMessage msg);
}
