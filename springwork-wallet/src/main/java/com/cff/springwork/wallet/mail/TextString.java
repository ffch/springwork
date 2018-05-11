package com.cff.springwork.wallet.mail;

public class TextString extends MailType{
	private String text;
	@Override
	public char getType() {
		return MailType.TYPE_TEXT;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
