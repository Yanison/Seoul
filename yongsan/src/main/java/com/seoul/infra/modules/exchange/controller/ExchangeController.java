package com.seoul.infra.modules.exchange.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seoul.infra.dto.Crypto;
import com.seoul.infra.modules.cryptogroup.CryptoGroupServiceImpl;
import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.ExchangeServiceImpl;
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