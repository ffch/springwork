package com.cff.springwork.wallet.mail;

public class MailMessage {
	private String from;
	private String to;
	private String subject;

	MailMessage(Builder builder) {
		this.from = builder.from;
		this.to = builder.to;
		this.subject = builder.subject;
	}

	public static class Builder {
		private String from;
		private String to;
		private String subject;

		public Builder() {

		}

		public Builder from(String from) {
			this.from = from;
			return this;
		}

		public Builder to(String to) {
			this.to = to;
			return this;
		}

		public Builder subject(String subject) {
			this.subject = subject;
			return this;
		}

		public MailMessage build() {
			if (to == null)
				throw new IllegalStateException("to == null");
			return new MailMessage(this);
		}
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}
}
