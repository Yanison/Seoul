package com.seoul.infra.common.utilFunc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class MyFunc {
	
	@Autowired
	HttpSession session;
	
	public void getSss(Model model, String mmName, String mmkTkn, String mmSeq) {
		Object memberName = session.getAttribute(mmName);
		Object idTokenKko  = session.getAttribute(mmkTkn);
		Object memberSeq  = session.getAttribute(mmSeq);
		model.addAttribute(mmName, memberName);
		model.addAttribute(mmkTkn, idTokenKko);
		model.addAttribute(mmSeq, memberSeq);
	}

}
