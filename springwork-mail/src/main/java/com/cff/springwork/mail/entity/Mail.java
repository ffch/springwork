package com.cff.springwork.mail.entity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * 
 *
 * 创建日期：2012-6-12
 */
public class Mail implements Serializable  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2462622153244370667L;
	

	/** charset */
	private String charset;
	/** 发信者的地址 */
	private String fromAddress;
	/** 发信者 */
	private String fromName;
	/** 送信地址(TO) */
	private List<String> to = new ArrayList<String>();
	/** 送信地址(CC) */
	private List<String> cc = new ArrayList<String>();
	/** 送信地址(BCC) */
	private List<String> bcc = new ArrayList<String>();
	/** 邮件名称 */
	private String subject;
	/** 邮件内容 */
	private String text;
	/** 署名 */
	private String signature;
	/** 附件 */
	private List<File> attach = new ArrayList<File>();
	/** 同步发送（默认是用异步发送的方式） */
	private boolean synchronization = false;

	/**
	 * 返回charset
	 * 
	 * @return charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * 设置charset
	 * 
	 * @param charset
	 *            charset
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 返回发信者的地址
	 * 
	 * @return 发信者的地址
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * 设置发信者的地址
	 * 
	 * @param fromAddress
	 *            发信者地址
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * 返回发信者
	 * 
	 * @return 发信者
	 */
	public String getFromName() {
		return fromName;
	}

	/**
	 * 设置发信者
	 * 
	 * @param fromName
	 *            发信者
	 */
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	/**
	 * 返回送信地址(TO)
	 * 
	 * @return 送信地址(TO)
	 */
	public List<String> getTo() {
		return to;
	}

	/**
	 * 设置送信地址(TO)
	 * 
	 * @param to
	 *            送信地址(TO)
	 */
	public void setTo(List<String> to) {
		this.to = to;
	}

	/**
	 * 追加送信地址(TO)
	 * 
	 * @param to
	 *            送信地址(TO)
	 */
	public void addTo(String to) {
		this.to.add(to);
	}

	/**
	 * 返回送信地址(CC)
	 * 
	 * @return 送信地址(CC)
	 */
	public List<String> getCc() {
		return cc;
	}

	/**
	 * 设置送信地址(CC)
	 * 
	 * @param cc
	 *            送信地址(CC)
	 */
	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	/**
	 * 追加送信地址(CC)
	 * 
	 * @param cc
	 *            送信地址(CC)
	 */
	public void addCc(String cc) {
		this.cc.add(cc);
	}

	/**
	 * 返回送信地址(BCC)
	 * 
	 * @return 返回送信地址(BCC)
	 */
	public List<String> getBcc() {
		return bcc;
	}

	/**
	 * 设置返回送信地址(BCC)
	 * 
	 * @param bcc
	 *            返回送信地址(BCC)
	 */
	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	/**
	 * 追加返回送信地址(BCC)
	 * 
	 * @param bcc
	 *            返回送信地址(BCC)
	 */
	public void addBcc(String bcc) {
		this.bcc.add(bcc);
	}

	/**
	 * 返回邮件名称
	 * 
	 * @return 邮件名称
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置邮件名称
	 * 
	 * @param subject
	 *            邮件名称
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 返回邮件内容
	 * 
	 * @return 邮件内容
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置邮件内容
	 * 
	 * @param text
	 *            邮件内容
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 返回邮件署名
	 * 
	 * @return 署名
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 设置邮件署名
	 * 
	 * @param signature
	 *            署名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * 返回附件
	 * 
	 * @return 附件
	 */
	public List<File> getAttach() {
		return attach;
	}

	/**
	 * 设置附件
	 * 
	 * @param attach
	 *            附件
	 */
	public void setAttach(List<File> attach) {
		this.attach = attach;
	}

	/**
	 * 追加附件
	 * 
	 * @param attach
	 *            附件
	 */
	public void addAttach(String attach) {
		this.attach.add(new File(attach));
	}

	/**
	 * 追加附件
	 * 
	 * @param attach
	 *            附件
	 */
	public void addAttach(File attach) {
		this.attach.add(attach);
	}

	/**
	 * 判断附件是否超过附件的最大设定

	 * 
	 * @param maxAttachSize
	 *            附件的最大设定

	 * @return TRUE:超过时。FALSE:没有超过时
	 */
	public boolean overMaxAttachSize(long maxAttachSize) {
		if (maxAttachSize == 0) {
			return false;
		}
		if (getAttachSize() == 0) {
			return false;
		}
		if (getAttachSize() > maxAttachSize) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 返回附件的大小

	 * 
	 * <pre>
	 * 返回此邮件的全部附件的大小。

	 * 单位是BYTE。

	 * 没有附件或者有内部错误的时候返回0。

	 * </pre>
	 * 
	 * @return 附件的大小

	 */
	public long getAttachSize() {
		try {
			if (getAttach() == null) {
				return 0;
			}
			long attachSize = 0;
			Iterator<File> it = getAttach().iterator();
			while (it.hasNext()) {
				File attachFile = it.next();
				attachSize += attachFile.length();
			}
			return attachSize;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @return the synchronization
	 */
	public boolean isSynchronization() {
		return synchronization;
	}

	/**
	 * @param synchronization the synchronization to set
	 */
	public void setSynchronization(boolean synchronization) {
		this.synchronization = synchronization;
	}

	
}
