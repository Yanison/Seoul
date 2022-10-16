package com.seoul.infra.socketexample.stompExample;

public class WebSocketContent {
	
	private String content;
	
	public  WebSocketContent() {
		
	}
	
	public WebSocketContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
}
