package com.cff.springwork.model.activi;

import java.io.Serializable;

public class TaskType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8922139729593122806L;
	String tasktype;
	String name;
	String type;
	public String getTasktype() {
		return tasktype;
	}
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
