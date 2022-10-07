package com.seoul.infra.modules.cryptogroup;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seoul.infra.dto.Crypto;


@Controller
@RequestMapping(value="cryptogroup")
public class CtyptoGroupController {
	
	@Autowired
	CryptoGroupServiceImpl service;
	@Autowired
	HttpSession session;
	
	
	public void getSSs(Model model,HttpSession session) {
		
		Object userNick = session.getAttribute("nickname");
		Object userKkoIdToken  = session.getAttribute("idToken");

		System.out.println("session.getAttribute nickname :: " + session.getAttribute("nickname"));
		System.out.println("session.getAttribute idToken :: " + session.getAttribute("idToken"));
		
		model.addAttribute("userNick", userNick);
		model.addAttribute("userKkoIdToken", userKkoIdToken);
	}
	
	@RequestMapping(value="cryptoGroupList")
	public String cryptoGroupList(@ModelAttribute("vo") Crypto vo, Model model)throws Exception {
		
		List<Crypto> getCryptoList = service.selectAllCrypto(vo);
		model.addAttribute("getCryptoList", getCryptoList);
		
		return "infra/hwangdmin/cryptogroup/cryptoGroupList";
	}
	
	@RequestMapping(value="cryptoGroupInfo")
	public String cryptoGroupInfo(@ModelAttribute("vo") Crypto vo, Model model)throws Exception {
		
		
		return "infra/hwangdmin/cryptogroup/cryptoGroupInfo";
	}
	
	
}
