package com.seoul.infra.socketexample.stompExcample2;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class StompServerToClient {
	private static final Logger logger = LoggerFactory.getLogger(StompServerToClient.class);

	private SimpMessagingTemplate template;

    @Autowired
    public void setMsgController(SimpMessagingTemplate template) {
        this.template = template;
    }
    
    @Scheduled(cron="*/2 * * * * *")
    public void checkNotice() throws Exception {
    	  logger.info("checkNotice call");
          try{
        	  template.setMessageConverter(new StringMessageConverter());
        	  template.convertAndSend("/subscribe/notice", new Date().toString() + " : 이것은 서버 메시지이빈다.");

          }catch(Exception ex){
              logger.error(ex.getMessage());
          }
    }
}
	

