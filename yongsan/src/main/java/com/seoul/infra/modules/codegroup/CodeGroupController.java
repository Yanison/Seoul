package com.seoul.infra.modules.codegroup;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seoul.infra.dto.Crypto;

@Controller
@RequestMapping(value = "/yongsancode/")
public class CodeGroupController {
	
	@Autowired
	CodeGroupServiceImpl service;
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
	
	@RequestMapping(value = "roothome")
	public String roothome(Model model,HttpSession session) {
		
		getSSs(model,session);
		
		return "infra/hwangdmin/hwangdminComponent/rootHome";
	}
	
	

	
	@RequestMapping(value = "codeGroupList")
	public String codeGroupList(@ModelAttribute("vo") CodeGroup vo,Crypto vo2,Model model) throws Exception {
		
	
		List<CodeGroup> list = service.selectCList(vo);
		model.addAttribute("list", list);
		
		List<Crypto> ctyptoList = service.selectAllCrypto(vo2);
		model.addAttribute("ctyptoList", ctyptoList);
		
		
		return "infra/hwangdmin/codegroup/codeGroupList"; 
	}
	
	

}
