package com.seoul.infra.modules.wod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seoul.infra.dto.Crypto;
import com.seoul.infra.modules.cryptogroup.CryptoGroupServiceImpl;

@Controller
@RequestMapping(value="/wod/")
public class WodController {
	
	@Autowired
	CryptoGroupServiceImpl serviceCtypto;
	@Autowired
	WodServiceImpl serviceWod;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="wod")
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

