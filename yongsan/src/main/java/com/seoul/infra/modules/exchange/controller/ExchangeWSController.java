package com.seoul.infra.modules.exchange.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.seoul.infra.modules.exchange.ExchangeServiceImpl;
import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;

@JsonAutoDetect
@Controller
@RequestMapping(value="/exchange/")
public class ExchangeWSController {
	
	@Autowired
	ExchangeServiceImpl serviceExch;
	
	private SimpMessagingTemplate template;
	
	@Autowired
	public ExchangeWSController(SimpMessagingTemplate template) {
		this.template = template;
		System.out.println("ExchangeWSController :: ExchangeWSController");
	}
	
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # User
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	@RequestMapping(path="/updateUserBalanceDiv")
	public void updateUserBalanceDiv(int UserBalance) {
		this.template.convertAndSend("/topic/updateUserBalanceDiv", UserBalance);
		
		
	}
	
	
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP observeSubmittedBids star
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	@RequestMapping(path="/observeSubmittedBids")
	public String observeSubmittedBids(Order order)throws Exception{
		
		Order bobOne = serviceExch.selectBOBOne(order);
		System.out.println("ExchangeWSController.observeSubmittedBids().observeSubmittedBids(selectBOBOne) :: 연결된 웹소켓으로 불러온 매수주문 하나를 보냅니다. "+"\n" + "");
		System.out.println("ExchangeWSController.observeSubmittedAsks() :: 연결된 웹소켓에 메세지를 전달합니다.");
	    this.template.convertAndSend("/topic/observeSubmittedBids", bobOne);
	    
	    return "observeSubmittedBids";
	}
	@RequestMapping(path="/observeSubmittedAsks")
	public String observeSubmittedAsks(Order order)throws Exception{
		Order sobOne = serviceExch.selectSOBOne(order);
		System.out.println("ExchangeWSController.observeSubmittedAsks().observeSubmittedBids(selectSOBOne) :: 연결된 웹소켓으로 불러온 매도주문 하나를 보냅니다. "+"\n" + "");
		System.out.println("ExchangeWSController.observeSubmittedAsks() :: 연결된 웹소켓에 메세지를 전달합니다.");
	    this.template.convertAndSend("/topic/observeSubmittedAsks", sobOne);
	    return "observeSubmittedAsks";
	}
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP observeSubmittedBids end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP selectTransactonOne start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	
	public void transactionTable(Order trade)throws Exception {
		System.out.println("ExchangeWSController.transactionTable() :: 최근 거래내역 주문 하나를 불러옵니다.");
		Order transactionTable = serviceExch.transactionTable(trade);
//		System.out.println("OrderMatchingSystemDao.completeOrder :: transactionTable() 정보를 갱신합니다."+ "\n" +
//				"거래번호 :: "+ trade.getTransactionSeq()+ "\n" +
//				"거래 통화 :: " + trade.getCryptoSeq()+ "\n" +
//				"매수자 :: " +trade.getMemberSeqSell()+ "\n" +
//				"매수번호 :: " + trade.getObSeqBuy()+ "\n" +
//				"매도번호 :: " + trade.getObSeqSell()+ "\n" +
//				"매도자 :: " +trade.getMemberSeqBuy()+ "\n" +
//				"거래타입 :: "+ trade.getTransactedType()+ "\n" +
//				"거래수량 :: " + trade.getAmount()+ "\n" +
//				"거래가격 :: " + trade.getbPrice()+ "\n" +
//				"거래 시간 :: " + trade.getTimestamp()+ "\n" +
//				"상승&하락비율 :: " + trade.getRatio()
//				);
		this.template.convertAndSend("/topic/transactionTable", transactionTable);
		
	}
	public void marketTable(Order trade)throws Exception {
		System.out.println("ExchangeWSController.marketTable() :: 시장 시세정보를 가져옵니다.ㅁ");
		Order marketTable = serviceExch.marketTable(trade);
//		System.out.println("ExchangeServiceImpl.marketTable :: 화폐의 시세정보 테이블을 갱신합니다." + "\n" +
//				"코인 :: " +trade.getCryptoSeq()+ "\n" +
//				"최근 24시간 고가 :: " + trade.getHigh24()+ "\n" +
//				"최근 24시간 저가 :: " + trade.getLow24()+ "\n" +
//				"최근 24시간 거래량 :: " + trade.getVolume24()+ "\n" +
//				"최근 24시간 거래대금 :: " + trade.getCap24()+ "\n" +
//				"최근 거래가격 :: " + trade.getRecentPrice()+ "\n" +
//				"전일 종가 :: " + trade.getClosingPrice() + "\n" +
//				"전일대비 상승비율 :: "+ trade.getRatio()
//				);
		this.template.convertAndSend("/topic/marketTable", marketTable);
		
	}
	public void spread(Order trade)throws Exception {
		System.out.println("ExchangeWSController.spread() :: spread 정보를 갱신합니다.");
		List<Order> spread = serviceExch.spread(trade);
//		System.out.println("ExchangeServiceImpl.spread :: spread 정보를 가져옵니다." + "\n" +
//				"매수자 // 매수번호 :: " +trade.getMemberSeqBuy()+ " // " + trade.getObSeqBuy()+ "\n" +
//				"매수가격 :: "+trade.getbPrice()+ "\n" +
//				"매도자 // 매도번호 :: " + trade.getMemberSeqSell() + " // " + trade.getObSeqSell()+ "\n" +
//				"매도가격 :: "+trade.getsPrice()+ "\n" +
//				"spread :: "+trade.getSpread()
//			);
		this.template.convertAndSend("/topic/spread", spread);
		
	}
	
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP selectTransactonOne end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP submitOrder star
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
		
