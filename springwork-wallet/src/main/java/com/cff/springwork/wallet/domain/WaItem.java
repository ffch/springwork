package com.cff.springwork.wallet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_item database table.
 * 
 */
@Entity
@Table(name="wa_item")
@NamedQuery(name="WaItem.findAll", query="SELECT w FROM WaItem w")
public class WaItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_no")
	private String itemNo;

	@Column(name="acct_type")
	private String acctType;

	@Column(name="bal_dir")
	private String balDir;

	@Column(name="item_name")
	private String itemName;

	@Column(name="total_item")
	private String totalItem;

	@Column(name="valid_flag")
	private String validFlag;

	public WaItem() {
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getAcctType() {
		return this.acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getBalDir() {
		return this.balDir;
	}

	public void setBalDir(String balDir) {
		this.balDir = balDir;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTotalItem() {
		return this.totalItem;
	}

	public void setTotalItem(String totalItem) {
		this.totalItem = totalItem;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

}