package com.cff.springwork.mybatis.test;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cff.springwork.model.security.AppUser;
import com.cff.springwork.mybatis.service.AppSeqService;
import com.cff.springwork.mybatis.service.AppUserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring-test.xml"}) 
public class TestMybatis {
	@Autowired
	AppUserService testService;
	
	@Autowired
	AppSeqService appSeqService;
	
	@Test
	public void testSave(){
		AppUser appUser = new AppUser();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		appUser.setUuid(uuid);
		appUser.setUserName("陈爷");
		appUser.setPassword("123456");
		testService.save(appUser);
	}
	
	@Test
	public void testSeq(){
		//System.out.println(appSeqService.nextSeq("userseq"));
		System.out.println(String.format("%06d", 123456));
	}
}
