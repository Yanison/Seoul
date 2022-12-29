package com.seoul.infra.modules.wod;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seoul.infra.dto.Crypto;
import com.seoul.infra.modules.cryptogroup.CryptoGroupServiceImpl;
import com.seoul.infra.modules.exchange.ExchangeServiceImpl;
import com.seoul.infra.modules.exchange.dto.ExchDTO;
import com.seoul.infra.modules.exchange.orderMatchingSystem.Order;

@Controller
@RequestMapping(value="/wod/")
public class WodController {
	
	@Autowired
	CryptoGroupServiceImpl serviceCtypto;
	@Autowired
	WodServiceImpl serviceWod;
	@Autowired
	ExchangeServiceImpl serviceExch;
	@Autowired
	HttpSession session;
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
	
	Order selectTransacton = serviceExch.transactionTable(order);
	model.addAttribute("selectTransacton", selectTransacton);
	}
	
	@RequestMapping(value="/")
	public String wod(Model model
					,@ModelAttribute("voW") WodDTO voW
					,@ModelAttribute("vo") Crypto dto
					)throws Exception {
		
		Object memberName = session.getAttribute("memberName");
		Object idTokenKko  = session.getAttribute("idTokenKko");
		Object memberSeq  = session.getAttribute("memberSeq");
		model.addAttribute("memberName", memberName);
		model.addAttribute("idTokenKko", idTokenKko);
		model.addAttribute("memberSeq", memberSeq);
		
		List<Crypto> getCryptoList = serviceCtypto.selectAllCrypto(dto);
		model.addAttribute("getCryptoList", getCryptoList);
		
		
		List<WodDTO> selectBal = serviceWod.selectBal(voW);
		model.addAttribute("selectBal", selectBal);
		
		
		return "wod/wod";
	}
	
//	@ResponseBody
//	@RequestMapping(value="ajaxWod")
//	public String get mmBal() {
//		
//		return"ok";
//	}
}

