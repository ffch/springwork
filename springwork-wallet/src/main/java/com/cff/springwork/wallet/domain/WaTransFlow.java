package com.cff.springwork.wallet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_trans_flow database table.
 * 
 */
@Entity
@Table(name="wa_trans_flow")
@NamedQuery(name="WaTransFlow.findAll", query="SELECT w FROM WaTransFlow w")
public class WaTransFlow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="trans_flow")
	private String transFlow;

	@Column(name="cancel_flag")
	private String cancelFlag;

	@Column(name="opp_acct")
	private String oppAcct;
	
	@Column(name="user_no")
	private String userNo;

	@Column(name="opp_item")
	private String oppItem;

	@Column(name="order_no")
	private String orderNo;

	@Column(name="refund_flag")
	private String refundFlag;

	@Column(name="refund_total")
	private int refundTotal;

	@Column(name="remark")
	private String remark;

	@Column(name="ret_code")
	private String retCode;

	@Column(name="ret_remark")
	private String retRemark;

	@Column(name="tran_status")
	private String tranStatus;

	private String trancode;

	@Column(name="trans_acct")
	private String transAcct;

	@Column(name="trans_amt")
	private int transAmt;

	@Column(name="trans_date")
	private String transDate;

	@Column(name="trans_item")
	private String transItem;

	@Column(name="trans_time")
	private String transTime;

	public WaTransFlow() {
	}

	public String getTransFlow() {
		return this.transFlow;
	}

	public void setTransFlow(String transFlow) {
		this.transFlow = transFlow;
	}

	public String getCancelFlag() {
		return this.cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getOppAcct() {
		return this.oppAcct;
	}

	public void setOppAcct(String oppAcct) {
		this.oppAcct = oppAcct;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getOppItem() {
		return this.oppItem;
	}

	public void setOppItem(String oppItem) {
		this.oppItem = oppItem;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRefundFlag() {
		return this.refundFlag;
	}

	public void setRefundFlag(String refundFlag) {
		this.refundFlag = refundFlag;
	}

	public int getRefundTotal() {
		return this.refundTotal;
	}

	public void setRefundTotal(int refundTotal) {
		this.refundTotal = refundTotal;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRetCode() {
		return this.retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetRemark() {
		return this.retRemark;
	}

	public void setRetRemark(String retRemark) {
		this.retRemark = retRemark;
	}

	public String getTranStatus() {
		return this.tranStatus;
	}

	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}

	public String getTrancode() {
		return this.trancode;
	}

	public void setTrancode(String trancode) {
		this.trancode = trancode;
	}

	public String getTransAcct() {
		return this.transAcct;
	}

	public void setTransAcct(String transAcct) {
		this.transAcct = transAcct;
	}

	public int getTransAmt() {
		return this.transAmt;
	}

	public void setTransAmt(int transAmt) {
		this.transAmt = transAmt;
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