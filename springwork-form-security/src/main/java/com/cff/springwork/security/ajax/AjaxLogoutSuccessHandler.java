package com.cff.springwork.security.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class AjaxLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if(isAjaxRequest(request)){
    		response.setStatus(HttpServletResponse.SC_OK);
    	}else{
    		super.onLogoutSuccess(request, response, authentication);
        }
	}
	
	public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
