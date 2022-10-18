package com.seoul.infra.modules.exchange.exchwebsocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.seoul.infra.modules.exchange.ExchangeServiceImpl;
import com.seoul.infra.modules.exchange.dto.ExchDTO;

public class EschSockService {
	
	@Autowired
	ExchangeServiceImpl service; 
	
	private SimpMessagingTemplate template;
	
//	public int getCountOBList(ExchDTO dto) throws Exception {
//		while(true) {
//			return service.countOB(dto);
//		}
//	}
	// obcount가 하나씩 늘어나는 걸 감
	
	
	
	@Autowired
	public EschSockService(SimpMessagingTemplate template) {
		this.template = template;
	}
	
//	@RequestMapping(path="/greetings", method = RequestMethod.POST)
//	  public void greet(String greeting, ExchDTO dto)throws Exception {
//		  System.out.println("GreetingController :: greet" + greeting);
//		  List<ExchDTO> listBOB = service.selectBOB(dto);
//		  List<ExchDTO> listSOB = service.selectSOB(dto);
//		  Gson gson = new Gson();
//	      String text = gson.toJson(listSOB, ExchDTO);
//	      this.template.convertAndSend("/topic/SimpMessagingTemplate", text);
//	  }

}
