package com.hyundai.hpass.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class HpassWebSocketHandler extends TextWebSocketHandler {

	private ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>(); //활성화된 세션들
	@Override
	public void afterConnectionEstablished(WebSocketSession session){
		sessions.put(getKey(session), session);
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String title = message.getPayload().split("::")[0];
		String data = message.getPayload().split("::")[1];
		String request = getKey(session);
		System.out.println(data);
		if(request.equals("controller") && title.equals("addProduct")) addProduct(data);
		if(request.equals("controller") && title.equals("coupon")) addProduct("coupon");
	}
	private void addProduct(String data) throws IOException {
		WebSocketSession session = sessions.get("getAddProduct");
		System.out.println(session.toString());
		session.sendMessage(new TextMessage(data));
	}
	private String getKey(WebSocketSession data){
		return String.valueOf(data.getUri()).split("/")[4];
	}
}