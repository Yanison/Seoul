package com.seoul.infra.modules.exchange.orderMatchingSystem.engine;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSystemDao;

public class OrderBook {
	@Autowired
	OrderMatchingSystemDao omsDao;
	
	private synchronized void updtObAmount(Order order) {
		omsDao.updtObAmount(order);
	}
	private synchronized void completeOrder(Order order) {
        omsDao.completeOrder(order);
    }
	private synchronized void insertTransactions(Trade trade) {
		omsDao.insertTransactions(trade);
	}
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
				
				final Order index0BO = buyOrders.get(0);
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
				final Order index0BO = buyOrders.get(0);
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
	
	
	private void machingProcessByAmount(Order pramOrder, Order index0Order,List<Order> orders) {
		if(pramOrder.getObAmount() < index0Order.getObAmount()) {
			System.out.println("machingProcessByAmount :: pramOrder.getObAmount() < index0Order.getObAmount() :: " + (pramOrder.getObAmount() < index0Order.getObAmount()));
			ProcessListMinusPram(pramOrder,index0Order,orders);
		}else if(pramOrder.getObAmount() > index0Order.getObAmount()) {
			System.out.println("machingProcessByAmount ::pramOrder.getObAmount() > index0Order.getObAmount() :: " + (pramOrder.getObAmount() > index0Order.getObAmount()));
			ProcessPramMinusList(pramOrder,index0Order,orders);
		}else {
			System.out.println("machingProcessByAmount :: pramOrder.getObAmount() == index0Order.getObAmount() :: " + (pramOrder.getObAmount() == index0Order.getObAmount()));
			processPramEqualList(pramOrder,index0Order,orders);
		}
	}

	private void ProcessListMinusPram(Order pramOrder, Order index0Order,List<Order> orders) {
		/*
		 * 매개변수로 들어온 주문보다 리스트로 들어온 주문[0]의 수량이 큰 경우
		 * paramOrder < inde0ORder
		 */
		 	System.out.print(
		 			"order info" + "\n"
		 			+"order.getObSeq() :: "+pramOrder.getObSeq() + "\n"
		 			+"order.getPrice() :: "+pramOrder.getPrice() + "\n"
		 			+"order.getObAmount() :: "+pramOrder.getObAmount() + "\n"
		 			+"order.getCryptoSeq() ::"+pramOrder.getCryptoSeq() + "\n"
		 			+"order.getMemberSeq() ::"+pramOrder.getMemberSeq() + "\n"
		 			+"order.getOrderType() ::"+pramOrder.getOrderType() + "\n"
		 			);
		 	System.out.print(
		 			"index0Order info" + "\n"
		 			+"index0Order.getObSeq() :: "+index0Order.getObSeq() + "\n"
		 			+"index0Order.getPrice() :: "+index0Order.getPrice() + "\n"
		 			+"index0Order.getObAmount() :: "+index0Order.getObAmount() + "\n"
		 			+"index0Order.getCryptoSeq() ::"+index0Order.getCryptoSeq() + "\n"
		 			+"index0Order.getMemberSeq() ::"+index0Order.getMemberSeq() + "\n"
		 			+"index0Order.getOrderType() ::"+index0Order.getOrderType() + "\n"
		 			);
			/*
			 * 리스트로 불러온 주문[0 수량에서 매개변수로 들어온 주문 수량 뺌 
			 * index0Order - order
			 */
			index0Order.setObAmount(index0Order.getObAmount() - pramOrder.getObAmount());
			//연산 후 남은 수량 업데이트 
			index0Order.setObSeq(orders.get(0).getObSeq());
			updtObAmount(index0Order);
			/*
			 * 거래내역에 성사된 주문 저장
			 * 매개변수로 들어온 주문이 매수주문(=0)일경우와 매도주문(=1)일 경우로 분기를 나누어서 거래된 가격 정보 저장.
			 * 저장될 주문은 수량에 의해서 소화된 주문임 
			 * (수량이 적은 주문 :: pramOrder)
			 */
			if(pramOrder.getOrderType() == 0) {
				System.out.println("if order.getOrderType() is 0 ::" + pramOrder.getOrderType());
				/*
				 * 매개변수로 들어온 주문이 매수일 경우 저장되는 주문
				 * 매수주문은 pramOrder
				 */
				Trade transactedOrder = 
						new Trade(
								pramOrder.getMemberSeq() // 매수자
								,index0Order.getMemberSeq() // 매도자 
								,pramOrder.getObSeq() //매수 OB
								,index0Order.getObSeq() // 매도 OB
								,pramOrder.getObAmount() // 수량
								,pramOrder.getPrice() //가격
								);
				insertTransactions(transactedOrder);
			}else {
				System.out.println("if order.getOrderType() is 1 ::" + pramOrder.getOrderType());
				/*
				 * 매개변수로 들어온 주문이 매도일 경우 저장되는 주문
				 * 매수주문은 index0Order
				 */
				Trade transactedOrder = 
						new Trade(
								index0Order.getMemberSeq() // 매수자
								,pramOrder.getMemberSeq() // 매도자
								,pramOrder.getObSeq() //매수 OB
								,index0Order.getObSeq() // 매도 OB
								,pramOrder.getObAmount() // 수량
								,pramOrder.getPrice() //가격
								);
				insertTransactions(transactedOrder);
			}
			// 매개변수 주문 삭제
			completeOrder(pramOrder);
			
			/*
			 * 이후 웹소켓으로 오더북 div 삭제하고
			 * 주문자 잔고 깎아버리자 
			 */
	}
	
	
	
