package com.cff.springwork.common.user;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserInfoManager {
	private static class SingletonHolder {
		private static final UserInfoManager INSTANCE = new UserInfoManager();
	}

	private UserInfoManager() {
	}

	public static final UserInfoManager getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public String getUserName(){
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(userName == null || "".equals(userName) || "anonymousUser".equals(userName)){
			userName = "";
		}
		return userName;
	}
	
}
