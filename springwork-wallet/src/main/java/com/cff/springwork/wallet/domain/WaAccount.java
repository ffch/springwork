package com.cff.springwork.wallet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_account database table.
 * 
 */
@Entity
@Table(name="wa_account")
@NamedQuery(name="WaAccount.findAll", query="SELECT w FROM WaAccount w")
public class WaAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="acc_no")
	private String accNo;

	@Column(name="ava_bal")
	private int avaBal;

	@Column(name="bal")
	private int bal;
	
	@Column(name="acc_type")
	private String accType;

	@Column(name="bal_dir")
	private String balDir;

	@Column(name="close_time")
	private String closeTime;

	@Column(name="dac")
	private String dac;

	@Column(name="frz_bal")
	private int frzBal;

	@Column(name="frz_flag")
	private String frzFlag;

	@Column(name="item_no")
	private String itemNo;

	@Column(name="last_time")
	private String lastTime;

	@Column(name="loan_bal")
	private int loanBal;

	@Column(name="lock_flag")
	private String lockFlag;

	@Column(name="open_time")
	private String openTime;

	@Column(name="passwd")
	private String passwd;

	@Column(name="product_no")
	private String productNo;

	@Column(name="status")
	private String status;

	@Column(name="user_no")
	private String userNo;

	@Version
	@Column(name="version")
	private int version;

	public WaAccount() {
	}

	public String getAccNo() {
		return this.accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public int getAvaBal() {
		return this.avaBal;
	}

	public void setAvaBal(int avaBal) {
		this.avaBal = avaBal;
	}

	public int getBal() {
		return this.bal;
	}

	public void setBal(int bal) {
		this.bal = bal;
	}

	public String getBalDir() {
		return this.balDir;
	}

	public void setBalDir(String balDir) {
		this.balDir = balDir;
	}

	public String getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getDac() {
		return this.dac;
	}

	public void setDac(String dac) {
		this.dac = dac;
	}

	public int getFrzBal() {
		return this.frzBal;
	}

	public void setFrzBal(int frzBal) {
		this.frzBal = frzBal;
	}

	public String getFrzFlag() {
		return this.frzFlag;
	}

	public void setFrzFlag(String frzFlag) {
		this.frzFlag = frzFlag;
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public int getLoanBal() {
		return this.loanBal;
	}

	public void setLoanBal(int loanBal) {
		this.loanBal = loanBal;
	}

	public String getLockFlag() {
		return this.lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getProductNo() {
		return this.productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}