		@MessageMapping("submitBids")
		@SendTo("/topic/observeSubmittedBids")
		public Order submitBids(Order order)throws Exception {
			System.out.println("ExchangeController.submitBids :: 매수주문을 신청합니다."+"\n" + "");
			
			int submitBids = serviceExch.submitBids(order);
			System.out.println("submitBids :: "+submitBids );
			
			
			Order bobOne = serviceExch.selectBOBOne(order);
			System.out.println("ExchangeWSController.submitBids().observeSubmittedBids(selectBOBOne) :: 연결된 웹소켓으로 불러온 매수주문 하나를 보냅니다. "+"\n" + "");
			System.out.println("ExchangeWSController.submitBids() :: 연결된 웹소켓에 메세지를 전달합니다.");
			
			return bobOne;
		}
		
		@MessageMapping("submitAsks")
		@SendTo("/topic/observeSubmittedAsks")
		public Order submitAsks(Order order)throws Exception {
			
			System.out.println("ExchangeController.submitAsks :: 매도주문을 신청합니다."+"\n" + "");
			int submitAsks = serviceExch.submitAsks(order);
			System.out.println("submitAsks :: "+submitAsks );
			
			
			Order sobOne = serviceExch.selectSOBOne(order);
			System.out.println("ExchangeWSController.submitAsks().observeSubmittedBids(selectSOBOne) :: 연결된 웹소켓으로 불러온 매도주문 하나를 보냅니다. "+"\n" + "");
			System.out.println("ExchangeWSController.submitAsks() :: 연결된 웹소켓에 메세지를 전달합니다.");
			
			return sobOne;
		}
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP submitOrder end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP requestOrderMatching start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
		
		@MessageMapping("requestOrderMatching")
		public void requestOrderMatching(Order order)throws Exception {
			
			if(order.getBos() == 0) {
				System.out.println("ExchangeWSController.requestOrderMatching() BOS :: "+order.getBos()+" 매수주문매칭 요청");
				serviceExch.orderMatchingBuy(order, serviceExch.selectSOB(order));
			}else {
				System.out.println("ExchangeWSController.requestOrderMatching() BOS :: "+order.getBos()+" 매도주문매칭 요청");
				serviceExch.orderMatchingSell(order, serviceExch.selectBOB(order));
			}
			
		}
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP requestOrderMatching end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP update & delete OrderDivFromOB start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
		@RequestMapping(path="updateIncompleteOrderDivFromOB")
		public void updateIncompleteOrderDivFromOB(Order order) {
			System.out.println(
					"ExchangeWSController.updateIncompleteOrderDivFromOB :: 소화가 안된 주문을 갱신합니다."+"\n"
					+ "주문번호는, order.getObSeq() :: "+order.getObSeq()+"\n"
					+ "매수/매도는, order.getObSeq() :: "+order.getBos()+"\n"
					+ "주문 상태는, order.getOrderStatus() :: "+order.getOrderStatus()+"\n"
					+ "주문유형은 "+order.getOrderType() + " 입니다."+"\n" + "");
			this.template.convertAndSend("/topic/updateIncompleteOrderDivFromOB", order);
			
		}
		@RequestMapping(path="deleteCompleteOrderDivFromOB")
		public void deleteCompleteOrderDivFromOB(Order order) {
			System.out.println(
					"ExchangeWSController.deleteCompleteOrderDivFromOB :: 거래가 완료된 주문을 삭제합니다."+"\n"
					+ "주문번호는, order.getObSeq() :: "+order.getObSeq()+"\n"
					+ "매수/매도는, order.getObSeq() :: "+order.getBos()+"\n"
					+ "주문 상태는, order.getOrderStatus() :: "+order.getOrderStatus()+"\n"
					+ "주문유형은 "+order.getOrderType() + " 입니다."+"\n" + "");
			this.template.convertAndSend("/topic/deleteCompleteOrderDivFromOB", order);
			
		}
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP deleteCompleteOrderDivFromOB end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */

	
}
