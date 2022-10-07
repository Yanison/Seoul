package com.seoul.infra.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ExchangeBalance {
	
	private Integer exchangeBalanceSeq;
	private Integer cryptoSeq;
	private Integer memberSeq;
	private BigDecimal amount;
	private Integer transactedType;
	private String transactedFrom;
	private Date timestamp;
	
	public Integer getExchangeBalanceSeq() {
		return exchangeBalanceSeq;
	}
	public void setExchangeBalanceSeq(Integer exchangeBalanceSeq) {
		this.exchangeBalanceSeq = exchangeBalanceSeq;
	}
	public Integer getCryptoSeq() {
		return cryptoSeq;
	}
	public void setCryptoSeq(Integer cryptoSeq) {
		this.cryptoSeq = cryptoSeq;
	}
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getTransactedType() {
		return transactedType;
	}
	public void setTransactedType(Integer transactedType) {
		this.transactedType = transactedType;
	}
	public String getTransactedFrom() {
		return transactedFrom;
	}
	public void setTransactedFrom(String transactedFrom) {
		this.transactedFrom = transactedFrom;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	

	

}
