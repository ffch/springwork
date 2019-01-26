package com.cff.springwork.security.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cff.springwork.security.detail.FormAddUserDetails;
import com.cff.springwork.security.detail.FormUserDetails;

@Component
public class SimpleAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private FormUserDetailsService formUserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 验证码等校验
		FormAddUserDetails details = (FormAddUserDetails) authentication.getDetails();

		System.out.println(details.getToken() + "+++++++++++++++++" + details.getSessionToken());
		if (!details.getIsAjax() && !StringUtils.isEmpty(details.getSessionToken())) {
			if (!details.getToken().equalsIgnoreCase(details.getSessionToken())) {
				throw new BadCredentialsException("验证码错误。");
			}
		}

		// 用户名密码校验
		FormUserDetails formUserDetails = (FormUserDetails) formUserDetailsService
				.loadUserByUsername(authentication.getName());
		System.out.println(authentication.getName() + "+++++++++++++++++" + authentication.getCredentials());
		if (!formUserDetails.getUserName().equals(authentication.getName())
				|| !formUserDetails.getPassword().equals(authentication.getCredentials())) {
			throw new BadCredentialsException("用户名或密码错误。");
		}
		Collection<? extends GrantedAuthority> authorities = formUserDetails.getAuthorities();
		return new UsernamePasswordAuthenticationToken(formUserDetails.getUsername(), formUserDetails.getPassword(),
				authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
