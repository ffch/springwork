package com.cff.springwork.web.endpoint.pub;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cff.springwork.common.constant.Constant;
import com.cff.springwork.model.security.AppUser;
import com.cff.springwork.mybatis.service.AppUserService;

@RestController("publicController")
@RequestMapping("/pub")
public class PublicController {
	@Autowired
	AppUserService appUserService;

	@RequestMapping("/register")
	public String register(@RequestBody AppUser appUser){
		appUserService.save(appUser);
		return Constant.RESPONSE_OK;
	}
}
