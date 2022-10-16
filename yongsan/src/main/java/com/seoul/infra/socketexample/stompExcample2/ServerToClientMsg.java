package com.seoul.infra.socketexample.stompExcample2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServerToClientMsg {
	
	
	@RequestMapping("/sockExample2")
	public String sockExmpl(){
		
		return "sockExample/sockExampleSendToUser";
	}

}
