package com.seoul.infra.modules.wod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/wod/")
public class WodController {
	
	@RequestMapping(value="wod")
	public String wod() {
		
		return "wod/wod";
	}

}
