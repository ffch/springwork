package com.cff.springwork.wallet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_account_flow database table.
 * 
 */
@Entity
@Table(name="wa_account_flow")
@NamedQuery(name="WaAccountFlow.findAll", query="SELECT w FROM WaAccountFlow w")
public class WaAccountFlow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="acc_flow")
	private String accFlow;

	@Column(name="bal_dir")
	private String balDir;

	@Column(name="flag")
	private String flag;

	@Column(name="opp_accno")
	private String oppAccno;

	@Column(name="opp_amt")
	private int oppAmt;

	@Column(name="opp_item")
	private String oppItem;

	@Column(name="tran_flow")
	private String tranFlow;

	@Column(name="trans_accno")
	private String transAccno;

	@Column(name="trans_amt")
	private int transAmt;

	@Column(name="trans_bal")
	private int transBal;

	@Column(name="trans_date")
	private String transDate;

	@Column(name="trans_item")
	private String transItem;

	@Column(name="trans_time")
	private String transTime;

	public WaAccountFlow() {
	}

	public String getAccFlow() {
		return this.accFlow;
	}

	public void setAccFlow(String accFlow) {
		this.accFlow = accFlow;
	}

	public String getBalDir() {
		return this.balDir;
	}

	public void setBalDir(String balDir) {
		this.balDir = balDir;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOppAccno() {
		return this.oppAccno;
	}

	public void setOppAccno(String oppAccno) {
		this.oppAccno = oppAccno;
	}

	public int getOppAmt() {
		return this.oppAmt;
	}

	public void setOppAmt(int oppAmt) {
		this.oppAmt = oppAmt;
	}

	public String getOppItem() {
		return this.oppItem;
	}

	public void setOppItem(String oppItem) {
		this.oppItem = oppItem;
	}

	public String getTranFlow() {
		return this.tranFlow;
	}

	public void setTranFlow(String tranFlow) {
		this.tranFlow = tranFlow;
	}

	public String getTransAccno() {
		return this.transAccno;
	}

	public void setTransAccno(String transAccno) {
		this.transAccno = transAccno;
	}

	public int getTransAmt() {
		return this.transAmt;
	}

	public void setTransAmt(int transAmt) {
		this.transAmt = transAmt;
	}

	public int getTransBal() {
		return this.transBal;
	}

	public void setTransBal(int transBal) {
		this.transBal = transBal;
	}

	public String getTransDate() {
		return this.transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getTransItem() {
		return this.transItem;
	}

	public void setTransItem(String transItem) {
		this.transItem = transItem;
	}

	public String getTransTime() {
		return this.transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

}