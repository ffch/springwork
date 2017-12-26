package com.cff.springwork.security.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

public class InMemoryAuthorizationCodeServices extends RandomValueAuthorizationCodeServices{
	protected final ConcurrentHashMap<String, OAuth2Authentication> authorizationCodeStore = new ConcurrentHashMap<String, OAuth2Authentication>();
	private RandomValueStringGenerator generator = new RandomValueStringGenerator(16);
	@Override
	protected void store(String code, OAuth2Authentication authentication) {
		this.authorizationCodeStore.put(code, authentication);
	}

	@Override
	public OAuth2Authentication remove(String code) {
		OAuth2Authentication auth = this.authorizationCodeStore.remove(code);
		return auth;
	}
	
	@Override
	public String createAuthorizationCode(OAuth2Authentication authentication) {
		String code = generator.generate();
		store(code, authentication);
		return code;
	}
}
