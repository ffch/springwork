package com.cff.springwork.network.common;


public class BussinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5327574598546086228L;

	String errorCode;
	String errorMsg;
	String errorName;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public BussinessException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public BussinessException(String errorName) {
		this.errorName = errorName;
	}
	
	public String getErrorName() {
		return errorName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public BussinessException(String errorCode,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorMsg = message;
	}
	public BussinessException(Throwable cause) {
		super(cause);
	}
	
}
