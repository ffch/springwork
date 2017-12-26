package com.cff.springwork.ldap.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cff.springwork.ldap.util.LdapUtil;
import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;

@Service
public class LdapService {
	@Value("${ldap.host}")
	private String ldapHost;
	@Value("${ldap.port}")
	private int ldapPort = 389;
	@Value("${ldap.bindDn}")
	private String ldapBindDN;
	@Value("${ldap.passwd}")
	private String ldapPassword;
	@Value("${ldap.baseDn}")
	private String ldapBaseDN;
	
	/**
	 * 
	 * @param userName
	 * @param passwd
	 * @return 0000 成功
	 * @throws UnsupportedEncodingException
	 * @throws LDAPException
	 */
	public String addUser(String userName,String passwd) throws UnsupportedEncodingException, LDAPException{
		String errorCode = "0000";
		LdapUtil ld = new LdapUtil(ldapHost,ldapPort, ldapBindDN, ldapPassword);
		LDAPConnection lc = ld.connect();
		String dn = genDN(userName);
		LDAPEntry le = ld.search(lc,dn);
		if(le != null){
			LDAPAttribute la = new LDAPAttribute("userPassword", passwd);
			LDAPModification lm = new LDAPModification(LDAPModification.REPLACE,la);
			ld.modify(lc, dn, lm);
		}else{
			LDAPAttributeSet attributeSet = new LDAPAttributeSet();
			attributeSet.add(new LDAPAttribute("objectclass", new String(
					"person")));
			attributeSet.add(new LDAPAttribute("cn", userName));
			attributeSet.add(new LDAPAttribute("sn", userName));
			attributeSet.add(new LDAPAttribute("userPassword", passwd));
			ld.add(lc, dn, attributeSet);
		}
		ld.close(lc);
		return errorCode;
	}
	
	/**
	 * 
	 * @param userName
	 * @return 0000 成功 0001不存在
	 * @throws UnsupportedEncodingException
	 * @throws LDAPException
	 */
	public String removeUser(String userName) throws UnsupportedEncodingException, LDAPException{
		String errorCode = "0000";
		LdapUtil ld = new LdapUtil(ldapHost,ldapPort, ldapBindDN, ldapPassword);
		LDAPConnection lc = ld.connect();
		String dn = genDN(userName);
		LDAPEntry le = ld.search(lc,dn);
		if(le != null){
			ld.remove(lc,dn);
		}else{
			errorCode = "0001";
		}
		ld.close(lc);
		return errorCode;
	}
	
	public String genDN(String userName){
		String dn = "cn=" + userName + "," + ldapBaseDN;
		return dn;
	}

	public String getLdapHost() {
		return ldapHost;
	}

	public void setLdapHost(String ldapHost) {
		this.ldapHost = ldapHost;
	}

	public int getLdapPort() {
		return ldapPort;
	}

	public void setLdapPort(int ldapPort) {
		this.ldapPort = ldapPort;
	}

	public String getLdapBindDN() {
		return ldapBindDN;
	}

	public void setLdapBindDN(String ldapBindDN) {
		this.ldapBindDN = ldapBindDN;
	}

	public String getLdapPassword() {
		return ldapPassword;
	}

	public void setLdapPassword(String ldapPassword) {
		this.ldapPassword = ldapPassword;
	}

	public String getLdapBaseDN() {
		return ldapBaseDN;
	}

	public void setLdapBaseDN(String ldapBaseDN) {
		this.ldapBaseDN = ldapBaseDN;
	}
}
