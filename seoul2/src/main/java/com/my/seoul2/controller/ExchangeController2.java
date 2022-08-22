package com.my.seoul2.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.seoul2.dao.UserDao;
import com.my.seoul2.vo.User;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ExchangeController2 {
	
	
	@Resource(name="UserDao")
	private UserDao userDao;
	
	
	
	void serMeFromSession(
			HttpSession s,
			Model m
			) {
		
		//세션에서 정보를 받아와서 
		User me = (User) s.getAttribute("me");
//		String me2 = (String)s.getAttribute("me");
		m.addAttribute("me",me);
		
		User root = (User) s.getAttribute("root");
		m.addAttribute("root", root);
	
		

//		// User rootUser = (User) s.getAttribute("root");
//		String rootUser = (String)s.getAttribute("root");
//		m.addAttribute("root",rootUser);
		
	}
	
	
	
	
///////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////
//exchange
////////////////////////////////////////////////////////////////////////////////
	
//	
//	
//	@RequestMapping(value = "/exchange", method = RequestMethod.GET)
//	public String exchange1(Model model, HttpSession session) {
//		
//		serMeFromSession(session, model);
//		model.addAttribute("now_menu","exchange");
//		
//		
//		return "exchange";
//	}
//	
//	@RequestMapping(value = "/exchange#2", method = RequestMethod.GET)
//	public String exchange2(Model model, HttpSession session) {
//		
//		serMeFromSession(session, model);
//		model.addAttribute("now_menu","exchange#2");
//		
//		
//		return "exchange#2";
//	}
//	
//	@RequestMapping(value = "/exchange#3", method = RequestMethod.GET)
//	public String exchange3(Model model, HttpSession session) {
//		
//		serMeFromSession(session, model);
//		model.addAttribute("now_menu","exchange#3");
//		
//		
//		return "exchange3";
//	}
	
}
