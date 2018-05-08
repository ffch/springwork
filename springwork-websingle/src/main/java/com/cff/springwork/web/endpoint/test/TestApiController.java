package com.cff.springwork.web.endpoint.test;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cff.springwork.common.util.DateUtil;
import com.cff.springwork.web.entity.WelEntity;

@RestController("testApiController")
@RequestMapping("/api")
public class TestApiController {
	
	
	@RequestMapping("/test")
	public WelEntity test(@RequestParam String reqType){
		String uuid = UUID.randomUUID().toString();
		String welMsg = "welcome 程序猿";
		if(reqType != null && "1000".equals(reqType)){
			welMsg = "welcome 程序媛";
		}
		String dateTime = DateUtil.format(new Date(), DateUtil.SimpleDatePattern);
		WelEntity welEntity = new WelEntity();
		welEntity.setUuid(uuid);
		welEntity.setDateTime(dateTime);
		welEntity.setWelMsg(welMsg);
		return welEntity;
	}
	
	@RequestMapping("/map")
	public WelEntity testMap(@RequestParam Map map){
		String uuid = UUID.randomUUID().toString();
		System.out.println("map:"+map.values());
		String reqMsg = (String) map.get("reqMsg");
		String welMsg = "welcome 程序猿" + "----" + reqMsg;
		String reqType = (String) map.get("reqType");
		if(reqType != null && "1000".equals(reqType)){
			welMsg = "welcome 程序媛";
		}
		String dateTime = DateUtil.format(new Date(), DateUtil.SimpleDatePattern);
		WelEntity welEntity = new WelEntity();
		welEntity.setUuid(uuid);
		welEntity.setDateTime(dateTime);
		welEntity.setWelMsg(welMsg);
		return welEntity;
	}
	
	@RequestMapping("/ret")
	public String testRet(){
		String ret = "this is api";
		return ret;
	}
}
