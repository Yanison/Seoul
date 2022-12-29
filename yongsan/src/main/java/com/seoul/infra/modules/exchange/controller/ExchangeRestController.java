package com.seoul.infra.modules.exchange.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seoul.infra.dto.Crypto;
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
	@Autowired
	ExchangeWSController exchWSC;
	
	
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
		
		@ResponseBody
		@RequestMapping("/transactionTable")
		public Order transactionTable(Order order)throws Exception{
			
			Order transactionTable = serviceExch.transactionTable(order);
			return transactionTable;
		}
		
		@ResponseBody
		@RequestMapping("/marketTable")
		public Order marketTable(Order order)throws Exception{
			
			Order marketTable = serviceExch.marketTable(order);
			return marketTable;
		}
		
		@ResponseBody
		@RequestMapping("/drawChart")
		public List<Order> drawChart(Order order)throws Exception{
			System.out.println("drawChart "+order.getShTime() +" 봉 차트");
			
			List<Order> drawChart = serviceExch.drawChart(order);
			return drawChart;
		}
		@ResponseBody
		@RequestMapping(value="getCryptoList")
		public List<Crypto> getCryptoList(Crypto crypto)throws Exception{
			
			List<Crypto> cryptoList = serviceExch.selectCryptoList(crypto);
			
			return cryptoList;
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
			System.out.println("ExchangeWSController.requestMatchingOrderBuy():: 매칭할 매수주문의 리스트를 불러옵니다. "+"\n" + "");
			serviceExch.orderMatchingBuy(selectBOBOne);
			return "requestMatchingOrderBuy";
		}
		@RequestMapping(path="requestMatchingOrderSell")
		public String requestMatchingOrderSell(Order order)throws Exception {
			System.out.println("ExchangeWSController.requestMatchingOrderBuy() ::최근 매도주문 하나를 불러옵니다. "+"\n" + "");
			Order selectSOBOne = serviceExch.selectSOBOne(order);;
			System.out.println("ExchangeWSController.requestMatchingOrderSell().orderMatchingBuy(selectSOBOne,selectBOB) :: 매도주문의 주문매칭을 시작합니다. "+"\n" + "");
			serviceExch.orderMatchingBuy(selectSOBOne);
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
		public ExchDTO userBalance(ExchDTO dto,Model model) throws Exception {
			
			ExchDTO userBalance = serviceExch.userBalance(dto);
//			double availableCash = serviceExch.selectAvailableCashBalance(dto.getMemberSeq());
//			userBalance.setAvailableCash(availableCash);
			
			return userBalance;
		}
		
		@ResponseBody
		@RequestMapping(value="requestMyOrders")
		public List<Order> selectMyOrder(Order order)throws Exception{
			
			List<Order> selectMyOrder = serviceExch.selectMyOrder(order);
			
			return selectMyOrder;
		}
		
		@ResponseBody
		@RequestMapping(value="requestMytransaction")
		public List<Order> selectMytransaction(Order order)throws Exception{
			
			List<Order> selectMytransaction = serviceExch.selectMytransaction(order);
			
			return selectMytransaction;
		}
		
		@RequestMapping(value="requestPenddingBalance")
		public HashMap<String, Object> requestPenddingBalance(Order order)throws Exception  {
			
			List<Order> selectMyOrder = serviceExch.selectMyOrder(order);
			HashMap<String,Object> data = new HashMap<String,Object>();
			double totalPrice = 0;
			double totalAmount = 0;
			
			int n = selectMyOrder.size();
			if(order.getBos() == 0) {
				
				for(int i = 0 ; i < n ; i ++) {
					totalPrice += selectMyOrder.get(i).getPrice();
					System.out.println(selectMyOrder.get(i).getPrice());
				}
				
				data.put("bos", order.getBos());
				data.put("pendingCash",totalPrice);
				System.out.println("availableBalance totalPrice :: " +  data);
				return data;
			}else {
				for(int i = 0 ; i < n ; i ++) {
					totalAmount += selectMyOrder.get(i).getObAmount();
					System.out.println(selectMyOrder.get(i).getObAmount());
				}
				data.put("bos", order.getBos());
				data.put("pendingAmount",totalAmount);
				System.out.println("availableBalance totalAmount :: " + data);
				return data;
			}
		}

	/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 * @@@@@@ # Ajax get userBalance end
	 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 */

}
