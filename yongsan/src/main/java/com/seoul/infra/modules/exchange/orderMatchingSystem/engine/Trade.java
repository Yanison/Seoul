package com.seoul.infra.modules.exchange.orderMatchingSystem.engine;

public class Trade {
	
	private Integer buyMemberSeq;
	private Integer sellMemberSeq;
	private double obAmount;
	private double price;
	private Integer obSeq;
	private Integer obSeqSell;
	private Integer obSeqBuy;
	private Integer crpytoSeq;
	
	/**
	 * 거래 인스턴스 생성
	 * 매수주문일 경우 taker는 매도자 maker는 매수자
	 * 매도주문일 경우 taker는 매수자 maker는 매도자. 
	 * taker -> 주문 물량 받아주는 사람
	 * maker -> 주문 물량 던지는 사람.
	 */
	
	public Trade(
			final Integer buyMemberSeq,
			final Integer sellMemberSeq,
			final Integer obSeqBuy,
			final Integer obSeqSell,
			final double obAmount,
			final double price
			) {
		super();
		
		this.buyMemberSeq = buyMemberSeq;
		this.sellMemberSeq = sellMemberSeq;
		this.obAmount = obAmount;
		this.price = price;
		this.obSeqBuy = obSeqBuy;
		this.obSeqSell = obSeqSell;
	}
	
	

	public Integer getBuyMemberSeq() {
		return buyMemberSeq;
	}



	public void setBuyMemberSeq(Integer buyMemberSeq) {
		this.buyMemberSeq = buyMemberSeq;
	}



	public Integer getSellMemberSeq() {
		return sellMemberSeq;
	}



	public void setSellMemberSeq(Integer sellMemberSeq) {
		this.sellMemberSeq = sellMemberSeq;
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

	public void setObSeq(Integer obSeq) {
		this.obSeq = obSeq;
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

	public Integer getCrpytoSeq() {
		return crpytoSeq;
	}

	public void setCrpytoSeq(Integer crpytoSeq) {
		this.crpytoSeq = crpytoSeq;
	}
}
