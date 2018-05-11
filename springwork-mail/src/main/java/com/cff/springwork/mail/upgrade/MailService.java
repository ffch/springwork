package com.cff.springwork.mail.upgrade;

import java.io.IOException;

public interface MailService {

	void sendSimpleMail(String to, String subject, String content);

	void sendHtmlMail(String to, String subject, String content);

	void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
	public void setMailMessage(MailMessage mailMessage);
	
	public void sendHtml(String content, MailType... mailTypes) throws IOException;
}