	private void ProcessPramMinusList(Order pramOrder, Order index0Order, List<Order> orders) {
		/*
		 * paramOrder > inde0ORder
		 * 매개변수로 들어온 주문보다 리스트로 들어온 주문[0]의 수량이 작은경우
		 */
		System.out.print(
	 			"order info" + "\n"
	 			+"order.getObSeq() :: "+pramOrder.getObSeq() + "\n"
	 			+"order.getPrice() :: "+pramOrder.getPrice() + "\n"
	 			+"order.getObAmount() :: "+pramOrder.getObAmount() + "\n"
	 			+"order.getCryptoSeq() ::"+pramOrder.getCryptoSeq() + "\n"
	 			+"order.getMemberSeq() ::"+pramOrder.getMemberSeq() + "\n"
	 			+"order.getOrderType() ::"+pramOrder.getOrderType() + "\n"
	 			);
	 	System.out.print(
	 			"index0Order info" + "\n"
	 			+"index0Order.getObSeq() :: "+index0Order.getObSeq() + "\n"
	 			+"index0Order.getPrice() :: "+index0Order.getPrice() + "\n"
	 			+"index0Order.getObAmount() :: "+index0Order.getObAmount() + "\n"
	 			+"index0Order.getCryptoSeq() ::"+index0Order.getCryptoSeq() + "\n"
	 			+"index0Order.getMemberSeq() ::"+index0Order.getMemberSeq() + "\n"
	 			+"index0Order.getOrderType() ::"+index0Order.getOrderType() + "\n"
	 			);
		/*
		 * 매개변수로 들어온 주문 에서 리스트로 불러온 주문[0] 수량 뺌
		 */
	 	pramOrder.setObAmount(pramOrder.getObAmount() - index0Order.getObAmount());
	 	pramOrder.setObSeq(orders.get(0).getObSeq());
		updtObAmount(pramOrder);
		/*
		 * 거래내역에 성사된 주문 저장
		 * 매개변수로 들어온 주문이 매수주문(=0)일경우와 매도주문(=1)일 경우로 분기를 나누어서 거래된 가격 정보 저장.
		 * 저장될 주문은 수량에 의해서 소화된 주문임 (index0Order)
		 */
		if(pramOrder.getOrderType() == 0) {
			/*
			 * 매개변수로 들어온 주문이 매수일 경우
			 * 매수주문은 :: pramOrder
			 */
			Trade transactedOrder = 
					new Trade(
							pramOrder.getMemberSeq() // 매수자
							,index0Order.getMemberSeq() // 매도자 
							,pramOrder.getObSeq() //매수 OB
							,index0Order.getObSeq() // 매도 OB
							,pramOrder.getObAmount() // 소화될 수량
							,pramOrder.getPrice() //가격
							);
			insertTransactions(transactedOrder);
		}else {
			/*
			 * 매개변수로 들어온 주문이 매도주문일 경우
			 * 매수주문은 :: index0Order
			 */
			Trade transactedOrder = 
					new Trade(
							index0Order.getMemberSeq() // 매수자
							,pramOrder.getMemberSeq() // 매도자
							,index0Order.getObSeq() //매수 OB
							,pramOrder.getObSeq() // 매도 OB
							,index0Order.getObAmount() // 소화될 수량
							,index0Order.getPrice() //가격
							);
			insertTransactions(transactedOrder);
		}
		
		/*
		 * 매수주문 삭제
		 */
		completeOrder(index0Order);
		/*
		 * 이후 웹소켓으로 오더북 div 삭제하고
		 * 주문자 잔고 깎아버리자 
		 */
	}
	
