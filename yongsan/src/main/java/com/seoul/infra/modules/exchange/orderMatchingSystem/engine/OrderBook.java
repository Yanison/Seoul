package com.seoul.infra.modules.exchange.orderMatchingSystem.engine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.orderMatchingSystem.OrderMatchingSystemDao;

public class OrderBook {
	@Autowired
	OrderMatchingSystemDao omsDao;
	
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

	public synchronized void processLimitBuy(final Order order){
		final List<Order> sellOrders = omsDao.selectSOB(order);
		final int n = sellOrders.size();
		if(n != 0) {
			for(int i = 0 ; i <= sellOrders.size() ; i ++) {
				
				final Order index0SO = sellOrders.get(0);
				
				if(order.getPrice() == index0SO.getPrice()) {
					/*
					 * 최근 매도주문의 수량이 들어온 매수주문 수량보다 많은경우
					 */
					if(order.getObAmount() < index0SO.getObAmount()) {
						/*
						 * 매도주문 수량에서 매수주문 수량만큼 빼고 저장
						 */
						index0SO.setObAmount(index0SO.getObAmount() - order.getObAmount());
						index0SO.setObSeq(sellOrders.get(0).getObSeq());
						updtObAmount(index0SO);
						/*
						 * 지워지는 쪽 정보 저장 --> 매수주문 지워짐
						 * 매수주문의 가격과 수량
						 */
						Trade transactedOrder = 
								new Trade(
										order.getMemberSeq()
										,index0SO.getMemberSeq()
										,order.getObSeq()
										,index0SO.getObSeq()
										,order.getObAmount() // 수량
										,order.getPrice() //가격
										);
						/*
						 * 거래내역에 성사된 주문 저장
						 */
						insertTransactions(transactedOrder);
						/*
						 * 매수주문 삭제
						 */
						delObseq(order);
						/*
						 * 이후 웹소켓으로 오더북 div 삭제하고
						 * 주문자 잔고 깎아버리자 
						 */
						
					}else if(order.getObAmount() > index0SO.getObAmount()) {
						
						order.setObAmount(order.getObAmount() - index0SO.getObAmount());
						order.setObSeq(order.getObSeq());
						updtObAmount(order);
						
						/*
						 * 지워지는 쪽 거래 저장 -> 매도 주문이 지워짐
						 * 매도 주문의 거래 수량과 가격
						 */
						Trade transactedOrder = 
								new Trade(order.getMemberSeq()
										,index0SO.getMemberSeq()
										,order.getObSeq()
										,index0SO.getObSeq()
										,index0SO.getObAmount() //수량
										,index0SO.getPrice() //가격
										);
						/*
						 * 거래내역에 성사된 주문 저장
						 */
						insertTransactions(transactedOrder);
						/*
						 * 매도주문 삭제
						 */
						delObseq(index0SO);
						/*
						 * 이후 웹소켓으로 오더북 div 삭제하고
						 * 주문자 잔고 깎아버리자 
						 */
					}else { 
						/*
						 * 거래 수량이 같을경우
						 * 둘다 지워짐 
						 */
						Trade transactedOrderBuy = 
								new Trade(order.getMemberSeq()
										,index0SO.getMemberSeq()
										,order.getObSeq()
										,index0SO.getObSeq()
										,index0SO.getObAmount() //수량
										,index0SO.getPrice() //가격
										);
						/*
						 * 거래내역에 성사된 주문 저장
						 */
						insertTransactions(transactedOrderBuy);
						/*
						 * 매도주문 삭제
						 */
						order.setObSeq(transactedOrderBuy.getObSeqBuy());
						delObseq(order);
						/*
						 * 이후 웹소켓으로 오더북 div 삭제하고
						 * 주문자 잔고 깎아버리자 
						 */
						
						delObseq(order);
						Trade transactedOrderSell = 
								new Trade(order.getMemberSeq()
										,index0SO.getMemberSeq()
										,order.getObSeq()
										,index0SO.getObSeq()
										,index0SO.getObAmount() //수량
										,index0SO.getPrice() //가격
										);
						/*
						 * 거래내역에 성사된 주문 저장
						 */
						insertTransactions(transactedOrderSell);
						/*
						 * 매도주문 삭제
						 */
						index0SO.setObSeq(transactedOrderSell.getObSeqSell());
						delObseq(index0SO);
						/*
						 * 이후 웹소켓으로 오더북 div 삭제하고
						 * 주문자 잔고 깎아버리자 
						 */
					}
				}
			}
		}else {
			System.out.println("no Orders");
		}
	}

		
	/*
	 * 파라미터는 
	 * Order SellOrders = omsDao.selectSOB(order);
	 */
	public synchronized void processLimitSell(final Order order) {
		
		final List<Order> buyOrders = omsDao.selectBOB(order);
		final int n = buyOrders.size();
		if( n != 0) {
			for(int i = 0 ; i <= buyOrders.size() ; i ++) {
				
				final Order index0BO = buyOrders.get(0);
				/*
				 * 들어온 매도주문과 매칭된 매수주문의 가격이 같을때
				 */
				if(order.getPrice() == index0BO.getPrice()) {
					/*
					 * 들어온 매도주문보다 매칭된 매수주문의 수량이 클 때
					 */
					if(order.getObAmount() < index0BO.getObAmount()) {
						index0BO.setObAmount(index0BO.getObAmount() - order.getObAmount());
						index0BO.setObSeq(index0BO.getObSeq());
						updtObAmount(index0BO);
						
						/*
						 * 지워지는 쪽이 
						 */
						Trade transactedOrder = 
								new Trade(
										index0BO.getMemberSeq()
										,order.getMemberSeq()
										,order.getObSeq()
										,index0BO.getObSeq()
										,order.getObAmount() // 수량
										,order.getPrice() //가격
										);
						insertTransactions(transactedOrder);
						delObseq(order);
					}
					
				}
			}
		}
	}
	
	
	
	
	private synchronized void updtObAmount(Order order) {
		omsDao.updtObAmount(order);
	}
	
	private synchronized void delObseq(Order order) {
        omsDao.delObseq(order);
    }
	
	private synchronized void insertTransactions(Trade trade) {
		omsDao.insertTransactions(trade);
	}
}
