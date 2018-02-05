package com.cff.springwork.wallet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_liq_account database table.
 * 
 */
@Entity
@Table(name="wa_liq_account")
@NamedQuery(name="WaLiqAccount.findAll", query="SELECT w FROM WaLiqAccount w")
public class WaLiqAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_no")
	private String itemNo;

	@Column(name="liq_level")
	private String liqLevel;

	@Column(name="lliqc_acctno")
	private String lliqcAcctno;

	@Column(name="lliqd_acctno")
	private String lliqdAcctno;

	@Column(name="uliq_acctno")
	private String uliqAcctno;

	@Column(name="up_itemno")
	private String upItemno;


	public WaLiqAccount() {
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getLiqLevel() {
		return this.liqLevel;
	}

	public void setLiqLevel(String liqLevel) {
		this.liqLevel = liqLevel;
	}

	public String getLliqcAcctno() {
		return this.lliqcAcctno;
	}

	public void setLliqcAcctno(String lliqcAcctno) {
		this.lliqcAcctno = lliqcAcctno;
	}

	public String getLliqdAcctno() {
		return this.lliqdAcctno;
	}

	public void setLliqdAcctno(String lliqdAcctno) {
		this.lliqdAcctno = lliqdAcctno;
	}

	public String getUliqAcctno() {
		return this.uliqAcctno;
	}

	public void setUliqAcctno(String uliqAcctno) {
		this.uliqAcctno = uliqAcctno;
	}

	public String getUpItemno() {
		return this.upItemno;
	}

	public void setUpItemno(String upItemno) {
		this.upItemno = upItemno;
	}

}