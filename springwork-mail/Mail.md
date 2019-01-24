## Spring和Email详解

### 官方主页

[Spring Email](https://docs.spring.io/spring/docs/5.0.12.RELEASE/spring-framework-reference/integration.html#mail-introduction)

### 概述

Spring Mail API都在org.springframework.mail及其子包org.springframework.mail.javamail中封装。 

JavaMailSenderImpl: 邮件发送器，主要提供了邮件发送接口、透明创建Java Mail的MimeMessage、及邮件发送的配置(如:host/port/username/password...)。 
MimeMailMessage、MimeMessageHelper：对MimeMessage进行了封装。Spring还提供了一个回调接口MimeMessagePreparator, 用于准备JavaMail的MIME信件. 


### 开始搭建

#### 依赖Jar包

```
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context-support</artifactId>
	<version>${spring.version}</version>
</dependency>
<dependency>
	<groupId>javax.mail</groupId>
	<artifactId>mail</artifactId>
	<version>1.4.7</version>
</dependency>
```
#### spring-mail.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xmlns:aop="http://www.springframework.org/schema/aop"
	   xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
                     http://www.springframework.org/schema/beans
                     http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
                     http://www.springframework.org/schema/aop
                     http://www.springframework.org/schema/aop/spring-aop-4.0.xsd
                     http://www.springframework.org/schema/context
                     http://www.springframework.org/schema/context/spring-context-4.0.xsd ">

	<bean id="annotationPropertyConfigurerMail"
		  class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="order" value="1" />
		<property name="ignoreUnresolvablePlaceholders" value="true" />
		<property name="locations">
			<list>
				<value>classpath:mail.properties</value>
			</list>
		</property>
	</bean>

	<bean id="javaMailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
		<property name="host" value="${mail.smtpHost}" />
		<property name="port" value="${mail.port}" />
		<property name="username" value="${mail.username}" />
		<property name="password" value="${mail.password}" />
		<property name="defaultEncoding" value="${mail.charset}"></property>
		<property name="javaMailProperties">
		<props>
			<prop key="mail.smtp.auth">${mail.smtp.auth}</prop>
		</props>
		</property>
	</bean>
	
</beans>
```
这里，声明了javaMailSender的bean，配置从mail.properties配置文件中取，spring-mail.xml配置完成后，只需要在spring的配置文件中
```<import resource="classpath*:spring-mail.xml"/>```
即可

#### mail.properties配置文件
```
mail.smtpHost=smtp.aliyun.com
mail.charset=utf-8
mail.fromAddress=xxxx@aliyun.com
mail.maxAttachSize=10M
mail.port=25
mail.username=xxxx@aliyun.com
mail.password=xxxxx
mail.smtp.auth=true
```

#### 发送邮件业务逻辑

这里，我们对Mail组件进行了扩展，以方便发送多类型的邮件。

MailServiceImpl：

```
package com.cff.springwork.mail.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.cff.springwork.mail.entity.AttachFile;
import com.cff.springwork.mail.entity.InlineFile;
import com.cff.springwork.mail.entity.JsonTable;
import com.cff.springwork.mail.entity.MailMessage;
import com.cff.springwork.mail.entity.MailType;
import com.cff.springwork.mail.entity.TextString;

@Component
public class MailServiceImpl implements MailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	static final String DELIM_STR = "{}";
	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.fromAddress}")
	private String from;

	MailMessage mailMessage;

	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);

		try {
			mailSender.send(message);
			logger.info("简单邮件已经发送。");
		} catch (Exception e) {
			logger.error("发送简单邮件时发生异常！", e);
		}

	}

	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			mailSender.send(message);
			logger.info("html邮件发送成功");
		} catch (MessagingException e) {
			logger.error("发送html邮件时发生异常！", e);
		}
	}

	@Override
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource res = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, res);

			mailSender.send(message);
			logger.info("嵌入静态资源的邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送嵌入静态资源的邮件时发生异常！", e);
		}
	}

	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);

			mailSender.send(message);
			logger.info("带附件的邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送带附件的邮件时发生异常！", e);
		}
	}

	public MailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void sendHtml(String content, MailType... mailTypes) throws IOException {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailMessage.getFrom());
			helper.setTo(mailMessage.getTo());
			helper.setSubject(mailMessage.getSubject());
			String msg = getContent(content, mailTypes);
			System.out.println(msg);
			helper.setText(msg, true);
			for (MailType item : mailTypes) {
				switch (item.getType()) {
				case MailType.TYPE_FILE:
					InlineFile inlineFile = (InlineFile) item;
					helper.addInline(inlineFile.getCid(), new File(inlineFile.getFilePath()));
					break;
				case MailType.TYPE_ATTACH:
					AttachFile attachFile = (AttachFile) item;
					helper.addAttachment(attachFile.getFileName(), new File(attachFile.getFilePath()));
					break;
				}
			}
			
