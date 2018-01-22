package com.cff.springwork.mail.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cff.springwork.mail.entity.Mail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


/***
 * 说明:<br>
 * **
 * 
 */
@Service
public class MailService {

	@Resource
	JavaMailSender javaMailSender;// 注入Spring封装的javamail，Spring的xml中已让框架装配
	@Resource
	TaskExecutor taskExecutor;// 注入Spring封装的异步执行器

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Mail createMail() {
		return new Mail();
	}

	public void sendMail(Mail mail) throws MessagingException, IOException {
		// 收件人为空时不发送
		if (mail == null || CollectionUtils.isEmpty(mail.getTo()))
			return;

		logger.debug("开始发送邮件。主送：{}", mail.getTo());
		if (mail.isSynchronization()) {
			sendMailBySynchronizationMode(mail);
		} else {
			sendMailByAsynchronousMode(mail);
		}
	}

	public void sendMail(List<Mail> mailList) throws MessagingException {
		if (CollectionUtils.isEmpty(mailList))
			return;
		for (int i = 0; i <= mailList.size(); i++) {
			try {
				sendMail(mailList.get(i));
			} catch (Exception e) {
				logger.error("邮件发送失败", e);
			}
		}

	}

	/**
	 * 异步发送
	 *
	 */
	protected void sendMailByAsynchronousMode(final Mail mail) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					sendMailBySynchronizationMode(mail);
				} catch (Exception e) {
					logger.error("异步发送邮件失败", e);
				}
			}
		});
	}

	/**
	 * 同步发送
	 *
	 * @throws IOException
	 */
	protected void sendMailBySynchronizationMode(Mail mail) throws MessagingException, IOException {
		// 建立邮件讯息
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
		messageHelper.setTo(mail.getTo().toArray(new String[mail.getTo().size()]));// 接受者

		// mail.fromAddress如果为空则初始化信息
		String fromMailAdd = mail.getFromAddress();
		if (StringUtils.isEmpty(fromMailAdd)) {
			fromMailAdd = "xiaoyaofeiyang@aliyun.com";
		}
		messageHelper.setFrom(new InternetAddress(fromMailAdd));// 发送者
		messageHelper.setSubject(mail.getSubject());// 主题

		messageHelper.setCc(mail.getCc().toArray(new String[mail.getCc().size()]));
		messageHelper.setBcc(mail.getBcc().toArray(new String[mail.getBcc().size()]));
		// 邮件内容，注意加参数true，表示启用html格式
		messageHelper.setText(mail.getText(), true);

		// 遍历附件,覆盖名字重复的附件
		Map<String, File> map = new HashMap<String, File>();

		if (mail.getAttach() != null) {
			for (File file : mail.getAttach()) {
				map.put(file.getPath(), file);
			}
		}

		Set<Map.Entry<String, File>> set = map.entrySet();
		if (set != null) {
			Iterator<Map.Entry<String, File>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, File> mapEntry = iterator.next();
				File file = mapEntry.getValue();
				messageHelper.addAttachment(file.getName(), file);
			}
		}

		javaMailSender.send(mailMessage);
	}
}
