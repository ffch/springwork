package com.cff.springwork.web.endpoint.user;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cff.springwork.common.util.DateUtil;
import com.cff.springwork.model.security.AppUser;
import com.cff.springwork.mybatis.service.AppUserService;
import com.cff.springwork.web.entity.WelEntity;

import net.sf.json.JSONObject;

@RestController("userControllor")
@RequestMapping("/user")
public class UserControllor {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping("/getUser")
	public AppUser getUserName(){
		AppUser user = new AppUser();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!StringUtils.isEmpty(userName)){
			AppUser userTmp = appUserService.findByName(userName);
			user.setUserType(userTmp.getUserType());
		}
		user.setUserName(userName);
		return user;
	}
	
	@RequestMapping(value="/modifypasswd")
	public String modifyPasswd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordnew = request.getParameter("passwordnew");
		String passwordconfirm = request.getParameter("passwordconfirm");
		logger.info(passwordnew+"----" + passwordconfirm);
		if(!passwordnew.equals(passwordconfirm)){
			logger.info("两次密码不一致");
			return "/jsp/user/userManager.jsp?type=101";
		}
		if(StringUtils.isEmpty(loginId)){
			logger.info("无登陆id");
			return "/index_2.jsp";
		}
		
		AppUser user = new AppUser();
		user.setUserName(loginId);
		user.setPassword(password);
		if(appUserService.findByName(loginId)!=null){
			user.setPassword(passwordnew);
			logger.info("密码正确");
			if(appUserService.modify(user)){
				return "/jsp_v2/user/userManager.jsp?type=200";
			}
			return "/jsp_v2/user/userManager.jsp?type=102";
		}
		
		return "/jsp_v2/user/userManager.jsp?type=100";
	}
	
	
}
