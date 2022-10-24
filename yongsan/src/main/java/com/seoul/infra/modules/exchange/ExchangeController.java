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
	public List<ExchDTO> selectBOB(ExchDTO dto)throws Exception {
		
		List<ExchDTO> selectBOB = serviceExch.selectBOB(dto);
		
		return selectBOB;
	}
	@ResponseBody
	@RequestMapping("/selectBOBOne")
	public ExchDTO selectBOBOne(ExchDTO dto)throws Exception {
		System.out.println("selectBOBOne ::");
		ExchDTO selectBOBOne = serviceExch.selectBOBOne(dto);
		observeSubmittedBids("greeting",dto);
		return selectBOBOne;
	}
	
	@RequestMapping(path="/observeSubmittedBids", method = RequestMethod.POST)
	public void observeSubmittedBids(String greeting,ExchDTO dto)throws Exception{
		System.out.println(greeting + "observeSubmittedBids ::");
		String bob = new Gson().toJson(serviceExch.selectBOBOne(dto));
		System.out.println("observeSubmittedBids // selectBOBOne ::" + bob);
	    this.template.convertAndSend("/topic/observeSubmittedBids", bob);
	}
	
	@ResponseBody
	@RequestMapping("/selectSOB")
	public List<ExchDTO> selectSOB(ExchDTO dto)throws Exception {
		
		List<ExchDTO> selectSOB = serviceExch.selectSOB(dto);
		return selectSOB;
	}
	// 주문내역 하나 가져옴
	@ResponseBody
	@RequestMapping("/selectSOBOne")
	public ExchDTO selectSOBOne(ExchDTO dto)throws Exception {
		System.out.println("selectSOBOne ::");
		ExchDTO selectSOBOne = serviceExch.selectBOBOne(dto);
		observeSubmittedAsks(dto);
		return selectSOBOne;
	}
	
	// 주문내역 하나 클라이언트로 전달
	@RequestMapping(path="/observeSubmittedAsks")
	public void observeSubmittedAsks(ExchDTO dto)throws Exception{
		System.out.println("observeSubmittedAsks ::");
		String sob = new Gson().toJson(serviceExch.selectSOBOne(dto));
		ExchDTO selectSOBOne = serviceExch.selectBOBOne(dto);
		System.out.println("observeSubmittedAsks // selectSOBOne ::" + sob);
	    this.template.convertAndSend("/topic/observeSubmittedAsks", selectSOBOne);
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
		
		int submitBids = serviceExch.submitBids(dto);
		System.out.println("submitBids :: "+submitBids );
		
		
		return "submitBids";
	}
	
	@ResponseBody
	@RequestMapping(path="submitAsks", method = RequestMethod.POST)
	public String submitAsks(@ModelAttribute ("vo") ExchDTO dto)throws Exception {
		
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
	
	@RequestMapping(value = "Exchange")
	public String codeGroupHome(Model model
								,@ModelAttribute("vo") Crypto vo
								,@ModelAttribute("dto") ExchDTO dto
								,@ModelAttribute("voW") WodDTO voW)throws Exception {
		
		getBasicList(model, vo, voW, dto);
		
		
		
		return "exchange/crypto/BTC";
	}
	
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