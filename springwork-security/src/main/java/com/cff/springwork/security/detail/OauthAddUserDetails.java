package com.cff.springwork.security.detail;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;


public class OauthAddUserDetails extends WebAuthenticationDetails{
	 /**
     * 
     */
    private static final long serialVersionUID = 6975601077710753878L;
    private final String token;
    private final String sessionToken;

    public OauthAddUserDetails(HttpServletRequest request) {
        super(request);
        token = request.getParameter("imgtoken");
        sessionToken = (String) request.getSession().getAttribute("imgtoken");
    }

    public String getToken() {
        return token;
    }

    public String getSessionToken() {
		return sessionToken;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; Token: ").append(this.getToken());
        return sb.toString();
    }

}
