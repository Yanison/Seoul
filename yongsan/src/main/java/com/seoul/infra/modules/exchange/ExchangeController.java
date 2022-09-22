package com.seoul.infra.modules.exchange;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/exchange/")
public class ExchangeController {
	
	@RequestMapping(value = "Exchange")
	public String codeGroupHome(Model model) {
		
		return "exchange/exchange";
	}

}