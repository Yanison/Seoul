package com.seoul.infra.modules.exchange.orderMatchingSystem.trash;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.modules.exchange.controller.ExchangeWSController;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderBookService;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatching3;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSystemDao;

@Service
public class OrderBook2 implements OrderBookService{
	@Autowired
	OrderMatchingSystemDao omsDao;
	@Autowired
	ExchangeWSController exchangeController;
	@Autowired
	OrderMatching3 om;
	
	private Integer numOfwhile = 0;
	private boolean endingSiganl;
	
	public boolean getEndingSiganl() {
		return endingSiganl;
	}
	public void setEndingSiganl(boolean endingSiganl) {
		this.endingSiganl = endingSiganl;
	}
	

	//spread값 구하기.
	@Override
	public double getSpread(Order order){
		final double buyOrderPrice = omsDao.selectBOB(order).get(0).getPrice();
		final double sellOrderPrice = omsDao.selectSOB(order).get(0).getPrice();
		System.out.println(
				"Orderbook.Java _ getSpread ::"
				+"Orderbook.Java _ buyOrderPrice :: " + buyOrderPrice + "\n"
				+"Orderbook.Java _ sellOrderPrice :: " + sellOrderPrice + "\n"
				+"Orderbook.Java _ buyOrderPrice - sellOrderPrice :: " + (buyOrderPrice - sellOrderPrice)
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
	@Override
	public synchronized boolean processLimitBuy(Order paramOrder) throws Exception{
		System.out.println(
				"OrderBook 클래스의 processLimitBuy() 메소드 입니다. \n 매개변수는 다음과 같습니다." + "\n" + 
				"Order order(들어온 지정매수주문), List<Order> sellOrders(OrderBook의 매도주문 전체내역)" + "\n"+""
				);
		System.out.println(
				"processLimitSell 신규주문 매수매도 :: "+paramOrder.getBos()+ "\n" +
				"신규주문 주문유형 :: "+paramOrder.getOrderType()+ "\n"
				);
		mathingLoop:
		while(true) {
			List<Order> sellOrders = omsDao.selectOBListForMatching(paramOrder);
			final int n = sellOrders.size();
			if(n != 0) {
				/*
				 * 수량 기준으로 주문 매칭을 끝내버리면 다음과 같은 문제가 발생
				 * 매개변수로 들어온 주문이 매칭된 주문수량보다 큰 경우 정상적으로 작동이 됨. 
				 * 
				 * 반대인경우, order 수량 < index0 수량 인경우는 매개변수로 들어온 order 수령은 차감이 되지 않기 때문 (index0 주문만 차감이 되기 때문에)
				 * 무한 반복이 발생하게 됨. 
				 * 
				 * 1. 해결할 방법은 주문 매칭에 매칭이 끝날 시점 피연산 주문의 수량이 저장되는 동시에 연산되는 주문의 수량을 0으로 바꿔야 함. 
				 * 2. 혹은 orderStatus가 pending(orderStatus == 0)이 아니게 될때 반복문을 끝나게 해야 함. 
				 */
				for(int i = 0 ; i < n ; i ++) {
					final Order index0SO = sellOrders.get(i);
					// 지정매수는 본인의 가격과 같은 주문들만 찾음. 
					if(paramOrder.getPrice() == index0SO.getPrice()) {
						/*
						 * 가격이 같은 주문과 매칭이 되면 다음 조건과 같이 수량에 따른 매칭 알고리즘이 진행
						 * 1. 들어온 주문이 매칭된 주문 수량보다 작은경우
						 * 2. 들어온 주문이 매칭된 주문 수량보다 큰 경우
						 * 3. 수량이 같은 경우
						 */
						if(om.matchingProcessByAmount(paramOrder,index0SO) == true) {
							System.out.println("om.matchingProcessByAmount(order,index0SO) == true");
							/*
							 * 알고리즘이 정상적으로 진행이 되면 true 값을 반환함.
							 * 만약 같은 조건의 가격의 매칭될 주문들이 신규주문 수량만큼 소화가 되지 못했다면
							 * 반복문을 다시 시작함. 
							 */
							if(omsDao.selectOrderStatus(paramOrder) == 3) {
								/*
								 * 매칭될 주문 리스트는 주문상태가 유효한 주문들만 불러옴. OrderStatus = 0 :: pending
								 * OrderStatus = 3 의 의미는 주문이 소화가 되었다는 의미.
								 */
								System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ paramOrder.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(paramOrder));
								numOfwhile = 0;
								break mathingLoop;
							}
						}
					}else {
						System.out.println( 
								"지정매수주문의 주문가격과 매칭된 매도주문 가격과 같지않은 경우입니다. "+ "\n"
								+"이 구간에서는 실행할 메소드가 존재하지 않습니다." + "\n"+""
								);
					}
					numOfwhile += 1;
					System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ paramOrder.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(paramOrder));
				}
			}else {
				System.out.println("매칭될 매도주문이 없습니다." + "\n"+"");
				break mathingLoop;
			}	
		}
		return true;
	}
	
