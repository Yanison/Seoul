package com.seoul.infra.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
	private Integer transactionSeq;
	private Integer memberSell;
	private Integer memberBuy;
	private Integer orderBookSeq;
	private Date timestamp;
	private BigDecimal price;
	private BigDecimal amount;
	private Integer transactedFrom;
	private Integer transactedType;
	
	
	public Integer getTransactionSeq() {
		return transactionSeq;
	}
	public void setTransactionSeq(Integer transactionSeq) {
		this.transactionSeq = transactionSeq;
	}
	public Integer getMemberSell() {
		return memberSell;
	}
	public void setMemberSell(Integer memberSell) {
		this.memberSell = memberSell;
	}
	public Integer getMemberBuy() {
		return memberBuy;
	}
	public void setMemberBuy(Integer memberBuy) {
		this.memberBuy = memberBuy;
	}
	public Integer getOrderBookSeq() {
		return orderBookSeq;
	}
	public void setOrderBookSeq(Integer orderBookSeq) {
		this.orderBookSeq = orderBookSeq;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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
	public Integer getTransactedFrom() {
		return transactedFrom;
	}
	public void setTransactedFrom(Integer transactedFrom) {
		this.transactedFrom = transactedFrom;
	}
	public Integer getTransactedType() {
		return transactedType;
	}
	public void setTransactedType(Integer transactedType) {
		this.transactedType = transactedType;
	}

	
	
	
}
