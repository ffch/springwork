package com.cff.springwork.ldap.util;


import java.io.UnsupportedEncodingException;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;

public class LdapUtil {
	private String ldapHost;
	private int ldapPort = 389;
	private String ldapBindDN;
	private String ldapPassword;
	private int ldapVersion = LDAPConnection.LDAP_V3;
	
	public LdapUtil() {
		super();
	}
	public LdapUtil(String ldapHost, String ldapBindDN, String ldapPassword) {
		super();
		this.ldapHost = ldapHost;
		this.ldapBindDN = ldapBindDN;
		this.ldapPassword = ldapPassword;
	}
	public LdapUtil(String ldapHost, int port, String ldapBindDN, String ldapPassword) {
		super();
		this.ldapHost = ldapHost;
		this.ldapPort = port;
		this.ldapBindDN = ldapBindDN;
		this.ldapPassword = ldapPassword;
	}
	public LdapUtil(String ldapHost, int ldapPort, String ldapBindDN, String ldapPassword, int ldapVersion) {
		super();
		this.ldapHost = ldapHost;
		this.ldapPort = ldapPort;
		this.ldapBindDN = ldapBindDN;
		this.ldapPassword = ldapPassword;
		this.ldapVersion = ldapVersion;
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
	public int getLdapVersion() {
		return ldapVersion;
	}
	public void setLdapVersion(int ldapVersion) {
		this.ldapVersion = ldapVersion;
	}
	/**
	 * 根据配置连接LDAP服务器
	 * @return LDAPConnection 
	 * @throws LDAPException 连接失败
	 * @throws UnsupportedEncodingException 密码格式错
	 */
	public LDAPConnection connect() throws LDAPException, UnsupportedEncodingException
	{ 
		LDAPConnection lc = new LDAPConnection();
		lc.connect(ldapHost, ldapPort);
		lc.bind(ldapVersion, ldapBindDN, ldapPassword.getBytes("UTF8"));
		return lc;
    }
	
	/**
	 * 根据dn查询该dn下条目
	 * @param dn 例：cn=15607110725,dc=my-domain,dc=com
	 * @return LDAPEntry
	 * @throws LDAPException 连接失败/查找失败
	 * @throws UnsupportedEncodingException 密码格式错
	 */
	public LDAPEntry search(String dn) throws LDAPException, UnsupportedEncodingException{
		LDAPConnection lc;
		LDAPEntry le = null;
		lc = connect();
		le = lc.read(dn);
		lc.disconnect();
		return le;
	}
	
	/**
	 * 根据dn查询指定连接下该dn下条目
	 * @param lc LDAPConnection
	 * @param dn 例：cn=15607110725,dc=my-domain,dc=com
	 * @return LDAPEntry
	 * @throws LDAPException 查找失败
	 */
	public LDAPEntry search(LDAPConnection lc, String dn) throws LDAPException{
		LDAPEntry le = null;
		le = lc.read(dn);
		return le;
	}
	
	/**
	 * 增加新条目/用户
	 * @param dn 例：cn=15607110725,dc=my-domain,dc=com
	 * @param attributeSet LDAPAttributeSet ldap属性集合
	 * @throws UnsupportedEncodingException  密码格式错
	 * @throws LDAPException 连接失败/添加失败
	 */
	public void add(String dn, LDAPAttributeSet attributeSet) throws UnsupportedEncodingException, LDAPException{
		LDAPConnection lc = connect();
		LDAPEntry newEntry = new LDAPEntry( dn, attributeSet);
		lc.add(newEntry);
		lc.disconnect();
	}
	
	/**
	 * 指定连接增加新条目/用户
	 * @param lc LDAPConnection
	 * @param dn 例：cn=15607110725,dc=my-domain,dc=com
	 * @param attributeSet LDAPAttributeSet ldap属性集合
	 * @throws LDAPException 添加失败
	 */
	public void add(LDAPConnection lc, String dn, LDAPAttributeSet attributeSet) throws LDAPException{
		LDAPEntry newEntry = new LDAPEntry( dn, attributeSet);
		lc.add(newEntry);
	}
	
	/**
	 * 指定连接增加新条目
	 * @param lc LDAPConnection
	 * @param newEntry LDAPEntry：ldap条目
	 * @throws LDAPException 添加失败
	 */
	public void add(LDAPConnection lc, LDAPEntry newEntry) throws LDAPException{
		lc.add(newEntry);
	}
	
	/**
	 * 删除指定连接下dn所有内容
	 * @param lc LDAPConnection
	 * @param dn 例：cn=15607110725,dc=my-domain,dc=com
	 * @throws LDAPException 删除失败
	 */
	public void remove(LDAPConnection lc, String dn) throws LDAPException{
		lc.delete(dn);
	}
	
	/**
	 * 删除指定dn所有内容
	 * @param dn 例：cn=15607110725,dc=my-domain,dc=com
	 * @throws LDAPException 查找失败/删除失败
	 * @throws UnsupportedEncodingException 密码格式错
	 */
	public void remove(String dn) throws LDAPException, UnsupportedEncodingException{
		LDAPConnection lc = connect();
		lc.delete(dn);
		lc.disconnect();
	}
	
	/**
	 * 修改指定dn下某属性
	 * @param lc LDAPConnection
	 * @param dn 例：cn=15607110725,dc=my-domain,dc=com
	 * @param mod LDAPModification 修改的属性
	 * @throws LDAPException 修改失败
	 */
	public void modify(LDAPConnection lc, String dn, LDAPModification mod) throws LDAPException{
		lc.modify(dn, mod);
	}
	
	/**
	 * 关闭连接
	 * @param lc LDAPConnection
	 * @throws LDAPException 
	 */
	public void close(LDAPConnection lc) throws LDAPException{
		if(lc.isConnected()){
			lc.disconnect();
		}
	}
}