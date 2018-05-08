package com.cff.springwork.web.endpoint.test;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/usertest")
public class TestLoginController {
	
	@RequestMapping("/test")
	public String welCome(@RequestParam String reqType){
		System.out.println(reqType);
		return "/index"+reqType+".html";
	}
	
	@RequestMapping("/test1")
	public String welCome1(@RequestParam String reqType){
		System.out.println(reqType);
		return "/index"+reqType+".jsp";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam Map map){
		System.out.println(map.values());
		return "/auth.jsp?clientId="+map.get("clientId")+"&&clientPwd"
				+map.get("clientPwd")+"&&userName="+map.get("userName")
				+"&&userPwd="+map.get("userPwd");
	}
	
	
}