	private void processPramEqualList(Order pramOrder, Order index0Order, List<Order> orders) {
		/*
		 * 매개변수로 들어온 주문과 리스트주문[0]의 거래 수량이 같을경우
		 * pramOrder = index0Order
		 * 서로 소화됨. 
		 */
		
		/*
		 * 매개변수로 들어온 주문 pramOrder이 매수주문(= 0), 매도주문(=1) 일 경우로 분기를 나눔
		 */
		
		if(pramOrder.getOrderType() == 0) {
			/*
			 *  pramOrder 가 매수주문일 경우
			 *  index0Order는 매도주문 
			 */
			Trade transactedOrderSell = 
					new Trade(
							pramOrder.getMemberSeq() // 매수자
							,index0Order.getMemberSeq() // 매도자 
							,pramOrder.getObSeq() //매수 OB
							,index0Order.getObSeq() // 매도 OB
							,pramOrder.getObAmount() // 소화될 수량
							,pramOrder.getPrice() //가격
							);
			insertTransactions(transactedOrderSell);
			pramOrder.setObSeq(transactedOrderSell.getObSeqBuy());
			completeOrder(pramOrder);
			/*
			 * 이후 웹소켓으로 오더북 div 삭제하고
			 * 주문자 잔고 깎아버리자 
			 */
			
			Trade transactedOrderBuy = 
					new Trade(pramOrder.getMemberSeq() // 매수자
							,index0Order.getMemberSeq() // 매도자
							,pramOrder.getObSeq() // 매수 OB
							,index0Order.getObSeq() // 매도 OB
							,index0Order.getObAmount() //소화된 수량
							,index0Order.getPrice() //가격
							);
			
			insertTransactions(transactedOrderBuy);
			index0Order.setObSeq(transactedOrderBuy.getObSeqSell());
			completeOrder(index0Order);
			
		}else {
			/*
			 *  pramOrder 가 매도주문일 경우
			 *  index0Order가 매수주문 
			 */
			Trade transactedOrderSell = 
					new Trade(
							index0Order.getMemberSeq() // 매수자
							,pramOrder.getMemberSeq() // 매도자 
							,pramOrder.getObSeq() //매수 OB
							,index0Order.getObSeq() // 매도 OB
							,pramOrder.getObAmount() // 소화될 수량
							,pramOrder.getPrice() //가격
							);
			insertTransactions(transactedOrderSell);
			pramOrder.setObSeq(transactedOrderSell.getObSeqBuy());
			completeOrder(pramOrder);
			/*
			 * 이후 웹소켓으로 오더북 div 삭제하고
			 * 주문자 잔고 깎아버리자 
			 */
			
			Trade transactedOrderBuy = 
					new Trade(index0Order.getMemberSeq() // 매수자
							,pramOrder.getMemberSeq() // 매도자
							,pramOrder.getObSeq() // 매수 OB
							,index0Order.getObSeq() // 매도 OB
							,index0Order.getObAmount() //소화된 수량
							,index0Order.getPrice() //가격
							);
			
			insertTransactions(transactedOrderBuy);
			index0Order.setObSeq(transactedOrderBuy.getObSeqSell());
			completeOrder(index0Order);
		}
		
		
		/*
		 * 이후 웹소켓으로 오더북 div 삭제하고
		 * 주문자 잔고 깎아버리자 
		 */
	}
}
