package com.seoul.infra.modules.exchange.dto;

import java.util.Date;

public class OderBookDto {
	
	private Integer orderSeq;
	private Integer buyOrSell;
	private Integer sbmittedBy;
	private Integer sbmittedCrypto;
	private double orderPrice;
	private double orderQuantity;
	private Date timestamp;
	
	
	public Integer getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(Integer orderSeq) {
		this.orderSeq = orderSeq;
	}
	public Integer getBuyOrSell() {
		return buyOrSell;
	}
	public void setBuyOrSell(Integer buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	public Integer getSbmittedBy() {
		return sbmittedBy;
	}
	public void setSbmittedBy(Integer sbmittedBy) {
		this.sbmittedBy = sbmittedBy;
	}
	public Integer getSbmittedCrypto() {
		return sbmittedCrypto;
	}
	public void setSbmittedCrypto(Integer sbmittedCrypto) {
		this.sbmittedCrypto = sbmittedCrypto;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public double getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(double orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	//OrderBook VO
	/*
	 * Entity 클래스에서 연산이 필요한 객체가 있다면 무엇일까?
	 * getOrderPrice() == 주문가격
	 * getOrderQuantity == 주문 수량
	 * 
	 */

}
