package com.cff.springwork.token.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cff.springwork.model.security.AppUser;
import com.cff.springwork.mybatis.service.AppUserService;
import com.cff.springwork.token.model.TokenUserDetails;

@Service("formUserDetailsService")
public class TokenUserDetailsService implements UserDetailsService {
	@Autowired
	private AppUserService appUserService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AppUser user;
		try {
			user = appUserService.findByName(userName);
		} catch (Exception e) {
			throw new UsernameNotFoundException("user select fail");
		}
		if (user == null) {
			throw new UsernameNotFoundException("no user found");
		} else {
			try {
				return new TokenUserDetails(user);
			} catch (Exception e) {
				throw new UsernameNotFoundException("user role select fail");
			}
		}
	}

}
