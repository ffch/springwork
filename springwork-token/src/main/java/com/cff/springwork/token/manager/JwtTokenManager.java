package com.cff.springwork.token.manager;

import java.util.Date;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenManager implements TokenManager{
	private Long tokenExpiration;
	private String tokenSignKey;

	@Override
	public String createToken(String username) {
		String token = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
				.signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
		return token;
	}

	@Override
	public String getUserFromToken(String token) {
		String user = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
		return user;
	}

	@Override
	public void removeToken(String token) {
		//jwttoken无需删除，客户端扔掉即可。
	}

}
