package com.cff.springwork.web.entity;

import java.io.Serializable;
import java.util.List;

public class VideoListDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2288948975034612049L;
	int sum;
	int totalPageNum;
	int curPageNum;
	String catid;
	int ord;
	List<String> coeds;
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public int getCurPageNum() {
		return curPageNum;
	}
	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}
	public String getCatid() {
		return catid;
	}
	public void setCatid(String catid) {
		this.catid = catid;
	}
	public int getOrd() {
		return ord;
	}
	public void setOrd(int ord) {
		this.ord = ord;
	}
	public List<String> getCoeds() {
		return coeds;
	}
	public void setCoeds(List<String> coeds) {
		this.coeds = coeds;
	}
}
