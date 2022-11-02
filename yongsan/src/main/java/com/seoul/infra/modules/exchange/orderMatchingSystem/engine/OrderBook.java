package com.seoul.infra.modules.exchange.orderMatchingSystem.engine;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSystemDao;

public class OrderBook extends OrderMatching{
	@Autowired
	OrderMatchingSystemDao omsDao;

	//spread값 구하기.
	public double getSpread(Order order){
		final double buyOrderPrice = omsDao.selectBOB(order).get(0).getPrice();
		final double sellOrderPrice = omsDao.selectSOB(order).get(0).getPrice();
		System.out.print(
				"getSpread ::"
				+"buyOrderPrice :: " + buyOrderPrice + "\n"
				+"sellOrderPrice :: " + sellOrderPrice + "\n"
				+"buyOrderPrice - sellOrderPrice :: " + (buyOrderPrice - sellOrderPrice)
				);
		return sellOrderPrice - buyOrderPrice;
	}
	
	
	/*
	 *  주문 매칭 알고리즘이 진행되는 시점은 주문이 들어올때가 아니고
	 *  주문 들어오고 나서 최근 주문들을 하나씩 불러오면서 진행하는게 어떨까?
	 *  주문이 들어오는 즉시 실행할 방법.. 
	 *  
	 *  주문이 들어올 때 마다 실행하는걸로 하자. 
	 *  주문이 들어옴
	 *  매칭시스템 가동, 매수주문이면 시장매도,시장매수 주문매칭 프로세스 진행
	 *  반대 주문은 그 반대.
	 *  
	 *  Order sellOrder = omsDao.selectSOBOne(order);
	 *  processLimitBuy(recentSellOrder)
	 * 
	 */

	//지정매수
	public synchronized void processLimitBuy(final Order order){
		final List<Order> sellOrders = omsDao.selectSOB(order);
		final int n = sellOrders.size();
		if(n != 0) {
			for(int i = 0 ; i <= sellOrders.size() ; i ++) {
				
				final Order index0SO = sellOrders.get(0);
				System.out.println("sellOrders index zero :: "+ index0SO);
				if(order.getPrice() == index0SO.getPrice()) {
					machingProcessByAmount(order,index0SO,sellOrders);
				}
			}
		}else {
			System.out.println("processLimitBuy :: there is no orders to match");
		}
	}
	
	/*
	 * 시장매수
	 */
	public synchronized void processMarketBuy(final Order order) {
		final List<Order> sellOrders = omsDao.selectSOB(order);
		final int n = sellOrders.size();
		
		if(n != 0) {
			for(int i = 0 ; i <= sellOrders.size() ; i ++) {
				final Order index0SO = sellOrders.get(0);
				System.out.println("sellOrders index zero :: "+ index0SO.getObSeq());
				/*
				 * 시장매수는 본인보다 높거라 같은 가격만을 찾음
				 */
				if(order.getPrice() >= index0SO.getPrice()) {
					machingProcessByAmount(order,index0SO,sellOrders);
				}else {
					System.out.println("Is order.getPrice() <= index0SO.getPrice() true? :: " + (order.getPrice() >= index0SO.getPrice()));
				}
			}
		}else {
			System.out.println("processMarketBuy :: there is no orders to match");
		}
	}
	/*
	 * 지정매도
	 */
	public synchronized void processLimitSell(final Order order) {
		
		final List<Order> buyOrders = omsDao.selectBOB(order);
		final int n = buyOrders.size();
		if( n != 0) {
			for(int i = 0 ; i <= buyOrders.size() ; i ++) {
				
				final Order index0BO = buyOrders.get(i);
				System.out.println("buyOrders index zero :: "+ index0BO);
				// 지정주문은 본인과 같은 가격만 찾음
				if(order.getPrice() == index0BO.getPrice()) {
					machingProcessByAmount(order,index0BO,buyOrders);
				}	
			}
	    }else {
	    	System.out.println("processLimitSell :: there is no orders to match");
	    }
	}
	/*
	 * 시장매도
	 */
	public synchronized void processMarketSell(final Order order) {
		final List<Order> buyOrders = omsDao.selectBOB(order);
		final int n = buyOrders.size();
		
		if(n != 0) {
			for(int i = 0 ; i <= buyOrders.size() ; i ++) {
				final Order index0BO = buyOrders.get(i);
				System.out.println("sellOrders index zero :: "+ index0BO.getObSeq());
				
				 //시장매수는 본인보다 높거라 같은 가격만을 찾음
				if(order.getPrice() <= index0BO.getPrice()) {
					machingProcessByAmount(order,index0BO,buyOrders);
				}else {
					System.out.println("Is order.getPrice() <= index0SO.getPrice() true? :: " + (order.getPrice() >= index0BO.getPrice()));
				}
			}
		}else {
			System.out.println("processMarketSell :: there is no orders to match");
		}
	}
}


