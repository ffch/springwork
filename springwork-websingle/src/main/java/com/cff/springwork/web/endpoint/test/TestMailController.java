package com.cff.springwork.web.endpoint.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cff.springwork.mail.entity.Mail;
import com.cff.springwork.mail.service.MailService;


@RestController("testMailController")
@RequestMapping("/mail")
public class TestMailController {
	@Autowired
	MailService mailService;
	static Map<String,List<String>> groupMap = new ConcurrentHashMap<>();
	
	static{
		List<String> cc = new ArrayList<>();
		cc.add("xxx@qq.com");
		cc.add("xxx@sunlands.com");
		groupMap.put("sms", cc);
	}
	
	@RequestMapping("/send")
	public String send(@RequestParam String content,@RequestParam String subject, @RequestParam String group){
		Mail mail = new Mail();
		mail.setTo(groupMap.get(group));
		mail.setSubject(subject);
		mail.setText(content);
		try {
			mailService.sendMail(mail);
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
}
