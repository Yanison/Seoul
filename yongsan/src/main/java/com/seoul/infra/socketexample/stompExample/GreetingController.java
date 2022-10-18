package com.seoul.infra.socketexample.stompExample;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    
    greet("SimpMessagingTemplate test");
    
    return new Greeting(new Date().toString() + "Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }
  
  private SimpMessagingTemplate template;

  @Autowired
  public GreetingController(SimpMessagingTemplate template) {
      this.template = template;
      System.out.println("GreetingController :: GreetingController");
  }

  @RequestMapping(path="/greetings", method = RequestMethod.POST)
  public void greet(String greeting) {
	  System.out.println("GreetingController :: greet" + greeting);
      String text = "[" + new Date() + "]:" + greeting;
      this.template.convertAndSend("/topic/SimpMessagingTemplate", text);
  }
  
  @RequestMapping("/sockExample")
  public String sockExample() {
	  return "sockExample/sockExmpl";
  }

}