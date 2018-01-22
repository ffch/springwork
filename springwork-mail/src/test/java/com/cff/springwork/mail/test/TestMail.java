package com.cff.springwork.mail.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cff.springwork.mail.entity.Mail;
import com.cff.springwork.mail.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring-test.xml"}) 
public class TestMail {
	@Autowired
	MailService mailService;
	@Test
	public void testMail() throws MessagingException, IOException {
		List<String> to = new ArrayList<String>();
		to.add("xxx@qq.com");
		List<String> cc = new ArrayList<String>();
		cc.add("xxxx@163.com");
		Mail mail = new Mail();
		mail.setTo(to);
		mail.setCc(cc);
		mail.setSubject("mayIComeIn,please");
		mail.setText("哈哈哈哈。谁知道么大是大叔大苏打三大三大");
		mail.setSynchronization(true);
		mailService.sendMail(mail );
	}
}
