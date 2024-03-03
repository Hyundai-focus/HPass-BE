package com.hyundai.hpass.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
@Component
public class HpassWebSocketHandler extends TextWebSocketHandler {
	private ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>(); //활성화된 세션들

	@Override
	public void afterConnectionEstablished(WebSocketSession session){
		System.out.println(session);
		sessions.put(getKey(session), session);
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String title = message.getPayload().split("::")[0];
		String data = message.getPayload().split("::")[1];
		String request = getKey(session);
		System.out.println(message);
		if(request.equals("controller") && title.equals("member")) sendProdRes(data);
		if(request.equals("NfcCall") && title.equals("member")) sendProdRes(data);
	}

	public void sendProdRes(String data) throws IOException {
		if(sessions.get("exhibition") != null && sessions.get("exhibition").isOpen()) sessions.get("exhibition").sendMessage(new TextMessage(data));
		if(sessions.get("newProd") != null && sessions.get("newProd").isOpen()) sessions.get("newProd").sendMessage(new TextMessage(data));
		if(sessions.get("coupon") != null && sessions.get("coupon").isOpen()) sessions.get("coupon").sendMessage(new TextMessage(data));
	}
	private String getKey(WebSocketSession data){
		return String.valueOf(data.getUri()).split("/")[4];
	}
}