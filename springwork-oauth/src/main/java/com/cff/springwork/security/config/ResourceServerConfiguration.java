package com.cff.springwork.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";
	@Autowired
	private TokenStore tokenStore;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore).resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable().requestMatchers().antMatchers("/api/**").and().authorizeRequests()
				.antMatchers("/api/**").access("hasRole('ADMIN')").and().exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}