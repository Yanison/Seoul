package com.seoul.infra.fb;
//본인에게 맞는 package로 변경하도록 합니다.

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/chat/")
public class ChatController {
	
	@Autowired
	ChatServiceImpl service;

	@RequestMapping(value="")
	public String chat(HttpSession httpSession,Model model) throws Exception {
		
		Integer ss =  (Integer) httpSession.getAttribute("memberSeq");
		List<Chat> list = service.selectChatListFromOne(ss);
		
		model.addAttribute("list", list);
		
		return "fb/firebase";
		//본인에게 맞는 jsp경로로 변경하도록 합니다.
	}

	@ResponseBody
	@RequestMapping(value="insChat")
	public Map<String,Object> insChat(HttpSession httpSession,Chat dto) throws Exception {
		
		
		System.out.println("dto.getCuMember() :: " + dto.getCuMember());
		
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		Integer ss = (Integer) httpSession.getAttribute("memberSeq");
		Chat newChat = service.createChat(ss,dto.getCuMember());
		
		if(newChat != null) {
			result.put("rt", "success");
			result.put("newChat", newChat);
		}
		else
			result.put("rt", "fail");
		
		return result;
	}
}