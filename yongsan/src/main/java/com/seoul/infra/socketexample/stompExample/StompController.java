package com.seoul.infra.socketexample.stompExample;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class StompController {
	 
	  @MessageMapping("/hello")
	  @SendTo("/topic/greetings")
	  public Greeting greeting(HelloMessage message) throws Exception {
	    Thread.sleep(1000); // simulated delay
	    System.out.println("getName :: "+message.getName());
		System.out.println("getMessage :: "+message.getMessage());
	    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	  }

	 
	@RequestMapping("/sockExample")
	public String sockExample() {
		return "sockExample/sockExample";
	}
	
}
