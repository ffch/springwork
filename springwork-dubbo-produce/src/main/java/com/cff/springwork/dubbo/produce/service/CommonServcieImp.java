package com.cff.springwork.dubbo.produce.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.cff.springwork.dubbo.inter.CommonService;

public class CommonServcieImp implements CommonService{

	@Override
	public String getLocalIp() throws UnknownHostException {
		String ip = InetAddress.getLocalHost().getHostAddress();
		return ip;
	}
	
}
