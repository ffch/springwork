package com.cff.springwork.dubbo.consume;

import java.net.UnknownHostException;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cff.springwork.dubbo.inter.CommonService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring-dubbo.xml"}) 
public class TestMybatis {
	@Autowired
	CommonService commonService;
	@Test
	public void testSave() throws UnknownHostException{
		System.out.println(commonService.getLocalIp());
	}
	

}
