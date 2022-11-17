package com.seoul.infra.modules.exchange.controller;

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
	public void selectTransactonOne(Order order) {
		order.setAllOrOne(1);
		System.out.println("ExchangeWSController.selectTransactonOne() :: 연결된 웹소켓에 메세지를 전달합니다.");
		 this.template.convertAndSend("/topic/selectTransactonOne", order);
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
					"ExchangeWSController.updateIncompleteOrderDivFromOB :: 거래가 완료된 주문을 삭제합니다."+"\n"
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
