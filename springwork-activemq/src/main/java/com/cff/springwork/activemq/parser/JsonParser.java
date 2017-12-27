package com.cff.springwork.activemq.parser;

import com.cff.springwork.activemq.JmsMessage;

public interface JsonParser {
	public JmsMessage parse(String msg);
}
