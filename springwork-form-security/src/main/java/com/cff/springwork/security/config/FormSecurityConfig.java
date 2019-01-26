package com.cff.springwork.security.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.cff.springwork.security.ajax.AjaxAuthFailHandler;
import com.cff.springwork.security.ajax.AjaxAuthSuccessHandler;
import com.cff.springwork.security.ajax.AjaxLogoutSuccessHandler;
import com.cff.springwork.security.ajax.UnauthorizedEntryPoint;
import com.cff.springwork.security.service.SimpleAuthenticationProvider;

@EnableWebSecurity
public class FormSecurityConfig {

	@Configuration                                                   
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
		private SimpleAuthenticationProvider simpleAuthenticationProvider;
		
		@Autowired
		@Qualifier("formAuthenticationDetailsSource")
		private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
		
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(simpleAuthenticationProvider);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().exceptionHandling()
					.authenticationEntryPoint(new UnauthorizedEntryPoint())
					.and().headers()
					.frameOptions().disable().and().authorizeRequests()
					.antMatchers("/favicon.ico").permitAll()
					.antMatchers("/login").permitAll()
					.antMatchers("/login.html").permitAll()
					.antMatchers("/css/**").permitAll()
					.antMatchers("/pub/**").permitAll()
					.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
					.anyRequest().authenticated()
					.and()
					.formLogin()
				  	.usernameParameter("userName").passwordParameter("userPwd")
				  	.authenticationDetailsSource(authenticationDetailsSource)
					.loginProcessingUrl("/login")
					.successHandler(new AjaxAuthSuccessHandler())
					.failureHandler(new AjaxAuthFailHandler())
					.and()
					.logout()
					.logoutUrl("/logout").logoutSuccessHandler(new AjaxLogoutSuccessHandler());
		}
	}
}
