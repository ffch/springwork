ldap在spring项目中的应用，主要是作为数据服务器使用，比如无线路由器wifi登录使用。
wifi允许用户登录以使用。可以将网络设备与ldap联动，程序到ldap服务器中增删改查，就实现了动态控制wifi用户密码。

1.ldap服务器搭建
	见ldap服务器搭建.docx

2.配置ldap的cn,passwd
	如：
	dn: cn=cff,dc=my-domain,dc=com
	objectClass: person
	objectClass: top
	cn: cff
	sn: cff
	userPassword: 881512
	
3.程序控制ldap。
	见代码。
4.网络设备配置。
	这个不懂，别人搞的。