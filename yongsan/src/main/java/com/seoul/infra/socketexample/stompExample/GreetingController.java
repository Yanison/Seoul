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
import com.seoul.infra.modules.exchange.dto.ExchDTO;

@Controller
public class GreetingController {


  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message, ExchDTO dto) throws Exception {
    Thread.sleep(1000); // simulated delay
    
    greet("oh!", dto);
    
    return new Greeting(new Date().toString() + "Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
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

  @RequestMapping(path="/greetings", method = RequestMethod.POST)
  public void greet(String greeting,ExchDTO dto) {
	  System.out.println("GreetingController :: greet" + greeting);
	  
	  String bob = new Gson().toJson(dao.selectBOB(dto));
      
      this.template.convertAndSend("/topic/SimpMessagingTemplate", bob);
  }
  
  @RequestMapping("/sockExample")
  public String sockExample(Model model, ExchDTO dto)throws Exception {
	  
	  List<ExchDTO> selectBOB = service.selectBOB(dto);
	  model.addAttribute("selectBOB", selectBOB);
	  
	  return "sockExample/sockExmpl";
  }
  
  @ResponseBody
  @RequestMapping("/sockAjaxList")
  public List<ExchDTO> sockAjax(Model model, ExchDTO dto)throws Exception {
	  
	  List<ExchDTO> selectBOB = service.selectBOB(dto);
	  model.addAttribute("selectBOB", selectBOB);
	  
	  return selectBOB;
  }

}