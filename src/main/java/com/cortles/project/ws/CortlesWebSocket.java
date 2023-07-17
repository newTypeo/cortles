package com.cortles.project.ws;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;


// ws.js에서 작성한 경로
@ServerEndpoint(value = "/CortlesWebSocket", configurator = CortlesWebSocketConfigurator.class)
public class CortlesWebSocket {
	
	// WebSocket 세션을 관리할 맵(멀티스레드에서 사용하므로 동기화처리 필수)
	// (key)memberId:String = (value)websocketSession:Session
	public static Map<String, Session> clientMap = 
			Collections.synchronizedMap(new HashMap<>());
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		System.out.println("open");
		Map<String, Object> configProperties = config.getUserProperties();
		String memberId = (String) configProperties.get("memberId");
		
		// 1. clientMap에 저장
		clientMap.put(memberId, session); // memberId로 해당 session을 찾기 위함
		// 2. WebSocket Session객체 properties 맵 객체에 memberId 저장 (@OnClose에서 사용)
		Map<String, Object> sessionProperties = session.getUserProperties();
		sessionProperties.put("memberId", memberId);
		
	}
	

	@OnMessage
	public void onMessage(String message, Session session) {
		// db chatService.insertChat(chat) 시작!
		
		System.out.println("message" + message);
		Map<String, Object> payload = new Gson().fromJson(message, Map.class);
		System.out.println("payload from message : " + payload);
		
	}
	
	@OnError
	public void onError(Throwable e) {
		System.out.println("error");
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("close");
	}
	
}
