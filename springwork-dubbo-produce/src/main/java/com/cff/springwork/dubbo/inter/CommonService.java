package com.cff.springwork.dubbo.inter;

import java.net.UnknownHostException;

public interface CommonService {
	public String getLocalIp() throws UnknownHostException;
}
