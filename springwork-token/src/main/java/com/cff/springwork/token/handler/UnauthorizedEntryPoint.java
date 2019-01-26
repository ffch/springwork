package com.cff.springwork.token.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.cff.springwork.token.model.ResultCode;
import com.cff.springwork.token.model.ResultModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		ResultModel rm = new ResultModel(ResultCode.CODE_00005, "401未授权!");

		ObjectMapper mapper = new ObjectMapper();
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		mapper.writeValue(response.getWriter(), rm);
	}

}
