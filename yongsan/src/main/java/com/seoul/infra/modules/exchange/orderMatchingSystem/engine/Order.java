package com.seoul.infra.modules.exchange.orderMatchingSystem.engine;

import java.util.Date;

public class Order implements Comparable<Order> {
	
	public Order() {
		
	}
	
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
	 * 주문 타입 buy or sell
	 */
	private Integer orderType;
	
	/*
	 * 주문 시간
	 */
	private Date timestamp;
	
	/*
	 * 주문 생성 클래스
	 */
	
	public static class Builder{
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
		 * 주문 타입 buy or sell
		 */
		private Integer orderType;
		/*
		 * 주문 시간
		 */
		private Date timestamp;
		
		/*
		 * 주문 타입
		 * @param porderType
		 */
		
		public Builder(final Integer orderType) {
			this.obSeq = orderType;
		}
		
		/*
		 * 주문 수량
		 * 
		 * @param amount
		 * @return The builder
		 */
		public Builder withAmount(final double amount) {
			this.obAmount = amount;
			return this;
		}
		
		/*
		 * 주문 가격
		 * @param pPrice
		 * @return The Builder
		 */
		public Builder atPrice(final double price) {
			this.price = price;
			return this;
		}
		
		/*
		 * 주문 시간
		 * @param timestamp
		 * @return The builder
		 */
		public Builder withTimestamp(final Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		
		/*
		 * 주문 빌드
		 * 
		 * @return The new Order
		 */
		public Order build() {
			return new Order(
					this.obAmount
					,this.price
					,this.memberSeq
					,this.orderType
					,this.timestamp
					);
		}
	}
	/*
	 * 주문 인스턴스 생성하는 메소드.
	 * 
	 *  @param obAmount
     * @param Price
     * @param MemberSeq
     * @param OrderType
     * @param TimeStamp
	 */
	private Order(
				final double obAmount,
				final double price,
				final Integer memberSeq,
				final Integer orderType,
				final Date timestamp
			) {
		
		if (price <= 0) {
            throw new IllegalArgumentException(
                    "Order prices must be greater than zero");
        } else if (obAmount <= 0) {
            throw new IllegalArgumentException(
                    "Order amounts must be greater than zero");
        }
		
		this.obAmount = obAmount;
		this.price = price;
		this.memberSeq = memberSeq;
		this.orderType = orderType;
		this.timestamp = timestamp;
	}
	
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	@Override
	public int compareTo(final Order order) {
		if(Double.compare(this.getPrice(), order.getPrice()) == 0) {
			
			if(this.getTimestamp().before(order.getTimestamp())) {
				return -1;
			}else {
				return 1;
			}
		}else {
			return Double.compare(this.getPrice(), order.getPrice());
		}
	}
	

}

