package com.cff.springwork.mail.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cff.springwork.mail.upgrade.InlineFile;
import com.cff.springwork.mail.upgrade.JsonTable;
import com.cff.springwork.mail.upgrade.MailMessage;
import com.cff.springwork.mail.upgrade.MailService;
import com.cff.springwork.mail.upgrade.TextString;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring-test.xml"}) 
public class MailServiceTest {

	@Autowired
	private MailService mailService;

	// @Test
	public void testSimpleMail() throws Exception {
		mailService.sendSimpleMail("916881512@qq.com", "test simple mail", " hello this is simple mail");
	}

	// @Test
	public void testHtmlMail() throws Exception {
		String content = read();
		mailService.sendHtmlMail("chenfufei@sunlands.com", "test simple mail", content);
	}

	// @Test
	public void sendInlineResourceMail() {
		String rscId = "neo006";
		String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
		String imgPath = "C:/Users/fufei/Desktop/test.jpg";

		mailService.sendInlineResourceMail("chenfufei@sunlands.com", "主题：这是有图片的邮件", content, imgPath, rscId);
	}

	@Test
	public void sendHtml() throws Exception {
		MailMessage mailMessage = new MailMessage.Builder().from("xiaoyaofeiyang@aliyun.com")
				.to("chenfufei@sunlands.com").subject("这是有图片的邮件").build();
		mailService.setMailMessage(mailMessage);
		InlineFile img1 = new InlineFile();
		img1.setCid("chart1");
		img1.setFilePath("C:/Users/fufei/Desktop/jfree/20180510/PieChart252679.jpg");

		InlineFile img2 = new InlineFile();
		img2.setCid("chart2");
		img2.setFilePath("C:/Users/fufei/Desktop/jfree/20180510/PieChart536216.jpg");

		TextString text1 = new TextString();
		text1.setText("大河向东流！");

		JsonTable json = new JsonTable();
		json.setTitle("大野好");
		json.setData("C:/Users/fufei/Desktop/jfree/20180510/表1.txt");

		mailService.sendHtml("试试发送效果,下面是一张图片：<br>{}<br>,再来一张图片:<br>{}<br>,说句话吧：{},不说算呢了,下面来一张表格：<br>{}", img1, img2,
				text1, json);
	}

	private String read() throws IOException {
		String encoding = "UTF-8";
		String filePath = "C:/Users/fufei/Desktop/test.html";
		File tmpFile = new File(filePath);
		Long filelength = tmpFile.length();
		byte[] filecontent = new byte[filelength.intValue()];
		FileInputStream in = new FileInputStream(tmpFile);
		in.read(filecontent);
		in.close();
		String fileJson = new String(filecontent, encoding);
		return fileJson;
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
}
