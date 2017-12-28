package com.cff.springwork.web.endpoint.pub;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cff.springwork.common.constant.Constant;
import com.cff.springwork.model.security.AppUser;
import com.cff.springwork.mybatis.service.AppUserService;

import net.sf.json.JSONObject;

@RestController("publicController")
@RequestMapping("/pub")
public class PublicController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	AppUserService appUserService;

	@RequestMapping("/register")
	public String register(@RequestBody AppUser appUser){
		AppUser appUserTmp = appUserService.findByName(appUser.getUserName());
		if(appUserTmp == null){
			appUserService.save(appUser);
		}else{
			return Constant.RESPONSE_EXIST;
		}
		
		return Constant.RESPONSE_OK;
	}
	
	@RequestMapping(value="/check/loginId")
	public void checkLoginId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String loginId = request.getParameter("loginId");
		
		AppUser appUser = appUserService.findByName(loginId);
		boolean result = true;
		if(appUser == null){
			result = false;
		}
		logger.info("结果{}"+result);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("status","200");
		if(result){
			map.put("status","100");
		}
		JSONObject json = JSONObject.fromObject(map);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/getUser")
	public String getUserName(HttpServletRequest request){
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(userName == null || "".equals(userName) || "anonymousUser".equals(userName)){
			userName = "";
		}else request.getSession(false).setAttribute("userName", userName);
		return userName;
	}
}
