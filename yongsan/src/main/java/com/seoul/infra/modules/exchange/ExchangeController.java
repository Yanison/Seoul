package com.seoul.infra.modules.exchange;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.seoul.infra.dto.Crypto;
import com.seoul.infra.modules.cryptogroup.CryptoGroupServiceImpl;
import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.orderMatchingSystem.engine.Order;
import com.seoul.infra.modules.wod.WodDTO;
import com.seoul.infra.modules.wod.WodServiceImpl;

@Controller
@RequestMapping(value = "/exchange/")
public class ExchangeController {
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
	
	
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # util Functions start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
	
	
	public void getBasicList(Model model
							,@ModelAttribute("vo") Crypto vo
							,@ModelAttribute("voW") WodDTO voW
							,@ModelAttribute("voE") ExchDTO dto
							)throws Exception {
		//get cryptoList
		List<Crypto> cryptoList = service.selectAllCrypto(vo);
		model.addAttribute("cryptoList", cryptoList);
		
		ExchDTO getOnlaodInfo = serviceExch.getOnlaodInfo(dto);
		model.addAttribute("getOnlaodInfo", getOnlaodInfo);
		
		//get UserSession
		Object memberName = session.getAttribute("memberName");
		Object idTokenKko  = session.getAttribute("idTokenKko");
		Object memberSeq  = session.getAttribute("memberSeq");
		model.addAttribute("memberName", memberName);
		model.addAttribute("idTokenKko", idTokenKko);
		model.addAttribute("memberSeq", memberSeq);
	}

	
	
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # util Functions end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
	
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Ajax get userBalance start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
	@ResponseBody
	@RequestMapping(value="userBalance")
	public ExchDTO userBalance(@ModelAttribute ("vo") ExchDTO dto,Model model) throws Exception {
		
		ExchDTO userBalance = serviceExch.userBalance(dto);
		model.addAttribute("userBalance", userBalance);
		
		return userBalance;
	}
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Ajax get userBalance end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Ajax get OBList start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
	
	private SimpMessagingTemplate template;
	
	@Autowired
	public ExchangeController(SimpMessagingTemplate template) {
		this.template = template;
		System.out.println("ExchangeController :: ExchangeController");
	}
	
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
		
		System.out.println("ExchangeController.selectBOBOne().observeSubmittedBids(selectBOBOne) :: 연결된 웹소켓으로 불러온 매수주문 하나를 보냅니다. "+"\n" + "");
		observeSubmittedBids(selectBOBOne);
		
