package com.cff.springwork.activemq.parser;

import com.cff.springwork.activemq.model.JmsMessage;

public interface JsonParser {
	public JmsMessage parse(String msg);
}
