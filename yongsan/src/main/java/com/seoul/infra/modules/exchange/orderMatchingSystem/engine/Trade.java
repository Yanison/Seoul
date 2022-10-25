package com.seoul.infra.modules.exchange.orderMatchingSystem.engine;

public class Trade {
	
	private Integer takerMemberSeq;
	private Integer makerMemberSeq;
	private double obAmount;
	private double price;
	private Integer obSeq;
	private Integer crpytoSeq;
	
	/**
	 * 거래 인스턴스 생성
	 * 매수주문일 경우 taker는 매도자 maker는 매수자
	 * 매도주문일 경우 taker는 매수자 maker는 매도자. 
	 * taker -> 주문 물량 받아주는 사람
	 * maker -> 주문 물량 던지는 사람.
	 */
	
	public Trade(
			final Integer takerMemberSeq,
			final Integer makerMemberSeq,
			final double obAmount,
			final double price
			) {
		super();
		
		this.takerMemberSeq = takerMemberSeq;
		this.makerMemberSeq = makerMemberSeq;
		this.obAmount = obAmount;
		this.price = price;
	}
	
	public Integer getTakerMemberSeq() {
		return takerMemberSeq;
	}

	public void setTakerMemberSeq(final Integer takerMemberSeq) {
		this.takerMemberSeq = takerMemberSeq;
	}

	public Integer getMakerMemberSeq() {
		return makerMemberSeq;
	}

	public void setMakerMemberSeq(final Integer makerMemberSeq) {
		this.makerMemberSeq = makerMemberSeq;
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

	public Integer getCrpytoSeq() {
		return crpytoSeq;
	}

	public void setCrpytoSeq(Integer crpytoSeq) {
		this.crpytoSeq = crpytoSeq;
	}
	
}
