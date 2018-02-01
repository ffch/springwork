package com.cff.springwork.wallet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_product database table.
 * 
 */
@Entity
@Table(name="wa_product")
@NamedQuery(name="WaProduct.findAll", query="SELECT w FROM WaProduct w")
public class WaProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_no")
	private String productNo;

	@Column(name="is_repeat")
	private String isRepeat;

	@Column(name="item_no")
	private String itemNo;

	@Column(name="lst_mdfdate")
	private String lstMdfdate;

	@Column(name="max_draw_amt")
	private int maxDrawAmt;

	@Column(name="min_draw_amt")
	private int minDrawAmt;

	@Column(name="prduct_name")
	private String prductName;

	@Column(name="product_curr")
	private String productCurr;

	@Column(name="product_type")
	private String productType;

	@Column(name="tran_mode")
	private String tranMode;

	@Column(name="valid_flag")
	private String validFlag;

	public WaProduct() {
	}

	public String getProductNo() {
		return this.productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getIsRepeat() {
		return this.isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getLstMdfdate() {
		return this.lstMdfdate;
	}

	public void setLstMdfdate(String lstMdfdate) {
		this.lstMdfdate = lstMdfdate;
	}

	public int getMaxDrawAmt() {
		return this.maxDrawAmt;
	}

	public void setMaxDrawAmt(int maxDrawAmt) {
		this.maxDrawAmt = maxDrawAmt;
	}

	public int getMinDrawAmt() {
		return this.minDrawAmt;
	}

	public void setMinDrawAmt(int minDrawAmt) {
		this.minDrawAmt = minDrawAmt;
	}

	public String getPrductName() {
		return this.prductName;
	}

	public void setPrductName(String prductName) {
		this.prductName = prductName;
	}

	public String getProductCurr() {
		return this.productCurr;
	}

	public void setProductCurr(String productCurr) {
		this.productCurr = productCurr;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTranMode() {
		return this.tranMode;
	}

	public void setTranMode(String tranMode) {
		this.tranMode = tranMode;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

}