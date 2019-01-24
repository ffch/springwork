## Spring和Spring Mvc整合详解

### 官方主页
[Spring](https://docs.spring.io/spring/docs/5.0.12.RELEASE/spring-framework-reference/core.html#spring-core)

[Spring Mvc](https://docs.spring.io/spring/docs/5.0.12.RELEASE/spring-framework-reference/web.html#spring-web)

### 概述

Spring Mvc的启动方式不同于Spring Boot，Spring Boot内嵌了tomcat容器，可以打包成jar文件快速启动。Spring Mvc仍需要打包成war包。所以，它是离不开web.xml配置。

配置Spring和Spring Mvc，主要有：
- 1.在web.xml中配置好Spring相关Listener/Filter/Servlet，并指明Spring和Spring Mvc的配置文件，当然，也可以不指定，放在classpath下就行，严谨一点还是写出来为好。
- 2.配置applicationContext.xml，这个是给Spring用的，名字随意，只要在web.xml指定就行。
- 3.配置 spring-servlet.xml，这个是给Spring Mvc用的，名字随意，只要在web.xml指定就行。

### 开始搭建

#### 依赖Jar包

```
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-core</artifactId>
	<version>${spring.version}</version>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context</artifactId>
	<version>${spring.version}</version>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-beans</artifactId>
	<version>${spring.version}</version>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-web</artifactId>
	<version>${spring.version}</version>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-webmvc</artifactId>
	<version>${spring.version}</version>
</dependency>
```
#### web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
  <display-name>UmpEnt</display-name>
  <context-param>
    <param-name>log4jConfigLocation</param-name>
    <param-value>classpath:properties/log4j.properties</param-value>
  </context-param>
  <context-param>
    <param-name>log4jRefreshInterval</param-name>
    <param-value>600000</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
  </listener>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
  </context-param>
  <filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <filter>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <servlet>
    <servlet-name>springDispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-servlet.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>springDispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
  <welcome-file-list>
    <welcome-file>/index.html</welcome-file>
  </welcome-file-list>
</web-app>
```

#### Spring配置
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p" xmlns:cache="http://www.springframework.org/schema/cache"
	xsi:schemaLocation="
                    http://www.springframework.org/schema/beans
                    http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
                    http://www.springframework.org/schema/tx 
                    http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
                    http://www.springframework.org/schema/aop 
                    http://www.springframework.org/schema/aop/spring-aop-4.0.xsd
                    http://www.springframework.org/schema/context      
                    http://www.springframework.org/schema/context/spring-context-4.0.xsd
                    http://www.springframework.org/schema/cache 
                    http://www.springframework.org/schema/cache/spring-cache-4.0.xsd">

	<context:annotation-config />
	<context:component-scan base-package="com.cff.springwork">
	</context:component-scan>
	
	
</beans>
```

这个配置文件里可以写入一些bean的配置。

Spring是一个大的父容器，Spring Mvc是其中的一个子容器。父容器不能访问子容器对象，但是子容器可以访问父容器对象。 因此，bean的配置要写到这个文件中，而不是Spring Mvc的配置文件中。

#### Spring Mvc配置

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:mvc="http://www.springframework.org/schema/mvc" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation=" 
                http://www.springframework.org/schema/beans 
                http://www.springframework.org/schema/beans/spring-beans.xsd 
                http://www.springframework.org/schema/context 
                http://www.springframework.org/schema/context/spring-context.xsd 
                http://www.springframework.org/schema/mvc 
                http://www.springframework.org/schema/mvc/spring-mvc.xsd">

	<context:annotation-config />
	<context:component-scan base-package="com.cff.springwork">
	</context:component-scan>
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="viewClass"
			value="org.springframework.web.servlet.view.JstlView" />
		<property name="prefix" value="" />
	</bean>

	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	</bean>

	<bean
		class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping" />

	<bean
		class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
		<property name="messageConverters">
			<list>
				<bean
					class="org.springframework.http.converter.StringHttpMessageConverter" />
				<bean
					class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
					<property name="supportedMediaTypes">
						<list>
							<value>application/json;charset=UTF-8</value>
						</list>
					</property>
				</bean>
			</list>
		</property>
	</bean>
	<mvc:resources mapping="/resources/**" location="/resources/" />

	<mvc:annotation-driven>
		<mvc:message-converters>
			<bean
				class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
			</bean>
		</mvc:message-converters>
	</mvc:annotation-driven>

	<mvc:resources location="/img/" mapping="/img/**" />
	<mvc:resources location="/css/" mapping="/css/**" />
	<mvc:resources location="/js/" mapping="/js/**" />
	<mvc:default-servlet-handler/>
</beans>
```
Spring是一个大的父容器，Spring Mvc是其中的一个子容器。父容器不能访问子容器对象，但是子容器可以访问父容器对象。 因此，bean的配置要写到这个文件中，而不是Spring Mvc的配置文件中。

#### 简单的Controller

```
package com.cff.springwork.web.endpoint.test;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cff.springwork.web.entity.WelEntity;

@RestController("testController")
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/welCome")
	public WelEntity welCome(@RequestParam String reqType){
		String uuid = UUID.randomUUID().toString();
		String welMsg = "welcome 程序猿";
		if(reqType != null && "1000".equals(reqType)){
			welMsg = "welcome 程序媛";
		}
		WelEntity welEntity = new WelEntity();
		welEntity.setUuid(uuid);
		welEntity.setWelMsg(welMsg);
		return welEntity;
	}
}
```

实体：
```
package com.cff.springwork.web.entity;

public class WelEntity {
	private String uuid;
	private String welMsg;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getWelMsg() {
		return welMsg;
	}
	public void setWelMsg(String welMsg) {
		this.welMsg = welMsg;
	}
}
```

#### 前端配合

```
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.0/jquery.min.js"></script>
<title>SkyNet</title>
</head>
<script type="text/javascript">
	function ajaxTest() {
		var type = "2";
		$.ajax({
			type : "post",
			url : "../test/welCome",
			dataType : "json",
			data : {reqType : type} ,
			success : function(data) {
				$("#div1").html(data.uuid + "<br>" + 
						data.welMsg + "<br>"+
						data.dateTime);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
</script>
<body>
	这是html
	<div id="div1"></div>
	<button type="button" onclick="ajaxTest()">Welcome</button>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#div1").html("呵呵");
		});
	</script>
</body>
</html>
```


### 快速构建项目
[Spring组件化构建](https://www.pomit.cn/java/spring/spring.html)