package com.cff.springwork.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.cff.springwork.security.detail.OauthAddUserDetails;

@Component("oauthAuthenticationDetailsSource")
public class OauthAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new OauthAddUserDetails(context);
    }


}
