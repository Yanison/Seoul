package com.seoul.infra.socketexample.stompExcample2;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class StompServerToClient {
	
	private static final Logger logger = LoggerFactory.getLogger(StompServerToClient.class);
	
	private SimpMessagingTemplate template;

    @Autowired
    public void setMsgController(SimpMessagingTemplate template) {
    	System.out.println("StompServerToClient :: setMsgController");
        this.template = template;
    }
    
    public void sendMsg2C() {
    	System.out.println("StompServerToClient :: sendMsg2C");
    	 try{
       	  template.setMessageConverter(new StringMessageConverter());
       	  template.convertAndSend("/subscribe/notice", new Date().toString() + " : 이것은 서버 메시지입니다.");
         }catch(Exception ex){
             logger.error(ex.getMessage());
         }
    }   
    
}
	

