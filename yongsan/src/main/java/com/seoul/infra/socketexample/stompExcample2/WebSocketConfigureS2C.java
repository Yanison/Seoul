package com.seoul.infra.socketexample.stompExcample2;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigureS2C implements WebSocketMessageBrokerConfigurer {
	
	@Override
	  public void registerStompEndpoints(StompEndpointRegistry registry) {
		  System.out.println("WebSocketConfigureS2C :: registerStompEndpoints");
	    registry
	    .addEndpoint("/S2C")
	    .setAllowedOrigins("*")
	    .withSockJS()
	    .setStreamBytesLimit(512 * 1024)
	    .setHttpMessageCacheSize(1000)
	    .setDisconnectDelay(30 * 1000);
	  }
	
	@Override
	  public void configureMessageBroker(MessageBrokerRegistry config) {
		System.out.println("WebSocketConfigureS2C ::configureMessageBroker");
	    config.enableSimpleBroker("/subscribe");
	    config.setApplicationDestinationPrefixes("/app");
	  }
	  
}
