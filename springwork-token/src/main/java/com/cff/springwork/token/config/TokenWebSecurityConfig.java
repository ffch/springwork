package com.cff.springwork.token.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.cff.springwork.token.filter.TokenAuthenticationFilter;
import com.cff.springwork.token.filter.TokenLoginFilter;
import com.cff.springwork.token.handler.DefaultPasswordEncoder;
import com.cff.springwork.token.handler.TokenLogoutHandler;
import com.cff.springwork.token.handler.UnauthorizedEntryPoint;
import com.cff.springwork.token.manager.TokenManager;

@Configuration
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	private TokenManager tokenManager;
	private DefaultPasswordEncoder defaultPasswordEncoder;

	@Autowired
	public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
			TokenManager tokenManager) {
		this.userDetailsService = userDetailsService;
		this.defaultPasswordEncoder = defaultPasswordEncoder;
		this.tokenManager = tokenManager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint()).and().csrf().disable()
				.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/public/**").permitAll()
				.anyRequest().authenticated().and().logout().logoutUrl("/logout")
				.addLogoutHandler(new TokenLogoutHandler(tokenManager)).and()
				.addFilter(new TokenLoginFilter(authenticationManager(), tokenManager))
				.addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager)).httpBasic();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
	}
}
