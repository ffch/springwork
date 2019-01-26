package com.cff.springwork.token.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.alibaba.druid.util.StringUtils;
import com.cff.springwork.token.manager.TokenManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
	private TokenManager tokenManager;

	public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager) {
		super(authManager);
		this.tokenManager = tokenManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader("token");

		if (header == null) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		if (authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		// token置于header里
		String token = request.getHeader("token");
		if (token != null && !"".equals(token.trim())) {
			// parse the token.
			String userName = tokenManager.getUserFromToken(token);

			if (!StringUtils.isEmpty(userName)) {
				return new UsernamePasswordAuthenticationToken(userName, token, new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}
