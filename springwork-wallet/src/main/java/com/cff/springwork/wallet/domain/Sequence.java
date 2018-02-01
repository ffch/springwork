package com.cff.springwork.wallet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sequence database table.
 * 
 */
@Entity
@NamedQuery(name="Sequence.findAll", query="SELECT s FROM Sequence s")
public class Sequence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sequence_name")
	private String sequenceName;

	@Column(name="value")
	private int value;

	public Sequence() {
	}

	public String getSequenceName() {
		return this.sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}