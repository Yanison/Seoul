package com.seoul.infra.modules.exchange.orderMatchingSystem.engine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.dto.ExchDTO;

public class OrderBook {
	@Autowired
	ExchangeDao exchDao;
	
	/*
	 * List 매수주문
	 */
	private List<Order> buyOrders;
	/*
	 * List 매도주문
	 */
	private List<Order> sellOrders;
	
	
	private double transactedPrice;
	
	/*
	 * 주문 생성자
	 */
	public OrderBook(Order dto) {
		this.buyOrders = exchDao.selectBOB(dto);
		this.sellOrders = exchDao.selectSOB(dto);
	}
	
	/*
	 * 마켓에 잔여주문 수량이 남기전에 주문을 진행시키고 발생된 거래를 반환함
	 * 지정주문 
	 * 	- taker 매도주문자 주문 물량을 받아주는 사람. 
	 * 	- maker 매수주문자
	 * @param order insert된 주문 (submittedOrder) 가 파라미터로 들어옴. 
	 * @return trades
	 */
	public synchronized List<Trade> processLimitBuy(final Order order){
		/*
		 * 잔여 주문을 담을 빈 List 생성
		 */
		final ArrayList<Trade> trades = new ArrayList<>();
		/*
		 * 매도주문의 수량을 불러옴
		 */
		final int n = this.sellOrders.size();
		final Order recentSellOrder = exchDao.selectSOBOne(order);
		/*
		 * 주문 수가 0이 아니면 매칭 알고리즘 시작. 
		 */
		if(n != 0) {
			/**
			 * 최근 매도주문 하나가 들어온 매수주문 가격보다 작을경우
			 */
			if(this.sellOrders.get(n-1).getPrice() <= order.getPrice()) {
				/*
				 * 매칭할 주문 탐색
				 */
				while(true) {
					/*
					 * 0번째 인덱스의 매수주문을 불러옴 -> 가장 최근 주문부터. 
					 */
					final Order sellOrder = exchDao.selectSOBOne(order);
					/*
					 * 매도 주문가가 매수 주문가보다 높으면 해당 메소드 중지. 
					 */
					if(sellOrder.getPrice() > order.getPrice()) {
						break;
					}
					/*
					 * 걸러진 모든 주문을 trades List 에 담음
					 */
					if(sellOrder.getObAmount() >= order.getObAmount()) {
						//매수주문이 매도수량에 의해 다 소화가 되었으면 거래자와 매수주문 수량과 매도주문 가격을 저장. 
						trades.add(new Trade(order.getMemberSeq(), sellOrder.getMemberSeq(),
								order.getObAmount(),sellOrder.getPrice()));
						/*
						 * 매도주문 수량에 매수주문 수량을 뺀 값을 저장.  
						 */
						sellOrder.setObAmount(sellOrder.getObAmount() - order.getObAmount());
						if(sellOrder.getObAmount() == 0) {
							removeSellOrder(order);
						}
						/*
						 * 최종 거래가격 저장
						 */
						return trades;
						
					}
				}
			}
		}
	}
	
	private synchronized void removeSellOrder(Order order) {
        exchDao.delObseq(order);
    }

	public double getTransactedPrice() {
		return transactedPrice;
	}

	public void setTransactedPrice(double transactedPrice) {
		this.transactedPrice = transactedPrice;
	}
	
	
	
	
	
	
}
