package com.seoul.infra.modules.exchange.orderMatchingSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seoul.infra.modules.exchange.controller.ExchangeWSController;

@Service
public class OrderMatching2 {
	
	@Autowired 
	OrderMatchingSystemDao omsDao;
	@Autowired
	ExchangeWSController exchangeController;
	
	
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ matchingProcessByAmount 수량에 따른 주문 매칭 로직. 
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	public boolean matchingProcessByAmount(Order paramOrder, Order index0Order,List<Order> orders) {
		
																										System.out.println("OrderMatching.machingProcessByAmount() 주문 수량에 따른 매칭을 시도합니다.");
		
		if(paramOrder.getObAmount() < index0Order.getObAmount()) {
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * @@@@@@ 매개변수로 들어온 주문의 수량이 매칭된 수량보다 작은경우. start
				 * 
				 */
																										System.out.println(
																												"paramOrder.getObAmount() < index0Order.getObAmount()"
																												+ "매개변수로 들어온 주문의 수량이 매칭된 주문의 수량보다 작은경우 입니다." 
																												+ (paramOrder.getObAmount() < index0Order.getObAmount()));
																										System.out.println(
																												"매개변수로 들어온 주문과 매칭될 주문의 정보입니다. " +"\n"
																												+ "paramOrder :: 매개변수로 들어온 주문 // index0Order :: 매칭될 주문" +"\n"
																												+ "paramOrder.getObAmount() :: " + paramOrder.getObAmount() +"\n"
																												+"paramOrder.getPrice() :: " + paramOrder.getPrice() +"\n"
																												+"paramOrder.getObSeq() :: " + paramOrder.getObSeq() +"\n"
																												+"paramOrder.getMemberSeq() :: " + paramOrder.getMemberSeq() +"\n"
																												+"index0Order.getObAmount() :: " + index0Order.getObAmount()+"\n"
																												+"index0Order.getPrice() :: " + index0Order.getPrice()+"\n"
																												+"index0Order.getObSeq() :: " + index0Order.getObSeq()+"\n"
																												+"index0Order.getMemberSeq() :: " + index0Order.getMemberSeq()+ "\n"+""
																												);
																										System.out.println("ProcessListMinusPram() :: 매개변수로 들어온 주문보다 매칭될 주문의 수량이 큰 경우의 매칭 알고리즘을 시작합니다."+ "\n"+"");
			
			
																										System.out.println("ProcessListMinusPram() :: 매개변수로 들어온 주문보다 매칭될 주문의 수량이 큰 경우 입니다.");
																									 	System.out.println(
																									 			"매개변수로 들어온 주문의 정보입니다." + "\n"
																									 			+"paramOrder.getObSeq() 주문번호 :: "+paramOrder.getObSeq() + "\n"
																							 					+"paramOrder.getOrderType() 주문유형::"+paramOrder.getOrderType() + "\n"
																									 			+"paramOrder.getBos() 매수/매도::"+paramOrder.getBos() + "\n"
																									 			+"paramOrder.getMemberSeq() 주문자번호::"+paramOrder.getMemberSeq() + "\n"
																									 			+"paramOrder.getCryptoSeq() 화폐종류::"+paramOrder.getCryptoSeq() + "\n"
																									 			+"paramOrder.getPrice() 주문 가격:: "+paramOrder.getPrice() + "\n"
																									 			+"paramOrder.getObAmount() 주문 수량:: "+paramOrder.getObAmount()+ "\n"+""
																									 			);
																									 	System.out.println(
																									 			"매칭될 주문의 정보입니다." + "\n"
																							 					+"index0Order.getObSeq() 주문번호 :: "+index0Order.getObSeq() + "\n"
																							 					+"index0Order.getOrderType() 주문유형::"+index0Order.getOrderType() + "\n"
																									 			+"index0Order.getBos() 매수/매도::"+index0Order.getBos() + "\n"
																									 			+"index0Order.getMemberSeq() 주문자번호::"+index0Order.getMemberSeq() + "\n"
																									 			+"index0Order.getCryptoSeq() 화폐종류::"+index0Order.getCryptoSeq() + "\n"
																									 			+"index0Order.getPrice() 주문 가격:: "+index0Order.getPrice() + "\n"
																									 			+"index0Order.getObAmount() 주문 수량:: "+index0Order.getObAmount()+ "\n"+""
																									 			);
																									 	System.out.println(
																									 			"매개변수로 들어온 주문보다 매칭된 주문의 수량이 큰경우 매칭된 주문이 소화되는 경우이기 때문에" + "\n"
																									 			+ "매칭된 주문의 수량에서 매개변수로 들어온 주문 수량을 차감합니다." + "\n"
																									 			+"매칭된주문 수량::"+index0Order.getObAmount() + " - " + "매개변수로 들어온 주문수량::"+paramOrder.getObAmount()+ "\n" 
																									 			+" = "+ (index0Order.getObAmount() - paramOrder.getObAmount())+ "\n"+""
																									 			);
			 	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 리스트로 불러온 주문[0 수량에서 매개변수로 들어온 주문 수량 뺌 
				 * index0Order - order
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				index0Order.setObAmount(index0Order.getObAmount() - paramOrder.getObAmount());
				
																										System.out.println(
																												"차감된 이후 주문 정보를 저장합니다."+ "\n"
																												+ "저장되는 주문 정보"+ "\n"
																												+"index0Order.getObSeq() 주문번호 :: "+index0Order.getObSeq() + "\n"
																							 					+"index0Order.getOrderType() 주문유형::"+index0Order.getOrderType() + "\n"
																									 			+"index0Order.getBos() 매수/매도::"+index0Order.getBos() + "\n"
																									 			+"index0Order.getMemberSeq() 주문자번호::"+index0Order.getMemberSeq() + "\n"
																									 			+"index0Order.getCryptoSeq() 화폐종류::"+index0Order.getCryptoSeq() + "\n"
																									 			+"index0Order.getPrice() 주문 가격:: "+index0Order.getPrice() + "\n"
																									 			+"@@ index0Order.getObAmount() 주문 수량:: "+index0Order.getObAmount()+ "\n"+""
																												);
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 차감된 이후 주문 정보를 저장.
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */																						
				index0Order.setObSeq(index0Order.getObSeq());
				omsDao.updtObAmount(index0Order);
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 거래내역에 성사된 주문 저장
				 * 매개변수로 들어온 주문이 매수주문(=0)일경우와 매도주문(=1)일 경우로 분기를 나누어서 거래된 가격 정보 저장.
				 * 저장될 주문은 수량에 의해서 소화된 주문임 
				 * (수량이 적은 주문 :: paramOrder)
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				if(paramOrder.getBos() == 0) {
																										System.out.println("OrderMatching.java_ if order.getOrderType() is 0 paramOrder.getOrderType():: " + paramOrder.getOrderType()+ "\n"
																												+ "소화된 주문 내역을 거래내역에 저장하는 구간입니다. 매개변수로 들어온 주문의 주문유형(매수/매도)이 0일경우 입니다. ::" + "\n"+""
																												);
								
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 매개변수로 들어온 주문이 매수일 경우 저장되는 주문
				 * 매수주문은 paramOrder
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				paramOrder.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
				paramOrder.setPrice(paramOrder.getPrice()); // 가격
					
																										System.out.println(
																									 			"OrderMatching :: 거래내역에 저장될 주문 정보입니다." + "\n"
																									 			+"paramOrder.setMemberSeqBuy() 매수자 번호 :: "+paramOrder.getMemberSeqBuy() + "\n"
																							 					+"paramOrder.setMemberSeqSell() 매도자 번호::"+index0Order.getMemberSeqSell() + "\n"
																									 			+"paramOrder.setObSeqBuy() 매수 번호::"+paramOrder.getObSeq() + "\n"
																									 			+"paramOrder.setObSeqSell() 매도 번호::"+index0Order.getObSeq() + "\n"
																									 			+"paramOrder.setObAmount() 거래 가격:: "+paramOrder.getPrice() + "\n"
																									 			+"paramOrder.setPrice() 거래 수량:: "+paramOrder.getObAmount()+ "\n"+""
																									 			);
																										System.out.println("거래내역에 주문을 저장합니다."+ "\n"+"");
					omsDao.insertTransactions(paramOrder);
				}else {
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 * 소화된 주문 내역을 거래내역에 저장하는 구간 start
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
																										System.out.println("OrderMatching.java_ if order.getOrderType() is 1 paramOrder.getOrderType():: " + paramOrder.getOrderType() + "\n"
																										+ "소화된 주문 내역을 거래내역에 저장하는 구간입니다. 매개변수로 들어온 주문의 주문유형(매수/매도)이 1일경우 입니다. ::" + "\n"+""
																										);
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 매개변수로 들어온 주문이 매도일 경우 저장되는 주문
				 * 매수주문은 index0Order
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				paramOrder.setMemberSeqBuy(index0Order.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(paramOrder.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
				paramOrder.setPrice(paramOrder.getPrice()); // 가격
					
					
																										System.out.println(
																									 			"OrderMatching.ProcessListMinusPram() :: 거래내역에 저장될 주문 정보입니다." + "\n"
																									 			+"paramOrder.setMemberSeqBuy() 매수자 번호 :: "+index0Order.getMemberSeqBuy() + "\n"
																							 					+"paramOrder.setMemberSeqSell() 매도자 번호::"+paramOrder.getMemberSeqSell() + "\n"
																									 			+"paramOrder.setObSeqBuy() 매수 번호::"+paramOrder.getObSeq() + "\n"
																									 			+"paramOrder.setObSeqSell() 매도 번호::"+index0Order.getObSeq() + "\n"
																									 			+"paramOrder.setObAmount() 거래 가격:: "+paramOrder.getPrice() + "\n"
																									 			+"paramOrder.setPrice() 거래 수량:: "+paramOrder.getObAmount()+ "\n"+""
																									 			);
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 거래내역에 주문을 저장
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */																						System.out.println("거래내역에 주문을 저장합니다."+ "\n"+""+ "\n"+"");
				omsDao.insertTransactions(paramOrder);
				
				/* 
				 * 소화된 주문 내역을 거래내역에 저장하는 구간 end
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				}
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 매개변수 주문 삭제
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */																					System.out.println("거래내역에 저장된 주문을 OrderBook 목록에서 삭제합니다. 삭제 대상인 주문은 매개변수로 들어온 주문입니다. :: paramOrder"+ "\n"+"");
				omsDao.completeOrder(paramOrder);
				
																										System.out.println("컨트롤러에 거래가 완료된 거래내역이 지워지도록 신호를 보냅니다. 매개변수 :: paramOrder"+ "\n"+"");
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 클라이언트에 OrderBook Div에서 삭제된 주문Div 삭제 요청. 
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				exchangeController.deleteCompleteOrderDivFromOB(paramOrder);
				
				
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 이후 웹소켓으로 오더북 div 삭제하고
				 * 주문자 잔고 깎아버리자 
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				
																										System.out.println("OrderMatching.ProcessListMinusPram() 을 종료합니다."+ "\n"+"");
			
				return false;
				/* 
				 * @@@@@@ 매개변수로 들어온 주문의 수량이 매칭된 수량보다 작은경우. end
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
			}else if(paramOrder.getObAmount() > index0Order.getObAmount()) {
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * @@@@@@ 매개변수로 들어온 주문의 수량이 매칭된 수량보다 큰경우.
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
																										System.out.println(
																												"paramOrder.getObAmount() > index0Order.getObAmount()"
																												+ "매개변수로 들어온 주문의 수량이 매칭된 주문의 수량보다 큰경우 입니다." 
																												+ (paramOrder.getObAmount() > index0Order.getObAmount()));
																										System.out.println(
																												"매개변수로 들어온 주문과 매칭될 주문의 정보입니다. " +"\n"
																														+ "paramOrder :: 매개변수로 들어온 주문 // index0Order :: 매칭될 주문"+"\n"
																														+ "paramOrder.getObAmount() :: " + paramOrder.getObAmount() +"\n"
																														+"paramOrder.getPrice() :: " + paramOrder.getPrice() +"\n"
																														+"paramOrder.getObSeq() :: " + paramOrder.getObSeq() +"\n"
																														+"paramOrder.getMemberSeq() :: " + paramOrder.getMemberSeq() +"\n"
																														+"index0Order.getObAmount() :: " + index0Order.getObAmount()+"\n"
																														+"index0Order.getPrice() :: " + index0Order.getPrice()+"\n"
																														+"index0Order.getObSeq() :: " + index0Order.getObSeq()+"\n"
																														+"index0Order.getMemberSeq() :: " + index0Order.getMemberSeq()+ "\n"+""
																												);
																										System.out.println("ProcessPramMinusList() :: 매개변수로 들어온 주문보다 매칭될 주문의 수량이 작은 경우의 매칭 알고리즘을 시작합니다."+ "\n"+"");		
																										
																										System.out.println(
																												"ProcessListMinusPram() :: 매개변수로 들어온 주문보다 매칭될 주문의 수량이 큰 경우 입니다.");
																									 	System.out.println(
																									 			"매개변수로 들어온 주문의 정보입니다." + "\n"
																									 			+"paramOrder.getObSeq() 주문번호 :: "+paramOrder.getObSeq() + "\n"
																												+"paramOrder.getOrderType() 주문유형::"+paramOrder.getOrderType() + "\n"
																									 			+"paramOrder.getBos() 매수/매도::"+paramOrder.getBos() + "\n"
																									 			+"paramOrder.getMemberSeq() 주문자번호::"+paramOrder.getMemberSeq() + "\n"
																									 			+"paramOrder.getCryptoSeq() 화폐종류::"+paramOrder.getCryptoSeq() + "\n"
																									 			+"paramOrder.getPrice() 주문 가격:: "+paramOrder.getPrice() + "\n"
																									 			+"paramOrder.getObAmount() 주문 수량:: "+paramOrder.getObAmount() + "\n"+""
																									 			);
																									 	System.out.println(
																									 			"매칭될 주문의 정보입니다." + "\n"
																												+"paramOrder.getObSeq() 주문번호 :: "+index0Order.getObSeq() + "\n"
																												+"paramOrder.getOrderType() 주문유형::"+index0Order.getOrderType() + "\n"
																									 			+"paramOrder.getBos() 매수/매도::"+index0Order.getBos() + "\n"
																									 			+"paramOrder.getMemberSeq() 주문자번호::"+index0Order.getMemberSeq() + "\n"
																									 			+"paramOrder.getCryptoSeq() 화폐종류::"+index0Order.getCryptoSeq() + "\n"
																									 			+"paramOrder.getPrice() 주문 가격:: "+index0Order.getPrice() + "\n"
																									 			+"paramOrder.getObAmount() 주문 수량:: "+index0Order.getObAmount() + "\n"+""
																									 			);
				
																									 	System.out.println(
																									 			"매개변수로 들어온 주문보다 매칭된 주문의 수량이 작은경우 매칭된 주문이 소화되는 경우이기 때문에" + "\n"
																									 			+ "매칭된 주문의 수량에서 매개변수로 들어온 주문 수량을 차감합니다." + "\n"
																									 			+"매칭된주문 수량::"+paramOrder.getObAmount() + " - " + "매개변수로 들어온 주문수량::"+index0Order.getObAmount() + "\n"
																									 			+" = "+ (paramOrder.getObAmount() - index0Order.getObAmount())+ "\n"+""
																									 			);
			 	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * @@@@@@ 매개변수로 들어온 주문 에서 리스트로 불러온 주문[0] 수량 뺌
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
			 	paramOrder.setObAmount(paramOrder.getObAmount() - index0Order.getObAmount());
																									 	System.out.println(
																												"차감된 이후 주문 정보를 저장합니다."+ "\n"
																												+ "저장되는 주문 정보"+ "\n"
																												+"paramOrder.getObSeq() 주문번호 :: "+paramOrder.getObSeq() + "\n"
																													+"paramOrder.getOrderType() 주문유형::"+paramOrder.getOrderType() + "\n"
																									 			+"paramOrder.getBos() 매수/매도::"+paramOrder.getBos() + "\n"
																									 			+"paramOrder.getMemberSeq() 주문자번호::"+paramOrder.getMemberSeq() + "\n"
																									 			+"paramOrder.getCryptoSeq() 화폐종류::"+paramOrder.getCryptoSeq() + "\n"
																									 			+"paramOrder.getPrice() 주문 가격:: "+paramOrder.getPrice() + "\n"
																									 			+"@@ paramOrder.getObAmount() 주문 수량:: "+paramOrder.getObAmount()+ "\n"+""
																												);
			 	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * @@@@@@ 차감된 이후 주문 정보를 저장
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
			 	paramOrder.setObSeq(orders.get(0).getObSeq());
				omsDao.updtObAmount(paramOrder);
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			 	 * 거래내역에 성사된 주문 저장
				 * 매개변수로 들어온 주문이 매수주문(=0)일경우와 매도주문(=1)일 경우로 분기를 나누어서 거래된 가격 정보 저장.
				 * 저장될 주문은 수량에 의해서 소화된 주문임 (index0Order)
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				if(paramOrder.getBos() == 0) {
																										System.out.println("OrderMatching.java_ if paramOrder.getOrderType() is 0 paramOrder.getOrderType():: " + paramOrder.getOrderType()+ "\n"
																										+ "소화된 주문 내역을 거래내역에 저장하는 구간입니다. 매개변수로 들어온 주문의 주문유형(매수/매도)이 0(매수)일경우 입니다. ::" + "\n"
																										);
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 매개변수로 들어온 주문이 매수일 경우
				 * 매수주문은 :: paramOrder
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				paramOrder.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
				paramOrder.setPrice(paramOrder.getPrice()); // 가격
					
																										System.out.println(
																									 			"OrderMatching :: 거래내역에 저장될 주문 정보입니다." + "\n"
																									 			+"paramOrder.setMemberSeqBuy() 매수자 번호 :: "+paramOrder.getMemberSeqBuy() + "\n"
																							 					+"paramOrder.setMemberSeqSell() 매도자 번호::"+paramOrder.getMemberSeqSell() + "\n"
																									 			+"paramOrder.setObSeqBuy() 매수 번호::"+paramOrder.getObSeq() + "\n"
																									 			+"paramOrder.setObSeqSell() 매도 번호::"+index0Order.getObSeq() + "\n"
																									 			+"paramOrder.setObAmount() 거래 가격:: "+paramOrder.getPrice() + "\n"
																									 			+"paramOrder.setPrice() 거래 수량:: "+paramOrder.getObAmount()+ "\n"+""
																									 			);
																										System.out.println("거래내역에 주문을 저장합니다."+ "\n"+"");
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 거래내역에 주문을 저장합니다
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				omsDao.insertTransactions(paramOrder);
				}else {
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 소화된 주문 내역을 거래내역에 저장하는 구간
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
																										System.out.println("OrderMatching.java_ if paramOrder.getOrderType() is 1 paramOrder.getOrderType():: " + paramOrder.getOrderType()+ "\n"
																												+ "소화된 주문 내역을 거래내역에 저장하는 구간입니다. 매개변수로 들어온 주문의 주문유형(매수/매도)이 1(매도)일경우 입니다. ::" + "\n"+""
																												);
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 매개변수로 들어온 주문이 매도주문일 경우
				 * 매수주문은 :: index0Order
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				paramOrder.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
				paramOrder.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
				paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
				paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
				paramOrder.setObAmount(index0Order.getObAmount()); // 소화될 수량
				paramOrder.setPrice(index0Order.getPrice()); // 가격
																										System.out.println(
																									 			"OrderMatching.ProcessPramMinusList() :: 거래내역에 저장될 주문 정보입니다." + "\n"
																									 			+"paramOrder.setMemberSeqBuy() 매수자 번호 :: "+paramOrder.getMemberSeqBuy() + "\n"
																							 					+"paramOrder.setMemberSeqSell() 매도자 번호::"+paramOrder.getMemberSeqSell() + "\n"
																									 			+"paramOrder.setObSeqBuy() 매수 번호::"+paramOrder.getObSeq() + "\n"
																									 			+"paramOrder.setObSeqSell() 매도 번호::"+index0Order.getObSeq() + "\n"
																									 			+"paramOrder.setObAmount() 거래 가격:: "+paramOrder.getPrice() + "\n"
																									 			+"paramOrder.setPrice() 거래 수량:: "+paramOrder.getObAmount()+ "\n"+""
																									 			);
																										System.out.println("거래내역에 주문을 저장합니다."+ "\n"+"");
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 거래내역에 주문을 저장
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				omsDao.insertTransactions(paramOrder);
				}
																										System.out.println("거래내역에 저장된 주문을 OrderBook 목록에서 삭제합니다. 삭제 대상인 주문은 매칭된 주문입니다. :: index0Order"+ "\n"+"");
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 매수주문 삭제
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				omsDao.completeOrder(index0Order);
																										System.out.println("컨트롤러에 거래가 완료된 거래내역이 지워지도록 신호를 보냅니다. 매개변수 :: index0Order"+ "\n"+"");
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 이후 웹소켓으로 오더북 div 삭제하고
				 * 주문자 잔고 깎아버리자
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				exchangeController.deleteCompleteOrderDivFromOB(index0Order);
				
																										System.out.println("OrderMatching.ProcessPramMinusList() 을 종료합니다."+ "\n"+"");
				
				
				
				
				
				
				
				
				return false;
			}else {
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 매개변수로 들어온 주문의 수량이 매칭된 주문의 수량과 같은경우
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
																										System.out.println(
																												"paramOrder.getObAmount() = index0Order.getObAmount()"
																												+ "매개변수로 들어온 주문의 수량이 매칭된 주문의 수량과 같은경우 입니다." 
																												+ (paramOrder.getObAmount() == index0Order.getObAmount()));
																										System.out.println(
																												"매개변수로 들어온 주문과 매칭될 주문의 정보입니다. " +"\n"
																														+ "paramOrder :: 매개변수로 들어온 주문 // index0Order :: 매칭될 주문"+"\n"
																														+ "paramOrder.getObAmount() :: " + paramOrder.getObAmount() +"\n"
																														+"paramOrder.getPrice() :: " + paramOrder.getPrice() +"\n"
																														+"paramOrder.getObSeq() :: " + paramOrder.getObSeq() +"\n"
																														+"paramOrder.getMemberSeq() :: " + paramOrder.getMemberSeq() +"\n"
																														+"index0Order.getObAmount() :: " + index0Order.getObAmount()+"\n"
																														+"index0Order.getPrice() :: " + index0Order.getPrice()+"\n"
																														+"index0Order.getObSeq() :: " + index0Order.getObSeq()+"\n"
																														+"index0Order.getMemberSeq() :: " + index0Order.getMemberSeq() + "\n"+""
																												);
																										System.out.println("processPramEqualList() :: 주문수량이 서로 같을 경우의 매칭 알고리즘을 시작합니다."+ "\n"+"");
																										System.out.println(
																												"processPramEqualList() :: 매개변수로 들어온 주문과 매칭될 주문의 수량이 같은 경우 입니다.");
																									 	System.out.println(
																									 			"매개변수로 들어온 주문의 정보입니다." + "\n"
																									 			+"paramOrder.getObSeq() 주문번호 :: "+paramOrder.getObSeq() + "\n"
																												+"paramOrder.getOrderType() 주문유형::"+paramOrder.getOrderType() + "\n"
																									 			+"paramOrder.getBos() 매수/매도::"+paramOrder.getBos() + "\n"
																									 			+"paramOrder.getMemberSeq() 주문자번호::"+paramOrder.getMemberSeq() + "\n"
																									 			+"paramOrder.getCryptoSeq() 화폐종류::"+paramOrder.getCryptoSeq() + "\n"
																									 			+"paramOrder.getPrice() 주문 가격:: "+paramOrder.getPrice() + "\n"
																									 			+"paramOrder.getObAmount() 주문 수량:: "+paramOrder.getObAmount() + "\n"+""
																									 			);
																									 	System.out.println(
																									 			"매칭될 주문의 정보입니다." + "\n"
																												+"index0Order.getObSeq() 주문번호 :: "+index0Order.getObSeq() + "\n"
																												+"index0Order.getOrderType() 주문유형::"+index0Order.getOrderType() + "\n"
																									 			+"index0Order.getBos() 매수/매도::"+index0Order.getBos() + "\n"
																									 			+"index0Order.getMemberSeq() 주문자번호::"+index0Order.getMemberSeq() + "\n"
																									 			+"paramOrder.getCryptoSeq() 화폐종류::"+index0Order.getCryptoSeq() + "\n"
																									 			+"index0Order.getPrice() 주문 가격:: "+index0Order.getPrice() + "\n"
																									 			+"index0Order.getObAmount() 주문 수량:: "+index0Order.getObAmount() + "\n"+""
																									 			);
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 매개변수로 들어온 주문과 리스트주문[0]의 거래 수량이 같을경우
				 * paramOrder = index0Order
				 * 서로 소화됨. 
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 매개변수로 들어온 주문 paramOrder이 매수주문(= 0), 매도주문(=1) 일 경우로 분기를 나눔
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				
				if(paramOrder.getBos() == 0) {
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 * 소화된 주문 내역을 거래내역에 저장하는 구간입니다. 매개변수로 들어온 주문의 주문유형(매수/매도)이 0(매수)일경우
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
																												System.out.println("OrderMatching.java_ if paramOrder.getOrderType() is 0 paramOrder.getOrderType():: " + paramOrder.getOrderType()+ "\n"
																														+ "소화된 주문 내역을 거래내역에 저장하는 구간입니다. 매개변수로 들어온 주문의 주문유형(매수/매도)이 0(매수)일경우 입니다. ::" + "\n"+""
																														);
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 *  paramOrder 가 매수주문일 경우
					 *  index0Order는 매도주문
					 *  주문정보 저장 setting
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					paramOrder.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
					paramOrder.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
					paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
					paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
					paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
					paramOrder.setPrice(paramOrder.getPrice()); // 가격
																												System.out.println(
																											 			"OrderMatching :: 거래내역에 저장될 주문 정보입니다." + "\n"
																											 			+"paramOrder.setMemberSeqBuy() 매수자 번호 :: "+paramOrder.getMemberSeqBuy() + "\n"
																									 					+"paramOrder.setMemberSeqSell() 매도자 번호::"+index0Order.getMemberSeqSell() + "\n"
																											 			+"paramOrder.setObSeqBuy() 매수 번호::"+paramOrder.getObSeq() + "\n"
																											 			+"paramOrder.setObSeqSell() 매도 번호::"+index0Order.getObSeq() + "\n"
																											 			+"paramOrder.setObAmount() 거래 가격:: "+paramOrder.getPrice() + "\n"
																											 			+"paramOrder.setPrice() 거래 수량:: "+paramOrder.getObAmount()+ "\n"+""
																											 			);
																												System.out.println("거래내역에 주문을 저장합니다.");
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 *  거래내역에 주문을 저장
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					omsDao.insertTransactions(paramOrder);
																												System.out.println("거래내역에 저장된 주문을 OrderBook 목록에서 삭제합니다. 삭제 대상인 주문은 매개변수로 들어온 주문입니다. :: paramOrder"+ "\n"+"");
					
					paramOrder.setObSeq(paramOrder.getObSeqBuy());
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 *  성사된 주문을 삭제
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					omsDao.completeOrder(paramOrder);
																												System.out.println("컨트롤러에 거래가 완료된 거래내역이 지워지도록 신호를 보냅니다. 매개변수 :: paramOrder"+ "\n"+"");
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 *  클라이언트에 주문을 삭제해달라고 요청
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					exchangeController.deleteCompleteOrderDivFromOB(paramOrder);
					
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 *  paramOrder 가 매수주문일 경우
					 *  index0Order는 매도주문
					 *  주문정보 저장 setting
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					paramOrder.setMemberSeqBuy(paramOrder.getMemberSeq()); //매수자
					paramOrder.setMemberSeqSell(index0Order.getMemberSeq());// 매도자
					paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
					paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
					paramOrder.setObAmount(index0Order.getObAmount()); // 소화될 수량
					paramOrder.setPrice(index0Order.getPrice()); // 가격
																												System.out.println(
																											 			"OrderMatching.processPramEqualList :: 거래내역에 저장될 주문 정보입니다." + "\n"
																											 			+"paramOrder.setMemberSeqBuy() 매수자 번호 :: "+paramOrder.getMemberSeqBuy() + "\n"
																									 					+"paramOrder.setMemberSeqSell() 매도자 번호::"+index0Order.getMemberSeqSell() + "\n"
																											 			+"paramOrder.setObSeqBuy() 매수 번호::"+paramOrder.getObSeq() + "\n"
																											 			+"paramOrder.setObSeqSell() 매도 번호::"+index0Order.getObSeq() + "\n"
																											 			+"paramOrder.setObAmount() 거래 가격:: "+index0Order.getPrice() + "\n"
																											 			+"paramOrder.setPrice() 거래 수량:: "+index0Order.getObAmount()+ "\n"+""
																											 			);
																												System.out.println("거래내역에 주문을 저장합니다."+ "\n"+"");
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 * 셋팅된 주문 정보를 거래내역에 저장
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					omsDao.insertTransactions(paramOrder);
																												System.out.println("거래내역에 저장된 주문을 OrderBook 목록에서 삭제합니다. 삭제 대상인 주문은 매개변수로 들어온 주문입니다. :: index0Order"+ "\n"+"");
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 * 주문 삭제하고 클라이언트에 삭제 요청
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					index0Order.setObSeq(paramOrder.getObSeqSell());
					omsDao.completeOrder(index0Order);
																												System.out.println("컨트롤러에 거래가 완료된 거래내역이 지워지도록 신호를 보냅니다. 매개변수 :: index0Order"+ "\n"+"");
					exchangeController.deleteCompleteOrderDivFromOB(index0Order);
					/*
					 * 이후 웹소켓으로 오더북 div 삭제하고
					 * 주문자 잔고 깎아버리자 
					 */
				}else {
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 * 소화된 주문 내역을 거래내역에 저장하는 구간
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
																												System.out.println("OrderMatching.java_ if paramOrder.getOrderType() is 1 paramOrder.getOrderType():: " + paramOrder.getOrderType()+ "\n"
																														+ "소화된 주문 내역을 거래내역에 저장하는 구간입니다. 매개변수로 들어온 주문의 주문유형(매수/매도)이 1(매도)일경우 입니다. ::" + "\n"+""
																														);
					/*  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 *  paramOrder 가 매도주문일 경우
					 *  index0Order가 매수주문 
					 *  주문정보 셋팅
					 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					paramOrder.setMemberSeqBuy(index0Order.getMemberSeq()); //매수자
					paramOrder.setMemberSeqSell(paramOrder.getMemberSeq());// 매도자
					paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
					paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
					paramOrder.setObAmount(paramOrder.getObAmount()); // 소화될 수량
					paramOrder.setPrice(paramOrder.getPrice()); // 가격
																														System.out.println(
																													 			"OrderMatching :: 거래내역에 저장될 주문 정보입니다." + "\n"
																											 					+"paramOrder.getCryptoSeq() 거래된 코인 종류:: "+paramOrder.getCryptoSeq() + "\n"
																																+"index0Order.getCryptoSeq() 거래된 코인 종류:: "+index0Order.getCryptoSeq() + "\n"
																													 			+"paramOrder.setMemberSeqBuy() 매수자 번호 :: "+index0Order.getMemberSeqBuy() + "\n"
																											 					+"paramOrder.setMemberSeqSell() 매도자 번호::"+paramOrder.getMemberSeqSell() + "\n"
																													 			+"paramOrder.setObSeqBuy() 매수 번호::"+paramOrder.getObSeq() + "\n"
																													 			+"paramOrder.setObSeqSell() 매도 번호::"+index0Order.getObSeq() + "\n"
																													 			+"paramOrder.setObAmount() 거래 가격:: "+paramOrder.getPrice() + "\n"
																													 			+"paramOrder.setPrice() 거래 수량:: "+paramOrder.getObAmount() + "\n"+""
																													 			);
																														System.out.println("거래내역에 저장된 주문을 OrderBook 목록에서 삭제합니다. 삭제 대상인 주문은 매개변수로 들어온 주문입니다. :: paramOrder"+ "\n"+"");
					/*  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 *  주문 삭제 요청후 클라이언트에도 삭제 요청
					 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					paramOrder.setObSeq(paramOrder.getObSeqBuy());
					omsDao.completeOrder(paramOrder);
																														System.out.println("컨트롤러에 거래가 완료된 거래내역이 지워지도록 신호를 보냅니다. 매개변수 :: paramOrder"+ "\n"+"");
					exchangeController.deleteCompleteOrderDivFromOB(paramOrder);
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 * 이후 웹소켓으로 오더북 div 삭제하고
					 * 주문자 잔고 깎아버리자 
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					
					
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 * index0Order 매도자
					 * paramOrder 매수자
					 * 주문정보 셋팅
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					paramOrder.setMemberSeqBuy(index0Order.getMemberSeq()); //매수자
					paramOrder.setMemberSeqSell(paramOrder.getMemberSeq());// 매도자
					paramOrder.setObSeqBuy(paramOrder.getObSeq());// 매수 OB
					paramOrder.setObSeqSell(index0Order.getObSeq()); // 매도 OB
					paramOrder.setObAmount(index0Order.getObAmount()); // 소화될 수량
					paramOrder.setPrice(index0Order.getPrice()); // 가격
																													System.out.println(
																												 			"OrderMatching :: 거래내역에 저장될 주문 정보입니다." + "\n"
																										 					+"paramOrder.getCryptoSeq() 거래된 코인 종류:: "+paramOrder.getCryptoSeq() + "\n"
																															+"index0Order.getCryptoSeq() 거래된 코인 종류:: "+index0Order.getCryptoSeq() + "\n"
																												 			+"paramOrder.setMemberSeqBuy() 매수자 번호 :: "+paramOrder.getMemberSeqBuy() + "\n"
																										 					+"paramOrder.setMemberSeqSell() 매도자 번호::"+index0Order.getMemberSeqSell() + "\n"
																												 			+"paramOrder.setObSeqBuy() 매수 번호::"+paramOrder.getObSeq() + "\n"
																												 			+"paramOrder.setObSeqSell() 매도 번호::"+index0Order.getObSeq() + "\n"
																												 			+"paramOrder.setObAmount() 거래 가격:: "+index0Order.getPrice() + "\n"
																												 			+"paramOrder.setPrice() 거래 수량:: "+index0Order.getObAmount()+ "\n"+""
																												 			);
																													System.out.println("거래내역에 주문을 저장합니다.");
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 * 거래내역에 주문을 저장
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					omsDao.insertTransactions(paramOrder);
					
																													System.out.println("거래내역에 저장된 주문을 OrderBook 목록에서 삭제합니다. 삭제 대상인 주문은 매개변수로 들어온 주문입니다. :: index0Order"+ "\n"+"");
					/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 * 거래된 주문을 삭제 요청후 클라이언트에도 삭제 요청함
					 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					 */
					index0Order.setObSeq(paramOrder.getObSeqSell());
					omsDao.completeOrder(index0Order);
																													System.out.println("컨트롤러에 거래가 완료된 거래내역이 지워지도록 신호를 보냅니다. 매개변수 :: index0Order"+ "\n"+"");
					exchangeController.deleteCompleteOrderDivFromOB(index0Order);
					
				}
				
				
				/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 * 이후 웹소켓으로 오더북 div 삭제하고
				 * 주문자 잔고 깎아버리자 
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				 */
				System.out.println("OrderMatching.processPramEqualList() 을 종료합니다."+ "\n"+"");
				return false;
			}
	}
	
	/*
	 * 이후 웹소켓으로 오더북 div 삭제하고
	 * 주문자 잔고 깎아버리자
	 * 
	 * 1. 주문자 잔고
	 */

	
}
