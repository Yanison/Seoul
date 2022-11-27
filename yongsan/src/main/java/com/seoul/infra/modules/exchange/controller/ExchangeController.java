package com.seoul.infra.modules.exchange.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seoul.infra.dto.Crypto;
import com.seoul.infra.modules.cryptogroup.CryptoGroupServiceImpl;
import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.ExchangeServiceImpl;
import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;
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
							,@ModelAttribute("vo") Crypto crypto
							,@ModelAttribute("voE") ExchDTO dto
							,@ModelAttribute("voE") Order order
							)throws Exception {
		//get cryptoList
		List<Crypto> cryptoList = serviceExch.selectCryptoList(crypto);
		model.addAttribute("cryptoList", cryptoList);
		ExchDTO selectCrpytoOne =serviceExch.selectCrpytoOne(dto);
		model.addAttribute("selectCrpytoOne", selectCrpytoOne);
		
		ExchDTO getOnlaodInfo = serviceExch.getOnlaodInfo(dto);
		model.addAttribute("getOnlaodInfo", getOnlaodInfo);
		
		//get UserSession
		Object memberName = session.getAttribute("memberName");
		Object idTokenKko  = session.getAttribute("idTokenKko");
		Object memberSeq  = session.getAttribute("memberSeq");
		model.addAttribute("memberName", memberName);
		model.addAttribute("idTokenKko", idTokenKko);
		model.addAttribute("memberSeq", memberSeq);
		
		System.out.println("ExchangeController.getBasicList()"+ "\n" +
				"cryptoSeq :: " + dto.getCryptoSeq() + "\n" +
				"cryptoName :: " + dto.getCryptoName() + "\n" +
				"cryptoNameKor :: " + dto.getCryptoNameKor() + "\n" +
				"memberSeq :: " + dto.getMemberSeq() + "\n" +
				"memberName :: " + dto.getMemberName()
				);
		Order selectTransacton = serviceExch.transactionTable(order);
		model.addAttribute("selectTransacton", selectTransacton);
	}

/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # util Functions end @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Exch Pages start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
 */
	

	
	@RequestMapping(value = "requestInfo/{cryptoSym}")
	@ResponseBody
	public HashMap<String, String> requestExcgPageInfo(Model model
					,@PathVariable String cryptoSym
					,@ModelAttribute("vo") Crypto vo
					,@ModelAttribute("dto") ExchDTO dto
					,@ModelAttribute("voE") Order order
					)throws Exception {
		
		dto.setCryptoSym(cryptoSym);
		
		getBasicList(model, vo,  dto,order);
		
		HashMap<String,String> exchList = new HashMap<String,String>();
		exchList.put("cryptoSeq", dto.getCryptoSeq().toString());
		exchList.put("cryptoName", dto.getCryptoName());
		exchList.put("cryptoNameKor", dto.getCryptoNameKor());
		exchList.put("crypto", dto.getCryptoSym());
		exchList.put("memberSeq", dto.getMemberSeq().toString());
		exchList.put("memberName", dto.getMemberName());
		
		return exchList;
	}
	
	@RequestMapping(value = "{cryptoSym}")
	public String requestExcgPage(Model model
					,@PathVariable String cryptoSym
					,@ModelAttribute("vo") Crypto vo
					,@ModelAttribute("dto") ExchDTO dto
					,@ModelAttribute("voE") Order order
					)throws Exception {
		
		dto.setCryptoSym(cryptoSym);
		
		getBasicList(model, vo,  dto,order);
		
		return "exchange/crypto/exchange";
	}
	

/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *@@@@@@@@@@@ # Exch Pages end
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
	
	

}