	/*
	 * 시장매수
	 */
	@Override
	public synchronized boolean processMarketBuy(Order paramOrder)throws Exception {
		mathingLoop:
		while(true) {
			List<Order> sellOrders = omsDao.selectOBListForMatching(paramOrder);
			final int n = sellOrders.size();
			if(n != 0) {
				/*
				 * 시장주문은 주문 총가격 -= index0 주문의 수량 * 가격
				 * 이후 주문총가격 이 0이 될때 까지 주문을 계속 이행해야함. 
				 */
				for(int i = 0 ; i < sellOrders.size() ; i ++) {
					final Order index0SO = sellOrders.get(i);
					/*
					 * 시장매수는 본인보다 높거나 같은 가격만을 찾음
					 */
					if(paramOrder.getPrice() >= index0SO.getPrice()) {
						/*
						 * 가격이 크거나 같은 주문과 매칭이 되면 다음 조건과 같이 수량에 따른 매칭 알고리즘이 진행
						 * 1. 들어온 주문이 매칭된 주문 수량보다 작은경우
						 * 2. 들어온 주문이 매칭된 주문 수량보다 큰 경우
						 * 3. 수량이 같은 경우
						 */
						if(om.matchingProcessByAmount(paramOrder,index0SO) == true) {
							System.out.println("om.matchingProcessByAmount(order,index0SO) == true");
							/*
							 * 알고리즘이 정상적으로 진행이 되면 true 값을 반환함.
							 * 만약 같은 조건의 가격의 매칭될 주문들이 신규주문 수량만큼 소화가 되지 못했다면
							 * 반복문을 다시 시작함. 
							 */
							if(omsDao.selectOrderStatus(paramOrder) == 3) {
								/*
								 * 매칭될 주문 리스트는 주문상태가 유효한 주문들만 불러옴. OrderStatus = 0 :: pending
								 * OrderStatus = 3 의 의미는 주문이 소화가 되었다는 의미.
								 */
								System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ paramOrder.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(paramOrder));
								numOfwhile = 0;
								break mathingLoop;
							}
						}
					}else {
						System.out.println( 
								"지정매수주문의 주문가격과 매칭된 매도주문 가격과 같지않은 경우입니다. "+ "\n"
								+"이 구간에서는 실행할 메소드가 존재하지 않습니다." + "\n"+""
								);
					}
					numOfwhile += 1;
					System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ paramOrder.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(paramOrder));
				}
			}else {
				System.out.println("매칭될 매도주문이 없습니다." + "\n"+"");
				break mathingLoop;
			}	
		}
		return true;
	}
	
	/*
	 * 지정매도
	 */
	@Override
	public synchronized boolean processLimitSell(Order paramOrder)throws Exception {
		mathingLoop:
		while(true) {
			System.out.println(
					"processLimitSell 신규주문 매수매도 :: "+paramOrder.getBos()+ "\n" +
					"신규주문 주문유형 :: "+paramOrder.getOrderType()+ "\n"
					);
			List<Order> buyOrders = omsDao.selectOBListForMatching(paramOrder);
			final int n = buyOrders.size();
			if( n != 0) {
				for(int i = 0 ; i < buyOrders.size() ; i ++) {
					final Order index0BO = buyOrders.get(i);
					// 지정주문은 본인과 같은 가격만 찾음
					if(paramOrder.getPrice() == index0BO.getPrice()) {
						/*
						 * 가격이 같은 주문과 매칭이 되면 다음 조건과 같이 수량에 따른 매칭 알고리즘이 진행
						 * 1. 들어온 주문이 매칭된 주문 수량보다 작은경우
						 * 2. 들어온 주문이 매칭된 주문 수량보다 큰 경우
						 * 3. 수량이 같은 경우
						 */
						if(om.matchingProcessByAmount(paramOrder,index0BO)  == true) {

							System.out.println("om.matchingProcessByAmount(order,index0SO) == true");
							/*
							 * 알고리즘이 정상적으로 진행이 되면 true 값을 반환함.
							 * 만약 같은 조건의 가격의 매칭될 주문들이 신규주문 수량만큼 소화가 되지 못했다면
							 * 반복문을 다시 시작함. 
							 */
							if(omsDao.selectOrderStatus(paramOrder) == 3) {
								/*
								 * 매칭될 주문 리스트는 주문상태가 유효한 주문들만 불러옴. OrderStatus = 0 :: pending
								 * OrderStatus = 3 의 의미는 주문이 소화가 되었다는 의미.
								 */
								System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ paramOrder.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(paramOrder));
								numOfwhile = 0;
								break mathingLoop;
							}
						}
					}else {
						System.out.println( 
								"지정매수주문의 주문가격과 매칭된 매도주문 가격과 같지않은 경우입니다. "+ "\n"
								+"이 구간에서는 실행할 메소드가 존재하지 않습니다." + "\n"+""
								);
					}
					numOfwhile += 1;
					System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ paramOrder.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(paramOrder));
				}
		    }else {
		    	System.out.println("OrderBook.processLimitSell() :: 매칭될 매수주문이 없습니다." + "\n"+"");
		    	break mathingLoop;
		    }	
		}
		return true;
	}
	/*
	 * 시장매도
	 */
	@Override
	public synchronized boolean processMarketSell(Order paramOrder)throws Exception {
		mathingLoop:
		while(true) {
			List<Order> buyOrders = omsDao.selectOBListForMatching(paramOrder);
			final int n = buyOrders.size();
			if( n != 0) {
				for(int i = 0 ; i < buyOrders.size() ; i ++) {
					final Order index0BO = buyOrders.get(i);
					// 지정주문은 본인과 같은 가격만 찾음
					if(paramOrder.getPrice() >= index0BO.getPrice()) {
						/*
						 * 가격이 크거나 같은 주문과 매칭이 되면 다음 조건과 같이 수량에 따른 매칭 알고리즘이 진행
						 * 1. 들어온 주문이 매칭된 주문 수량보다 작은경우
						 * 2. 들어온 주문이 매칭된 주문 수량보다 큰 경우
						 * 3. 수량이 같은 경우
						 */
						if(om.matchingProcessByAmount(paramOrder,index0BO)  == true) {

							System.out.println("om.matchingProcessByAmount(order,index0SO) == true");
							/*
							 * 알고리즘이 정상적으로 진행이 되면 true 값을 반환함.
							 * 만약 같은 조건의 가격의 매칭될 주문들이 신규주문 수량만큼 소화가 되지 못했다면
							 * 반복문을 다시 시작함. 
							 */
							if(omsDao.selectOrderStatus(paramOrder) == 3) {
								/*
								 * 매칭될 주문 리스트는 주문상태가 유효한 주문들만 불러옴. OrderStatus = 0 :: pending
								 * OrderStatus = 3 의 의미는 주문이 소화가 되었다는 의미.
								 */
								System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ paramOrder.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(paramOrder));
								numOfwhile = 0;
								break mathingLoop;
							}
						}
					}else {
						System.out.println( 
								"지정매수주문의 주문가격과 매칭된 매도주문 가격과 같지않은 경우입니다. "+ "\n"
								+"이 구간에서는 실행할 메소드가 존재하지 않습니다." + "\n"+""
								);
					}
					numOfwhile += 1;
					System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ paramOrder.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(paramOrder));
				}
		    }else {
		    	System.out.println("OrderBook.processLimitSell() :: 매칭될 매수주문이 없습니다." + "\n"+"");
		    	break mathingLoop;
		    }	
		}
		return true;
	}
}


