package com.seoul.infra.modules.exchange.orderMatchingSystem.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSystemDao;

public class OrderMatching {
	
	@Autowired
	OrderMatchingSystemDao omsDao;
	
	//주문이 완전히 소화되지 않은 경우 잔여 수량 저장
	private synchronized void updtObAmount(Order order) {
		omsDao.updtObAmount(order);
	}
	//주문이 소화될 경우 주문 상태를 변경
	private synchronized void completeOrder(Order order) {
        omsDao.completeOrder(order);
    }
	//주문이 소화될 경우 거래내역에 저장
	private synchronized void insertTransactions(Trade trade) {
		omsDao.insertTransactions(trade);
	}
	
	public void machingProcessByAmount(Order pramOrder, Order index0Order,List<Order> orders) {
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

	/*
	 * @Order pramOrder :: 주문들어온 매수 혹은 매도
	 * @List<Order> orders :: select orders
	 * @Order index0Order ::orders의 0번째 인덱스
	 */
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
	
	/*
	 * @Order pramOrder :: 주문들어온 매수 혹은 매도
	 * @List<Order> orders :: select orders
	 * @Order index0Order ::orders의 0번째 인덱스
	 */
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
	
	/*
	 * @Order pramOrder :: 주문들어온 매수 혹은 매도
	 * @List<Order> orders :: select orders
	 * @Order index0Order ::orders의 0번째 인덱스
	 */
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
	
	/*
	 * 이후 웹소켓으로 오더북 div 삭제하고
	 * 주문자 잔고 깎아버리자
	 * 
	 * 1. 주문자 잔고
	 */

	
}
