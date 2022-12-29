package com.seoul.infra.modules.exchange.controller;


import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.seoul.infra.dto.Crypto;
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
	@MessageMapping(value="/updateUserBalanceDiv")
	public boolean updateUserBalanceDiv(double UserBalance) {

		
		System.out.println("updateUserBalanceDiv UserBalance :: "  +  UserBalance);
		this.template.convertAndSend("/topic/updateUserBalanceDiv", UserBalance);
		return true;
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
	
	public boolean transactionTable(Order trade)throws Exception {
		System.out.println("ExchangeWSController.transactionTable() :: 최근 거래내역 주문 하나를 불러옵니다.");
		Order transactionTable = serviceExch.transactionTable(trade);
		if(transactionTable != null) {
			this.template.convertAndSend("/topic/transactionTable", transactionTable);
		}
		return true;
	}
	
	public boolean marketTable(Order trade)throws Exception {
		System.out.println("ExchangeWSController.marketTable() :: 시장 시세정보를 가져옵니다.ㅁ");
		Order marketTable = serviceExch.marketTable(trade);
		if(marketTable != null) {
			this.template.convertAndSend("/topic/marketTable", marketTable);
		}
		selectCryptoList(new Crypto());
		return true;
	}
	
	@MessageMapping(value="/selectListCryptoTrend/{cryptoSeq}")
	public void selectListCryptoTrend(Order order,@DestinationVariable Integer cryptoSeq)throws Exception {
		if(cryptoSeq != null) {
			order.setCryptoSeq(cryptoSeq);
		}
		List<Order> selectListCryptoTrend = serviceExch.selectListCryptoTrend(order);
		this.template.convertAndSend("/topic/selectListCryptoTrend", selectListCryptoTrend);
	}
	@MessageMapping(value="/selectCryptoList")
	public void selectCryptoList(Crypto crypto)throws Exception {
		List<Crypto> selectCryptoList = serviceExch.selectCryptoList(crypto);
		this.template.convertAndSend("/topic/selectCryptoList", selectCryptoList);
	}
	
	
	@MessageMapping(value="/drawChart/addCandle")
	public void addCandle(Order order)throws Exception {
		Order dataSet = new Order();
		dataSet.setShTime(order.getShTime());
		dataSet.setCryptoSeq(order.getCryptoSeq());
		dataSet.setLimit(1);
		List<Order> addCandle = serviceExch.drawChart(dataSet);
		
		System.out.println("addCandle " + order.getShTime() + "봉 캔들 추가");
	
		this.template.convertAndSend("/topic/drawChart/addCandle", addCandle);
	}
	
	public boolean spread(Order trade)throws Exception {
		System.out.println("ExchangeWSController.spread() :: spread 정보를 갱신합니다.");
		List<Order> spread = serviceExch.spread(trade);
		this.template.convertAndSend("/topic/spread", spread);
		return true;
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
			
			
			availableBalance(order);
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
			
			availableBalance(order);
			return sobOne;
		}
		
		
		public void availableBalance(Order order)throws Exception {
			
			ExchDTO userBalance = new ExchDTO();
			userBalance.setShSelectOne(0);
			userBalance.setCryptoSeq(order.getCryptoSeq());
			userBalance.setMemberSeq(order.getMemberSeq());
			userBalance = serviceExch.userBalance(userBalance);
			userBalance.setAvailableCash(serviceExch.selectAvailableCashBalance(order.getMemberSeq()));
			
			this.template.convertAndSend("/topic/availableBalance", userBalance);
		}
		
		@MessageMapping(value="cancelOrder")
		public void cancelOrder(Order order)throws Exception {
			int obSeq = order.getObSeq();
			
			int delObseq = serviceExch.delObseq(obSeq);
			if(delObseq == 1) {
				this.template.convertAndSend("/topic/cancelOrder", obSeq);
				availableBalance(order);
			}
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
		public boolean requestOrderMatching(Order order)throws Exception {
			
			if(order.getBos() == 0) {
				System.out.println("ExchangeWSController.requestOrderMatching() BOS :: "+order.getBos()+" 매수주문매칭 요청");
				serviceExch.orderMatchingBuy(order);
			}else {
				System.out.println("ExchangeWSController.requestOrderMatching() BOS :: "+order.getBos()+" 매도주문매칭 요청");
				serviceExch.orderMatchingSell(order);
			}
			return true;
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
		public boolean updateIncompleteOrderDivFromOB(Order order) {
			System.out.println(
					"ExchangeWSController.updateIncompleteOrderDivFromOB :: 소화가 안된 주문을 갱신합니다."+"\n"
					+ "주문번호는, order.getObSeq() :: "+order.getObSeq()+"\n"
					+ "매수/매도는, order.getObSeq() :: "+order.getBos()+"\n"
					+ "주문 상태는, order.getOrderStatus() :: "+order.getOrderStatus()+"\n"
					+ "주문유형은 "+order.getOrderType() + " 입니다."+"\n" + "");
			this.template.convertAndSend("/topic/updateIncompleteOrderDivFromOB", order);
			return true;
		}
		@RequestMapping(path="deleteCompleteOrderDivFromOB")
		public boolean deleteCompleteOrderDivFromOB(Order order,String msg) {
			System.out.println(
					"ExchangeWSController.deleteCompleteOrderDivFromOB :: 거래가 완료된 주문을 삭제합니다."+"\n"
					+ "주문번호는, order.getObSeq() :: "+order.getObSeq()+"\n"
					+ "매수/매도는, order.getObSeq() :: "+order.getBos()+"\n"
					+ "주문 상태는, order.getOrderStatus() :: "+order.getOrderStatus()+"\n"
					+ "주문유형은 "+order.getOrderType() + " 입니다."+"\n" + "");
			if(msg == "iep") {
				this.template.convertAndSend("/topic/deleteCompleteOrderDivFromOBiep", order);
			}else {
				this.template.convertAndSend("/topic/deleteCompleteOrderDivFromOB", order);
			}
			return true;
		}
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # STOMP deleteCompleteOrderDivFromOB end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */

	
}
