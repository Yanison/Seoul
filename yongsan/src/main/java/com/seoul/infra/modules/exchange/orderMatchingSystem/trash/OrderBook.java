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
public class OrderBook implements OrderBookService{
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
	public synchronized boolean processLimitBuy(Order order, List<Order> sellOrders) throws Exception{
		System.out.println(
				"OrderBook 클래스의 processLimitBuy() 메소드 입니다. \n 매개변수는 다음과 같습니다." + "\n" + 
				"Order order(들어온 지정매수주문), List<Order> sellOrders(OrderBook의 매도주문 전체내역)" + "\n"+""
				);
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
			 * 
			 * 
			 */
			
			for(int i = 0 ; i < sellOrders.size() ; i ++) {
				System.out.println(
						"OrderBook의 전체 매도주문은" + n + "개 입니다." + "\n" +
						+n+"번 만큼 반복문을 수행합니다." + "\n"+""
						);
				
				final Order index0SO = sellOrders.get(i);
				System.out.println(
						"선언된 변수 index0SO는" + n + "번째 중 "+ i +"번째("+i+" index"+")" + "\n" +
						+i+"번째 매도주문 index0SO" + "\n"+""
						);
				/*
				 * 지정매수는 본인과 같은 가격만을 찾음
				 */
				System.out.println(
						"현재 들어온 지정매수 주문과 매칭되는 매도주문의 정보입니다." + "\n" +
						"order.getObSeq() 지정매수주문의 주문시퀀스 // index0SO.getObSeq() 매도주문의 주문시퀀스 ::" + order.getObSeq()+ " // "+ index0SO.getObSeq() + "\n"
						+"order.getPrice() 지정매수주문의 주문자시퀀스 // index0SO.getMemberSeq() 매도주문의 주문자 시퀀스::" + order.getMemberSeq()+ " // "+ index0SO.getMemberSeq() + "\n"
						+"order.getPrice() 지정매수주문의 주문가격// index0SO.getPrice() 매도주문자의 주문 가격::" + order.getPrice()+ " // "+ index0SO.getPrice() + "\n"
						+"order.getObAmount() 지정매수주문자의 주문수량// index0SO.getObAmount() 매도주문자의 주문수량::" + order.getObAmount()+ " // "+ index0SO.getObAmount() + "\n"+""
						);
				
				if(order.getPrice() == index0SO.getPrice()) {
					System.out.println(
							"order.getPrice() == index0SO.getPrice()"+ "\n"
							+ "지정매수주문의 주문가격과 매칭된 매도주문 가격과 같은 경우입니다. "+ "\n"
							+ "matchingProcessByAmount(order(지정매수주문),index0SO(매칭된 매도주문),sellOrders(전체매도주문 리스트))"+ "\n"
							+ "주문수량에 따른 주문매칭 메소드를 실행합니다." + "\n"+""
							);
					
					/*
					 * order < index0SO
					 * 일 경우 order의 orderStatus가 바뀜
					 * order > index0SO
					 * 일 경우 index0SO의 orderStatus가 바뀜
					 * order == index0SO
					 * 일 경우 order와 index0SO의 orderStatus가 바뀜
					 * 
					 * --> 주문 유형에 따라서 결과가 다름. 
					 * 
					 * if(order.getOrderStatus() != 0) 로 조건을 걸면?
					 * order < index0SO 일 경우에만 loop1을 탈출
					 * 
					 * if(index0SO.getOrderStatus() != 0) 로 조건을 걸면?
					 * order > index0SO 일 경우에만 loop1을 탈출
					 *  
					 * if(index0SO.getOrderStatus() != 0 || order.getOrderStatus() != 0) 로 조건을 걸면?
					 * 모든경우 loop1을 탈출
					 * 
					 * --> 주문 유형에 따라서 수량에 따른 주문매칭이 끝나는 시점이다르기 떄문에 모든 상황에 따른 대처를 해야함
					 * 
					 * 
					 */
					
					if(om.matchingProcessByAmount(order,index0SO) == false) {
						System.out.println("om.matchingProcessByAmount(order,index0SO) == false");
					}else if(omsDao.selectOrderStatus(order) == 0){
						System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ order.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(order));
						numOfwhile = 0;
						
					}
					numOfwhile += 1;
					System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ order.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(order));
				}else {
					System.out.println( 
							"지정매수주문의 주문가격과 매칭된 매도주문 가격과 같지않은 경우입니다. "+ "\n"
							+"이 구간에서는 실행할 메소드가 존재하지 않습니다." + "\n"+""
							);
				}
			}
		}else {
			System.out.println("매칭될 매도주문이 없습니다." + "\n"+"");
			return false;
		}
		return true;
	}
	
	/*
	 * 시장매수
	 */
	@Override
	public synchronized boolean processMarketBuy(Order order, List<Order> sellOrders)throws Exception {
		System.out.println(
				"OrderBook 클래스의 processMarketBuy() 메소드 입니다.  \n 매개변수는 다음과 같습니다." + "\n" + 
				"Order order(들어온 시장매수주문), List<Order> sellOrders(OrderBook의 매도주문 전체내역)" + "\n"+""
				);
		final int n = sellOrders.size();
		if(n != 0) {
			/*
			 * 시장주문은 주문 총가격 -= index0 주문의 수량 * 가격
			 * 이후 주문총가격 이 0이 될때 까지 주문을 계속 이행해야함. 
			 */
			
			for(int i = 0 ; i < sellOrders.size() ; i ++) {
				System.out.println(
						"OrderBook의 전체 매도주문은" + n + "개 입니다." + "\n" +
						+n+"번 만큼 반복문을 수행합니다." + "\n"+""
						);
				
				final Order index0SO = sellOrders.get(i);
				System.out.println(
						"선언된 변수 index0SO는" + n + "번째 중 "+ i +"번째("+i+" index"+")" + "\n" +
						+i+"번째 매도주문 index0SO" + "\n"+""
						);
				/*
				 * 시장매수는 본인보다 높거나 같은 가격만을 찾음
				 */
				System.out.println(
						"현재 들어온 지정매수 주문과 매칭되는 매도주문의 정보입니다." + "\n" +
						"order.getObSeq() 시장매수주문의 주문시퀀스 // index0SO.getObSeq() 매도주문의 주문시퀀스 ::" + order.getObSeq()+ " // "+ index0SO.getObSeq() + "\n"
						+"order.getPrice() 시장매수주문의 주문자시퀀스 // index0SO.getMemberSeq() 매도주문의 주문자 시퀀스::" + order.getMemberSeq()+ " // "+ index0SO.getMemberSeq() + "\n"
						+"order.getPrice() 시장매수주문의 주문가격// index0SO.getPrice() 매도주문자의 주문 가격::" + order.getPrice()+ " // "+ index0SO.getPrice() + "\n"
						+"order.getObAmount() 시장매수주문자의 주문수량// index0SO.getObAmount() 매도주문자의 주문수량::" + order.getObAmount()+ " // "+ index0SO.getObAmount() + "\n"+""
						);
				
				if(order.getPrice() >= index0SO.getPrice()) {
					System.out.println(
							"order.getPrice() >= index0SO.getPrice()"+ "\n"
							+ "시장매수주문의 주문가격이 매칭된 매도주문 가격과 같거나 큰 경우입니다. "+ "\n"
							+ "matchingProcessByAmount(order(시장매수주문),index0SO(매칭된 매도주문),sellOrders(전체매도주문 리스트))"+ "\n"
							+ "주문수량에 따른 주문매칭 메소드를 실행합니다." + "\n"+""
							);
					
					if(om.matchingProcessByAmount(order,index0SO) == false) {
						System.out.println("om.matchingProcessByAmount(order,index0SO) == false");
					}else if(omsDao.selectOrderStatus(order) == 0){
						System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ order.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(order));
						numOfwhile = 0;
						
					}
					numOfwhile += 1;
					System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ order.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(order));
					
				}else {
					System.out.println( 
							"지정매수주문의 주문가격과 매칭된 매도주문 가격과 같지않은 경우입니다. "+ "\n"
							+"이 구간에서는 실행할 메소드가 존재하지 않습니다." + "\n"+""
							);
				}
			}
		}else {
			System.out.println("매칭될 매도주문이 없습니다." + "\n"+"");
			return false;
		}
		return true;
	}
	
	/*
	 * 지정매도
	 */
	@Override
	public synchronized boolean processLimitSell(Order order,List<Order> buyOrders)throws Exception {
		System.out.println(
				"OrderBook 클래스의 processLimitSell() 메소드 입니다.  \n 매개변수는 다음과 같습니다." + "\n" + 
				"Order order(들어온 지정매도주문), List<Order> buyOrders(OrderBook의 매수주문 전체내역)" + "\n"+""
				);
		final int n = buyOrders.size();
		if( n != 0) {
				
				for(int i = 0 ; i < buyOrders.size() ; i ++) {
					System.out.println(
							"OrderBook.processLimitSell() :: 전체 매수주문은" + n + "개 입니다." + "\n" +
							+n+"번 만큼 반복문을 수행합니다." + "\n"+""
							);
					
					final Order index0BO = buyOrders.get(i);
					System.out.println(
							"OrderBook.processLimitSell() :: 선언된 변수 index0BO는" + n + "번째 중 "+ i +"번째("+i+" index"+")" + "\n" +
							+i+"번째 매도주문 index0BO" + "\n"+""
							);
					// 지정주문은 본인과 같은 가격만 찾음
					System.out.println(
							"OrderBook.processLimitSell() :: 현재 들어온 지정매도 주문과 매칭되는 매수주문의 정보입니다." + "\n" +
							"order.getObSeq() 지정매도주문의 주문시퀀스 // index0BO.getObSeq() 매수주문의 주문시퀀스 ::" + order.getObSeq()+ " // "+ index0BO.getObSeq() + "\n"
							+"order.getPrice() 지정매도주문의 주문자시퀀스 // index0BO.getMemberSeq() 매수주문의 주문자 시퀀스::" + order.getMemberSeq()+ " // "+ index0BO.getMemberSeq() + "\n"
							+"order.getPrice() 지정매도주문의 주문가격// index0BO.getPrice() 매수주문의 주문 가격::" + order.getPrice()+ " // "+ index0BO.getPrice() + "\n"
							+"order.getObAmount() 지정매도주문의 주문수량// index0BO.getObAmount() 매수주문의 주문수량::" + order.getObAmount()+ " // "+ index0BO.getObAmount() + "\n"+""
							);
					if(order.getPrice() == index0BO.getPrice()) {
						System.out.println(
								"OrderBook.processLimitSell() :: order.getPrice() >= index0BO.getPrice()"+ "\n"
								+ "지정매도주문의 주문가격이 매칭된 매수주문 가격과 같거나 큰 경우입니다. "+ "\n"
								+ "matchingProcessByAmount(order(지정매도주문),index0SO(매칭된 매수주문),sellOrders(전체매도주문 리스트))"+ "\n"
								+ "주문수량에 따른 주문매칭 메소드를 실행합니다." + "\n"+""
								);
						if(om.matchingProcessByAmount(order,index0BO)  == false) {
							System.out.println("om.matchingProcessByAmount(order,index0BO) == false");
						}else if(omsDao.selectOrderStatus(order) == 0){
							System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ order.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(order));
							numOfwhile = 0;
						}
						numOfwhile += 1;
						System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ order.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(order));
					}else {
						System.out.println( 
								"OrderBook.processLimitSell() :: 지정매도주문의 주문가격과 매칭된 매수주문 가격과 같지않은 경우입니다. "+ "\n"
								+"이 구간에서는 실행할 메소드가 존재하지 않습니다." + "\n"+""
								);
					}
				}
	    }else {
	    	System.out.println("OrderBook.processLimitSell() :: 매칭될 매수주문이 없습니다." + "\n"+"");
	    	return false;
	    }
		return true;
	}
	/*
	 * 시장매도
	 */
	@Override
	public synchronized boolean processMarketSell(Order order,List<Order> buyOrders)throws Exception {
		System.out.println(
				"OrderBook 클래스의 processMarketSell() 메소드 입니다.  \n 매개변수는 다음과 같습니다." + "\n" + 
				"Order order(들어온 시장매도주문), List<Order> buyOrders(OrderBook의 매수주문 전체내역)" + "\n"+""
				);
		final int n = buyOrders.size();
		
		if(n != 0) {
			/*
			 * 시장주문은 주문 총가격 -= index0 주문의 수량 * 가격
			 * 이후 주문총가격 이 0이 될때 까지 주문을 계속 이행해야함. 
			 */
			for(int i = 0 ; i < buyOrders.size() ; i ++) {
				System.out.println(
						"OrderBook.processMarketSell() ::  전체 매수주문은" + n + "개 입니다." + "\n" +
						+n+"번 만큼 반복문을 수행합니다." + "\n"+""
						);
				final Order index0BO = buyOrders.get(i);
				System.out.println(
						"OrderBook.processMarketSell() ::  선언된 변수 index0BO는" + n + "번째 중 "+ i +"번째("+i+" index"+")" + "\n" +
						+i+"번째 매도주문 index0BO" + "\n"+""
						);
				
				 //시장매수는 본인보다 높거라 같은 가격만을 찾음
				System.out.println(
						"OrderBook.processMarketSell() ::  현재 들어온 지정매도 주문과 매칭되는 매수주문의 정보입니다." + "\n" +
						"order.getObSeq() 시장매도주문의 주문시퀀스 // index0BO.getObSeq() 매수주문의 주문시퀀스 ::" + order.getObSeq()+ " // "+ index0BO.getObSeq() + "\n"
						+"order.getPrice() 시장매도주문의 주문자시퀀스 // index0BO.getMemberSeq() 매수주문의 주문자 시퀀스::" + order.getMemberSeq()+ " // "+ index0BO.getMemberSeq() + "\n"
						+"order.getPrice() 시장매도주문의 주문가격// index0BO.getPrice() 매수주문의 주문 가격::" + order.getPrice()+ " // "+ index0BO.getPrice() + "\n"
						+"order.getObAmount() 시장매도주문의 주문수량// index0BO.getObAmount() 매수주문의 주문수량::" + order.getObAmount()+ " // "+ index0BO.getObAmount() + "\n"+""
						);
				if(order.getPrice() <= index0BO.getPrice()) {
					System.out.println(
							"OrderBook.processMarketSell() ::  order.getPrice() <= index0BO.getPrice()"+ "\n"
							+ "시장매도주문의 주문가격이 매칭된 매수주문 가격과 같거나 큰 경우입니다. "+ "\n"
							+ "matchingProcessByAmount(order(지정매도주문),index0SO(매칭된 매수주문),sellOrders(전체매도주문 리스트))"+ "\n"
							+ "주문수량에 따른 주문매칭 메소드를 실행합니다." + "\n"+""
							);
					
					if(om.matchingProcessByAmount(order,index0BO)  == false) {
						System.out.println("om.matchingProcessByAmount(order,index0BO) == false");
					}else if(omsDao.selectOrderStatus(order) == 0){
						System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ order.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(order));
						numOfwhile = 0;
					}
					numOfwhile += 1;
					System.out.println(" OrderBook.processLimitSell() :: "+numOfwhile+" 번째 반복 주문번호 "+ order.getObSeq() +"  주문상태  ::" + omsDao.selectOrderStatus(order));
				}else {
					System.out.println( 
							"OrderBook.processMarketSell() ::  시장매도주문의 주문가격과 매칭된 매수주문 가격과 같지않은 경우입니다. "+ "\n"
							+"이 구간에서는 실행할 메소드가 존재하지 않습니다." + "\n"+""
							);
				}
			}
		}else {
			System.out.println("매칭될 매수주문이 없습니다." + "\n"+"");
			return false;
		}
		return true;
	}
}


