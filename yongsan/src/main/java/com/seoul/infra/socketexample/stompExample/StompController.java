package com.seoul.infra.socketexample.stompExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StompController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public WebSocketContent wsc(Message message) {
		
		System.out.println("getname"+message.getName());
		
		return new WebSocketContent("hello" + message.getName());
	}
	
	@RequestMapping("/sockExample")
	public String sockExmpl(){
		
		return "sockExample/sockExample";
	}
	
}
