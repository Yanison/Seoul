package com.seoul.infra.socketexample.stompExample;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.google.gson.Gson;
import com.seoul.infra.modules.exchange.ExchangeDao;
import com.seoul.infra.modules.exchange.ExchangeServiceImpl;
import com.seoul.infra.modules.exchange.orderMatchingSystem.engine.Order;

@Controller
public class GreetingController {


  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message, Order dto) throws Exception {
    Thread.sleep(1000); // simulated delay
    
    greet("oh!", dto);
    
    return new Greeting(new Date().toString() + "Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }
  
  @RequestMapping(path="/greetings", method = RequestMethod.POST)
  public void greet(String greeting,Order dto) {
	  System.out.println("GreetingController :: greet" + greeting);
	  
//	  String bob = new Gson().toJson(dao.selectBOB(dto));
      
//      this.template.convertAndSend("/topic/SimpMessagingTemplate", bob);
  }
  
  private SimpMessagingTemplate template;

  @Autowired
  public GreetingController(SimpMessagingTemplate template) {
      this.template = template;
      System.out.println("GreetingController :: GreetingController");
  }
  
  @Autowired
  ExchangeDao dao;
  @Autowired
  ExchangeServiceImpl service;

  
  
  @RequestMapping("/sockExample")
  public String sockExample(Model model, Order dto)throws Exception {
	  
	  List<Order> selectBOB = service.selectBOB(dto);
	  model.addAttribute("selectBOB", selectBOB);
	  
	  return "sockExample/sockExmpl";
  }
  
  @ResponseBody
  @RequestMapping("/sockAjaxList")
  public List<Order> sockAjax(Model model, Order dto)throws Exception {
	  
	  List<Order> selectBOB = service.selectBOB(dto);
	  model.addAttribute("selectBOB", selectBOB);
	  
	  return selectBOB;
  }

}