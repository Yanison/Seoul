package com.seoul.infra.modules.exchange.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seoul.infra.modules.cryptogroup.CryptoGroupServiceImpl;
import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.ExchangeServiceImpl;
import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;
import com.seoul.infra.modules.wod.WodServiceImpl;

@RestController
@RequestMapping(value="/exchange/")
public class ExchangeRestController {
	@Autowired
	WodServiceImpl serviceWod;
	@Autowired
	ExchangeServiceImpl serviceExch;
	@Autowired
	HttpSession session;
	@Autowired
	CryptoGroupServiceImpl service;
	@Autowired
	ExchangeDao ExchDao;
	
	
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # Ajax get OBList start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
		@ResponseBody
		@RequestMapping("/selectBOB")
		public List<Order> selectBOB(Order order)throws Exception {
			System.out.println("ExchangeController.selectBOB() :: 매수주문의 모든 내역을 select합니다. \n" + "");
			List<Order> selectBOB = serviceExch.selectBOB(order);
			return selectBOB;
		}
		@ResponseBody
		@RequestMapping("/selectBOBOne")
		public Order selectBOBOne(Order order)throws Exception {
			System.out.println("ExchangeController.selectBOBOne() :: 매수주문내역 하나를 select합니다. "+"\n" + "");
			Order selectBOBOne = serviceExch.selectBOBOne(order);
			
		
			return selectBOBOne;
		}
		@ResponseBody
		@RequestMapping("/selectSOB")
		public List<Order> selectSOB(Order order)throws Exception {
			System.out.println("ExchangeController.selectSOB() :: 매도주문의 모든 내역을 select합니다. \n" + "");
			List<Order> selectSOB = serviceExch.selectSOB(order);
			
			return selectSOB;
		}
		// 주문내역 하나 가져옴
		@ResponseBody
		@RequestMapping("/selectSOBOne")
		public Order selectSOBOne(Order order)throws Exception {
			System.out.println("ExchangeController.selectSOBOne() :: 매도주문내역 하나를 select합니다. "+"\n" + "");
			Order selectSOBOne = serviceExch.selectSOBOne(order);
			return selectSOBOne;
		}
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # Ajax get OBList end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # Ajax get OBList end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
		@ResponseBody
		@RequestMapping("/selectTransacton")
		public List<Order> selectTransacton(Order order)throws Exception{
			
			List<Order> selectTransacton = serviceExch.selectTransacton(order);
			return selectTransacton;
		}
		
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # Ajax get OBList end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # Ajax requestOrderMatching star
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
		@RequestMapping(path="requestMatchingOrderBuy")
		public String requestMatchingOrderBuy(Order order)throws Exception {
			System.out.println("ExchangeWSController.requestMatchingOrderBuy() ::최근 매수주문 하나를 불러옵니다. "+"\n" + "");
			Order selectBOBOne = serviceExch.selectBOBOne(order);
			List<Order> selectSOB = serviceExch.selectSOB(order);
			System.out.println("ExchangeWSController.requestMatchingOrderBuy():: 매칭할 매수주문의 리스트를 불러옵니다. "+"\n" + "");
			serviceExch.orderMatchingBuy(selectBOBOne, selectSOB);
			return "requestMatchingOrderBuy";
		}
		@RequestMapping(path="requestMatchingOrderSell")
		public String requestMatchingOrderSell(Order order)throws Exception {
			System.out.println("ExchangeWSController.requestMatchingOrderBuy() ::최근 매도주문 하나를 불러옵니다. "+"\n" + "");
			Order selectSOBOne = serviceExch.selectSOBOne(order);
			System.out.println("ExchangeWSController.requestMatchingOrderBuy() ::매칭할 매도주문 리스트를 불러옵니다. "+"\n" + "");
			List<Order> selectBOB = serviceExch.selectBOB(order);
			System.out.println("ExchangeWSController.requestMatchingOrderSell().orderMatchingBuy(selectSOBOne,selectBOB) :: 매도주문의 주문매칭을 시작합니다. "+"\n" + "");
			serviceExch.orderMatchingBuy(selectSOBOne, selectBOB);
			return "requestMatchingOrderSell";
		}
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # Ajax requestOrderMatching end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # Ajax get userBalance start
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */
		
		@ResponseBody
		@RequestMapping(value="userBalance")
		public ExchDTO userBalance(@ModelAttribute ("vo") ExchDTO dto,Model model) throws Exception {
			
			ExchDTO userBalance = serviceExch.userBalance(dto);
			model.addAttribute("userBalance", userBalance);
			
			return userBalance;
		}

	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # Ajax get userBalance end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */

}