//			mailSender.send(message);
			logger.info("带附件的邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送带附件的邮件时发生异常！", e);
		}
	}

	public String getContent(String content, MailType... mailTypes)
			throws MessagingException, IOException {
		String bodyPrefix = "<html><body>";
		String bodySuffix = "</body></html>";
		StringBuffer sb = new StringBuffer();
		sb.append(bodyPrefix);
		for (MailType item : mailTypes) {
			if(content.length() < 1)break;

			int index = content.indexOf(DELIM_STR);
			if(index == -1)break;
			sb.append(content.substring(0, index));
			switch (item.getType()) {
			case MailType.TYPE_FILE:
				InlineFile inlineFile = (InlineFile) item;
				sb.append("<img src=\'cid:" + inlineFile.getCid() + "\' />");
				break;
			case MailType.TYPE_TEXT:
				TextString textString = (TextString) item;
				sb.append(textString.getText());
				break;
			case MailType.TYPE_JSON:
				JsonTable json = (JsonTable) item;
				sb.append(genReportData(json));
				break;
			}
			content = content.substring(index + 2);
		}
		sb.append(content);
		sb.append(bodySuffix);
		return sb.toString();
	}
	
	private String read(String filePath) throws IOException {
		String encoding = "UTF-8";
		File tmpFile = new File(filePath);
		Long filelength = tmpFile.length();
		byte[] filecontent = new byte[filelength.intValue()];
		FileInputStream in = new FileInputStream(tmpFile);
		in.read(filecontent);
		in.close();
		String fileJson = new String(filecontent, encoding);
		return fileJson;
	}
	
	private String genReportData(JsonTable jsonTable) throws IOException {
		JSONArray ja = (JSONArray) JSON.parse(read(jsonTable.getData()),Feature.OrderedField);
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("<br />\n");
			sb.append("<table border=\"1\" style=\"border-collapse:collapse;font-size:14px\">\n");
			sb.append("<caption align = \"left\">");
			sb.append(jsonTable.getTitle());
			sb.append("</caption>\n");
			JSONObject jsonFirst = (JSONObject) ja.get(0);
			
			sb.append("<tr>\n");
			for(String key : jsonFirst.keySet()){
				sb.append("<td>");
				sb.append(jsonFirst.get(key));
				sb.append("</td>\n");
			}
			
			sb.append("</tr>\n");
			ja.remove(0);
			for (Object column : ja) {
				sb.append("<tr>\n");
				JSONObject json = (JSONObject) column;
				for(String key : jsonFirst.keySet()){
					sb.append("<td>");
					sb.append(json.get(key));
					sb.append("</td>\n");
				}
				
				sb.append("</tr>\n");
			}

			sb.append("</table>\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
```

MailService：

```
package com.cff.springwork.mail.service;

import java.io.IOException;

import com.cff.springwork.mail.entity.MailMessage;
import com.cff.springwork.mail.entity.MailType;

public interface MailService {

	void sendSimpleMail(String to, String subject, String content);

	void sendHtmlMail(String to, String subject, String content);

	void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
	public void setMailMessage(MailMessage mailMessage);
	
	public void sendHtml(String content, MailType... mailTypes) throws IOException;
}
```

#### 多种邮件类型

我们可以定义MailType抽象类，以适应不同的邮件类型。

MailType：

```
package com.cff.springwork.mail.entity;

public abstract class MailType {
	public final static char TYPE_FILE ='F';
	public final static char TYPE_ATTACH ='A';
	public final static char TYPE_TEXT ='T';
	public final static char TYPE_JSON ='J';
	public abstract char getType();
}
```

InlineFile 是内嵌文件类型。

InlineFile ：

```
package com.cff.springwork.mail.entity;

public class InlineFile extends MailType{
	private String filePath;
	private String cid;
	@Override
	public char getType() {
		return MailType.TYPE_FILE;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
}
```

详细完整代码，可以在[Spring组件化构建](https://www.pomit.cn/java/spring/spring.html)中选择查看，并下载。


### 快速构建项目
[Spring组件化构建](https://www.pomit.cn/java/spring/spring.html)