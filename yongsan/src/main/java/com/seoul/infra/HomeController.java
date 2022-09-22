package com.seoul.infra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String home() {
		
		return "home/home";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		
		return "home/login";
	}
	
	@RequestMapping(value="/adduser")
	public String adduser() {
		
		return "home/adduser";
	}
	
	
	
}
