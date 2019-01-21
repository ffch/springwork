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
