package com.cff.springwork.mail.upgrade;

public class JsonTable extends MailType{
	private String title;
	private String data;
	@Override
	public char getType() {
		return MailType.TYPE_JSON;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
