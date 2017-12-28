package com.cff.springwork.mybatis.mapper;

import java.util.List;

import com.cff.springwork.model.security.AppUser;

public interface AppUserMapper {
	public List<AppUser> getAppUser(String userNo);
	
	public List<AppUser> getAppUserByUserName(String userName);
	
	public void save(AppUser appUser);
	
	public void modify(AppUser appUser);
	
}
