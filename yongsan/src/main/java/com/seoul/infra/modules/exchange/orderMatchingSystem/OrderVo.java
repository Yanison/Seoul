package com.seoul.infra.modules.exchange.orderMatchingSystem;

public class OrderVo {
	
	private int shOrderType;
	private int shBos;
	private String shTime;
	private Integer limit;
	private String shValue;
	private Integer shValueInt;
	private Integer shSelectOne;
	
	public Integer getShSelectOne() {
		return shSelectOne;
	}
	public void setShSelectOne(Integer shSelectOne) {
		this.shSelectOne = shSelectOne;
	}
	public int getShOrderType() {
		return shOrderType;
	}
	public void setShOrderType(int shOrderType) {
		this.shOrderType = shOrderType;
	}
	public int getShBos() {
		return shBos;
	}
	public void setShBos(int shBos) {
		this.shBos = shBos;
	}
	public String getShTime() {
		return shTime;
	}
	public void setShTime(String shTime) {
		this.shTime = shTime;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getShValue() {
		return shValue;
	}
	public void setShValue(String shValue) {
		this.shValue = shValue;
	}
	public Integer getShValueInt() {
		return shValueInt;
	}
	public void setShValueInt(Integer shValueInt) {
		this.shValueInt = shValueInt;
	}
}
