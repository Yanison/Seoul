package com.my.seoul2.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.seoul2.dao.UserDao;
import com.my.seoul2.vo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@Resource(name="UserDao")
	private UserDao userDao;
	
	
	
	void serMeFromSession(HttpSession s, Model m) {
		//세션에서 정보를 받아와서 
		User me = (User) s.getAttribute("me");	
		m.addAttribute("me",me);
		
//		User rootUser = new User();
//		rootUser.setUser_idx(rootUser);
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		
		serMeFromSession(session, model);
		model.addAttribute("now_menu","home");
		
		
		
		
		return "home";
	}
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String adduser(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","adduser");
		return "adduser";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","login");
		return "login";
	}
	
	@RequestMapping(value = "/helpcenter", method = RequestMethod.GET)
	public String helpcenter(Model model, HttpSession session) {
		
		
		
		serMeFromSession(session, model);
		model.addAttribute("now_menu","helpcenter");
		
		
		List<User> userlist = userDao.getAll();
		model.addAttribute("userlist", userlist);
		
		
		return "helpcenter/helpcenter";
	}
	
	//root
	@RequestMapping(value = "/root", method = RequestMethod.GET)
	public String root(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","root");
		return "root/root";
	}
	
	@RequestMapping(value = "/root2", method = RequestMethod.GET)
	public String root2(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","root2");
		return "root/root2";
	}
	
	@RequestMapping(value = "/root3", method = RequestMethod.GET)
	public String root3(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","root3");
		return "root/root3";
	}
	
	@RequestMapping(value = "/user_info_form", method = RequestMethod.GET)
	public String user_info_form(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","user_info_form");
		
		List<User> userlist = userDao.getAll();
		model.addAttribute("userlist", userlist);
		
		
		return "root/user_info_form";
	}
	
}
