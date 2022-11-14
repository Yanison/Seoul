package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.sql.Date;

public class Order  {
	
	/*
	 * 주문자 정보
	 */
	private Integer memberSeq;
	/*
	 * 거래된 코인 정보
	 */
	private Integer cryptoSeq;
	
	/*
	 * 주문 수량
	 */
	private double obAmount;
	/*
	 * 주문 가격
	 */
	private double price;
	/*
	 * 주문정보
	 */
	private Integer obSeq;
	/*
	 * 주문 타입 Limit or Market
	 */
	private Integer orderType;
	/*
	 * 주문 타입 buy or sell
	 */
	private Integer bos;
	/*
	 * 주문 시간
	 */
	private Date timestamp;
	
	
	public Integer getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(final Integer memberSeq) {
		this.memberSeq = memberSeq;
	}
	public Integer getCryptoSeq() {
		return cryptoSeq;
	}
	public void setCryptoSeq(final Integer cryptoSeq) {
		this.cryptoSeq = cryptoSeq;
	}
	public double getObAmount() {
		return obAmount;
	}
	public void setObAmount(final double obAmount) {
		this.obAmount = obAmount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(final double price) {
		this.price = price;
	}
	public Integer getObSeq() {
		return obSeq;
	}
	public void setObSeq(final Integer obSeq) {
		this.obSeq = obSeq;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(final Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getBos() {
		return bos;
	}
	public void setBos(Integer bos) {
		this.bos = bos;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	/*
	 * 거래내역 정보 요소들
	 */
	private Integer transactionseq;
	private Integer memberSeqSell;
	private Integer memberSeqBuy;
	private Integer obSeqSell;
	private Integer obSeqBuy;
	private Integer orderStatus;
	private Integer transactedType;
	private double amount;
	
	public Integer getTransactionseq() {
		return transactionseq;
	}
	public void setTransactionseq(Integer transactionseq) {
		this.transactionseq = transactionseq;
	}
	public Integer getMemberSeqSell() {
		return memberSeqSell;
	}
	public void setMemberSeqSell(Integer memberSeqSell) {
		this.memberSeqSell = memberSeqSell;
	}
	public Integer getMemberSeqBuy() {
		return memberSeqBuy;
	}
	public void setMemberSeqBuy(Integer memberSeqBuy) {
		this.memberSeqBuy = memberSeqBuy;
	}
	public Integer getObSeqSell() {
		return obSeqSell;
	}

	public void setObSeqSell(Integer obSeqSell) {
		this.obSeqSell = obSeqSell;
	}

	public Integer getObSeqBuy() {
		return obSeqBuy;
	}

	public void setObSeqBuy(Integer obSeqBuy) {
		this.obSeqBuy = obSeqBuy;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getTransactedType() {
		return transactedType;
	}
	public void setTransactedType(Integer transactedType) {
		this.transactedType = transactedType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}



	private Integer allOrOne;


	public Integer getAllOrOne() {
		return allOrOne;
	}
	public void setAllOrOne(Integer allOrOne) {
		this.allOrOne = allOrOne;
	}
	
	

}

