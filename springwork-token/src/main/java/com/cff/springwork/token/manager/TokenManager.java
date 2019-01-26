package com.cff.springwork.token.manager;

public interface TokenManager {

	public String createToken(String username);

	public String getUserFromToken(String token);

	public void removeToken(String token);

}