		List<Order> selectSOB = serviceExch.selectSOB(order);
		System.out.println("ExchangeController.selectBOBOne().orderMatchingBuy(selectBOBOne,selectSOB) :: 매수주문의 주문매칭을 시작합니다. "+"\n" + "");
		serviceExch.orderMatchingBuy(selectBOBOne, selectSOB);
		return selectBOBOne;
	}
	//주문내역 하나 클라이언트로 전달
	@RequestMapping(path="/observeSubmittedBids", method = RequestMethod.POST)
	public void observeSubmittedBids(Order order)throws Exception{
		System.out.println("ExchangeController.observeSubmittedAsks() :: 연결된 웹소켓에 메세지를 전달합니다.");
		Order bobOne = serviceExch.selectBOBOne(order);
	    this.template.convertAndSend("/topic/observeSubmittedBids", bobOne);
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
		Order selectSOBOne = serviceExch.selectBOBOne(order);
		
		System.out.println("ExchangeController.selectBOBOne().observeSubmittedBids(selectSOBOne) :: 연결된 웹소켓으로 불러온 매도주문 하나를 보냅니다. "+"\n" + "");
		observeSubmittedAsks(selectSOBOne);
		
		List<Order> selectBOB = serviceExch.selectBOB(order);
		System.out.println("ExchangeController.selectBOBOne().orderMatchingBuy(selectSOBOne,selectBOB) :: 매도주문의 주문매칭을 시작합니다. "+"\n" + "");
		serviceExch.orderMatchingBuy(selectSOBOne, selectBOB);
		
		return selectSOBOne;
	}
	
	// 주문내역 하나 클라이언트로 전달
	@RequestMapping(path="/observeSubmittedAsks")
	public void observeSubmittedAsks(Order order)throws Exception{
		Order sobOne = serviceExch.selectSOBOne(order);
		System.out.println("ExchangeController.observeSubmittedAsks() :: 연결된 웹소켓에 메세지를 전달합니다.");
	    this.template.convertAndSend("/topic/observeSubmittedAsks", sobOne);
	}
	
	/*
	 * 주문을 던짐.
	 * 오더북에 주문DB가 저장이 됨. 
	 * db에서 감지하면 클라이언트로 보냄
	 * 
	 * 어떻게 감지하고 보내?
	 * 
	 * 클라이언트에서는 selectOne으로 최근 주문내역을 하나 찍음.
	 * 
	 * 주문이 저장되고 나서 메세지를 보내야함. 그럼 메소드를 어디다 위치 시키지?
	 */
	
	@RequestMapping(path="deleteCompleteOrderDivFromOB")
	public void deleteCompleteOrderDivFromOB(Order order) {
		System.out.println(
				"ExchangeController.deleteCompleteOrderDivFromOB :: 거래가 완료된 주문을 삭제합니다."+"\n"
				+ "주문번호는, dto.getObSeq() :: "+order.getObSeq()+"\n"
				+ "매수/매도는, dto.getObSeq() :: "+order.getObSeq()+"\n"
				+ "주문 상태는, dto.getOrderStatus() :: "+order.getOrderStatus()+"\n"
				+ "주문유형은 "+order.getOrderType() + " 입니다."+"\n" + "");
		this.template.convertAndSend("/topic/deleteCompleteOrderDivFromOB", order.getObSeq());
	}
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Ajax get OBList end  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */	
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Ajax submitOrder star @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
	@ResponseBody
	@RequestMapping(path="submitBids", method = RequestMethod.POST)
	public String submitBids(@ModelAttribute ("vo") ExchDTO dto)throws Exception {
		System.out.println("ExchangeController.submitBids :: 매수주문을 신청합니다."+"\n" + "");
		
		int submitBids = serviceExch.submitBids(dto);
		System.out.println("submitBids :: "+submitBids );
		
		return "submitBids";
	}
	@ResponseBody
	@RequestMapping(path="submitAsks", method = RequestMethod.POST)
	public String submitAsks(@ModelAttribute ("vo") ExchDTO dto)throws Exception {
		
		System.out.println("ExchangeController.submitAsks :: 매도주문을 신청합니다."+"\n" + "");
		int submitAsks = serviceExch.submitAsks(dto);
		System.out.println("submitAsks :: "+submitAsks );
		
		return "submitAsks";
	}
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Ajax submitOrder end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
	
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Exch Pages start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
	
	@RequestMapping(value = "/EZC")
	public String EZC(Model model
					,@ModelAttribute("vo") Crypto vo
					,@ModelAttribute("dto") ExchDTO dto
					,@ModelAttribute("voW") WodDTO voW)throws Exception {
		
		
		getBasicList(model, vo, voW, dto);
		
		
		
		
		return "exchange/crypto/EZC";
	}
	
	@RequestMapping(value="/SYC")
	public String SYC(Model model
					,@ModelAttribute("vo") Crypto vo
					,@ModelAttribute("dto") ExchDTO dto
					,@ModelAttribute("voW") WodDTO voW)throws Exception{
		
		getBasicList(model, vo, voW, dto);
		
		
		
		return "exchange/crypto/SYC";
	}
	
	@RequestMapping(value="/BTC")
	public String BTC(Model model
					,@ModelAttribute("vo") Crypto vo
					,@ModelAttribute("dto") ExchDTO dto
					,@ModelAttribute("voW") WodDTO voW)throws Exception{
		
		
		getBasicList(model, vo, voW, dto);
		
		
		
		return "exchange/crypto/BTC";
	}
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Exch Pages end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
	
	

}