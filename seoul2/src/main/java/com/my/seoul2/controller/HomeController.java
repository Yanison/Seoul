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
public class HomeController {
	
	
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
	
	
	
	
////////////////////////////////////////////////////////////////////////////////
//test
////////////////////////////////////////////////////////////////////////////////	
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","test");
		return "test";
	}
	
	
	
	
////////////////////////////////////////////////////////////////////////////////
//root
////////////////////////////////////////////////////////////////////////////////
	
	
	
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
	
////////////////////////////////////////////////////////////////////////////////
	//root
////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////
	//root_form
	//////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/user_info_form", method = RequestMethod.GET)
	public String user_info_form(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","user_info_form");
	
		
		return "root/user_info_form";
	}
	
	
	
	//////////////////////////////////////////////////////////////////////
	//root_main
	//////////////////////////////////////////////////////////////////////	
	@RequestMapping(value = "/root_main", method = RequestMethod.GET)
	public String root_main(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","root_main");
		return "root/root_main/root_main";
	}
	
	@RequestMapping(value = "/root_main_userinfo", method = RequestMethod.GET)
	public String root_main_userinfo(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","root_main_userinfo.jsp");
		
		List<User> userlist = userDao.getAllInfo();
		model.addAttribute("userlist", userlist);
		
		
		return "root/root_main/root_main_userinfo";
	}
	
	//수정
	@RequestMapping(value = "/root_main_mod_userinfo", method = RequestMethod.GET)
	public String root_main_mod_userinfo(Model model, 
			HttpSession session,
			@RequestParam(value="user_idx") int user_idx
			) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","root_main_mod_userinfo");
		
		model.addAttribute("user_idx",user_idx);
		
		return "root/root_main/root_main_mod_userinfo";
	}
	
	//등록폼
	@RequestMapping(value = "/root_main_form_userinfo", method = RequestMethod.GET)
	public String root_main_form_userinfo(
			Model model,
			HttpSession session
			
			) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","root_main_form_userinfo");
		
		
		
		
		return "root/root_main/root_main_form_userinfo";
	}
	
	
	
	//////////////////////////////////////////////////////////////////////
	//root_cd
	//////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/root_codegroup", method = RequestMethod.GET)
	public String root_codegroup(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","root_codegroup");
		return "root/root_codegroup/root_codegroup";
	}
	
	
	
	//////////////////////////////////////////////////////////////////////
	//root_cm
	//////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/root_code_manage", method = RequestMethod.GET)
	public String root_code_manage(Model model, HttpSession session) {

		serMeFromSession(session, model);
		model.addAttribute("now_menu","root_code_manage");
		return "root/./root_code_manage/root_code_manage";
	}
	
	
	

	
}
