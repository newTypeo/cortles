package com.cortles.project.ws;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.cortles.project.member.model.vo.Member;


public class CortlesWebSocketConfigurator extends Configurator {
	
	/**
	 * 사용자연결을 위한 WebSocket용 Session객체 생성시 메소드를 호출
	 * - HttpSession에 등록된 loginMember객체의 memberId를 가져와서
	 * - ServerEndpointConfig객체의 properties 맵 객체에 저장.
	 * - Endpoint클래스의 @OnOpen 메소드에서 사용할수 있다.
	 */
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		System.out.println("HelloWebSocketConfigurator#modifyHandshake 실행");
		HttpSession httpSession = (HttpSession) request.getHttpSession();
		
		// memberId 관리
		Member loginMember = (Member) httpSession.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		
		Map<String, Object> configProperties = sec.getUserProperties();
		configProperties.put("memberId", memberId);
		
		
	}
}
