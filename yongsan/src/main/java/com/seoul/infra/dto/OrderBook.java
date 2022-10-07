package com.seoul.infra.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderBook {
	
	
	private Integer orderSeq;
	private Integer orderStatus;
	private Integer submittedBy;
	private Integer submittedCrypto;
	private Integer orderType;
	private BigDecimal price;
	private BigDecimal amount;
	private Integer activeNy;
	private Date timestamp;
	
	public Integer getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(Integer orderSeq) {
		this.orderSeq = orderSeq;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getSubmittedBy() {
		return submittedBy;
	}
	public void setSubmittedBy(Integer submittedBy) {
		this.submittedBy = submittedBy;
	}
	public Integer getSubmittedCrypto() {
		return submittedCrypto;
	}
	public void setSubmittedCrypto(Integer submittedCrypto) {
		this.submittedCrypto = submittedCrypto;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getActiveNy() {
		return activeNy;
	}
	public void setActiveNy(Integer activeNy) {
		this.activeNy = activeNy;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
