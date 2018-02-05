package com.cff.springwork.wallet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wa_drcr_control database table.
 * 
 */
@Entity
@Table(name="wa_drcr_control")
@NamedQuery(name="WaDrcrControl.findAll", query="SELECT w FROM WaDrcrControl w")
public class WaDrcrControl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cr_accno")
	private String crAccno;

	@Column(name="dr_accno")
	private String drAccno;

	@Column(name="drcr_dir")
	private String drcrDir;

	@Column(name="trans_amt")
	private String transAmt;

	@Id
	@Column(name="trans_code")
	private String transCode;

	@Column(name="trans_name")
	private String transName;

	public WaDrcrControl() {
	}

	public String getCrAccno() {
		return this.crAccno;
	}

	public void setCrAccno(String crAccno) {
		this.crAccno = crAccno;
	}

	public String getDrAccno() {
		return this.drAccno;
	}

	public void setDrAccno(String drAccno) {
		this.drAccno = drAccno;
	}

	public String getDrcrDir() {
		return this.drcrDir;
	}

	public void setDrcrDir(String drcrDir) {
		this.drcrDir = drcrDir;
	}

	public String getTransAmt() {
		return this.transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getTransCode() {
		return this.transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getTransName() {
		return this.transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

}