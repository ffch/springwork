package com.cff.springwork.mybatis.mapper;

import java.util.List;

import com.cff.springwork.model.user.UserBind;

public interface UserBindMapper {
	public List<UserBind> getUserBind(String userNo);
			
	public void save(UserBind userBind);
	
	public void modify(UserBind userBind);
	
}
