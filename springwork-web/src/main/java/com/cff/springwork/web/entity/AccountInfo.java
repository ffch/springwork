package com.cff.springwork.web.entity;

import java.io.Serializable;

public class AccountInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5590810303104306443L;
	private String accNo;
	private String accName;
	private String bal;
	private String frzBal;
	private String loanBal;
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getBal() {
		return bal;
	}
	public void setBal(String bal) {
		this.bal = bal;
	}
	public String getFrzBal() {
		return frzBal;
	}
	public void setFrzBal(String frzBal) {
		this.frzBal = frzBal;
	}
	public String getLoanBal() {
		return loanBal;
	}
	public void setLoanBal(String loanBal) {
		this.loanBal = loanBal;
	}
	
}
