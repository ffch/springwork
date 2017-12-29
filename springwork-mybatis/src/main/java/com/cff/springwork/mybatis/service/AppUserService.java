package com.cff.springwork.mybatis.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cff.springwork.common.constant.Constant;
import com.cff.springwork.model.security.AppUser;
import com.cff.springwork.mybatis.mapper.AppUserMapper;

@Service
@Transactional
public class AppUserService {
	@Autowired
	AppUserMapper appUserMapper;

	@Autowired
	AppSeqService appSeqService;

	public void save(AppUser appUser) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		appUser.setUuid(uuid);
		appUser.setUserNo(appSeqService.nextSeq(Constant.USERSEQ));
		appUser.setUserType(Constant.USERTYPE);
		appUserMapper.save(appUser);
	}

	public AppUser findByName(String userName) {
		List<AppUser> appusers = appUserMapper.getAppUserByUserName(userName);
		if (appusers == null || appusers.size() < 1) {
			return null;
		}
		return appusers.get(0);
	}
	
	public List<AppUser> findByType(String userType) {
		List<AppUser> appusers = appUserMapper.getAppUserByType(userType);
		if (appusers == null || appusers.size() < 1) {
			return null;
		}
		return appusers;
	}

	public boolean modify(AppUser user) {
		try {
			appUserMapper.modify(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
