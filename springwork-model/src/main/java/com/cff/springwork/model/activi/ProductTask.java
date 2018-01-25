package com.cff.springwork.model.activi;

import java.io.Serializable;

public class ProductTask implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3481818865839076537L;
	
	String taskid;
	String userid;
	String type;
	String tasktype;
	String content;
	String name;
	String email;
	String mobile;
	String title;
	String curviewer;
	int money;
	String status;
	String realTaskId;
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTasktype() {
		return tasktype;
	}
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurviewer() {
		return curviewer;
	}
	public void setCurviewer(String curviewer) {
		this.curviewer = curviewer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRealTaskId() {
		return realTaskId;
	}
	public void setRealTaskId(String realTaskId) {
		this.realTaskId = realTaskId;
	}
	public String toString(){
		return taskid + "---" + userid + "----" +title+"---"+ type+"--" +tasktype+ "--" +content+ "---" +name + "--" +email+ "--" +mobile;
	}
}
