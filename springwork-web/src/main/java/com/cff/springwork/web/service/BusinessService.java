package com.cff.springwork.web.service;

import com.cff.springwork.network.tcp.data.TransactionMapData;

public interface BusinessService {
	public TransactionMapData trans(TransactionMapData tm) throws Exception;
}
