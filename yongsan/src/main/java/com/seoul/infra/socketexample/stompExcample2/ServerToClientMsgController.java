package com.seoul.infra.socketexample.stompExcample2;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServerToClientMsgController {
	
	
	@RequestMapping("/sockExample2")
	public String sockExmpl(){
		
		return "sockExample/sockExampleSendToUser";
	}

}
