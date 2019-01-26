package com.cff.springwork.token.handler;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

	public DefaultPasswordEncoder() {
		this(-1);
	}

	/**
	 * @param strength
	 *            the log rounds to use, between 4 and 31
	 */
	public DefaultPasswordEncoder(int strength) {

	}

	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}
}
