package com.cff.springwork.security.detail;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.cff.springwork.model.security.AppUser;


public class OauthUserDetails extends AppUser implements UserDetails{

	public OauthUserDetails(AppUser appUser) {
		super(appUser);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6272869114201567325L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("USER");
	}

	@Override
	public String getUserType() {
		return super.getUserType();
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
