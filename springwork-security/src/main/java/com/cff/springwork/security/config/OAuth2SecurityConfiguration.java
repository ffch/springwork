package com.cff.springwork.security.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.cff.springwork.security.ajax.AjaxAuthFailHandler;
import com.cff.springwork.security.ajax.AjaxAuthSuccessHandler;
import com.cff.springwork.security.ajax.AjaxLogoutSuccessHandler;
import com.cff.springwork.security.ajax.UnauthorizedEntryPoint;
import com.cff.springwork.security.service.OauthAuthenticationProvider;


@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("myClientDetailsService")
	private ClientDetailsService clientDetailsService;

	@Autowired
    private OauthAuthenticationProvider oauthAuthenticationProvider; 
	
	@Autowired
	@Qualifier("oauthAuthenticationDetailsSource")
	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(oauthAuthenticationProvider);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	SimpleUrlAuthenticationFailureHandler hander = new SimpleUrlAuthenticationFailureHandler();
    	hander.setUseForward(true);
    	hander.setDefaultFailureUrl("/authlogin.jsp");

		http
		.exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint())
		.and()
		.csrf().disable()
	  	.authorizeRequests()
	  	.antMatchers("/oauth/token")
	  	.permitAll().and()
	  	.formLogin().loginPage("/authlogin.jsp")
	  	.usernameParameter("userName").passwordParameter("userPwd")
	  	.authenticationDetailsSource(authenticationDetailsSource)
	  	.loginProcessingUrl("/login")
	  	.successHandler(new AjaxAuthSuccessHandler())
        .failureHandler(new AjaxAuthFailHandler())//failureHandler(hander).defaultSuccessUrl("/index_v2.jsp")
//        .loginProcessingUrl("/ajaxlogin")
//        .successHandler(new AjaxAuthSuccessHandler())
//        .failureHandler(new AjaxAuthFailHandler())
	  	.and().logout().logoutUrl("/logout").logoutSuccessHandler(new AjaxLogoutSuccessHandler());

		http.authorizeRequests().antMatchers("/user/**").authenticated();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}
	
	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}
	
}
