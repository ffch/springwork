package com.cff.springwork.web.endpoint.test;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cff.springwork.common.util.DateUtil;
import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.web.client.tcp.TransSocketClient;
import com.cff.springwork.web.entity.WelEntity;


@RestController("testController")
@RequestMapping("/test")
public class TestController {
//	@Autowired
//	ParamInfoService paramInfoService;
//	
//	@Autowired
//	ParamInfoCache paramInfoCache;
	@Autowired
	TransSocketClient transSocketClient;
	@RequestMapping("/welCome")
	public WelEntity welCome(@RequestParam String reqType){
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
//	
//	@RequestMapping("/testparam")
//	public ParamInfo testParam(@RequestParam String reqType){
//		if(reqType ==null || "".equals(reqType))return null;
//		return paramInfoService.getParamInfo(reqType).get(0);
//	}
//	
//	@RequestMapping("/testcache")
//	public List<ParamInfo> testCache(@RequestParam String reqType){
//		if(reqType ==null || "".equals(reqType))return null;
//		return paramInfoCache.getParamInfoCache(reqType);
//	}
//	
//	@RequestMapping("/updatecache")
//	public List<ParamInfo> updateCache(@RequestParam String reqType){
//		if(reqType ==null || "".equals(reqType))return null;
//		return paramInfoCache.updateParamInfoCache(reqType);
//	}
//	
//	@RequestMapping("/testupdatecache")
//	public List<ParamInfo> updateTestCache(@RequestParam String reqType){
//		if(reqType ==null || "".equals(reqType))return null;
//		return paramInfoCache.updateTestParamInfoCache(reqType);
//	}
//	
//	@RequestMapping("/testallcache")
//	public List<ParamInfo> testAllCache(){
//		return paramInfoCache.getAllParamInfoCache();
//	}
	
	@RequestMapping("/imgtoken")
	public String imgtoken(HttpServletRequest request){
		String imgtoken = "1234";
		RandomValueStringGenerator generator = new RandomValueStringGenerator(4);
		imgtoken = generator.generate();
		if(!StringUtils.isEmpty(request.getParameter("imgtoken"))){
			imgtoken = (String) request.getParameter("imgtoken");
		}
		System.out.println(imgtoken);
		request.getSession().setAttribute("imgtoken", imgtoken);
		return imgtoken;
	}
	
	@RequestMapping("/netty")
	public String imgtoken(@RequestParam String value,@RequestParam String type) throws Exception{
		if(type==null)type="1";
		TransactionMapData tm = new TransactionMapData();
		tm.put("heartBit", value);
		tm.setType(1);
		if("1".equals(type)){
			transSocketClient.sendAndWaitResult(tm);
			String requestId = UUID.randomUUID().toString();
			tm.put("reqTransFlow",requestId);
		}else{
			transSocketClient.sendRequest(tm);
		}
		return "0000";
	}
}
