package handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//TextWebSocketHandler 텍스트 데이터를 주고받을 때 상속 받아 사용가능
public class EchoHandler extends TextWebSocketHandler {

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId() + "연결");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.printf("%s로 부터 [%s] 받음\n", session.getId(), message.getPayload());

		// 웹 소켓 클라이언트에 데이터 전송
		session.sendMessage(new TextMessage("echo: " + message.getPayload()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId() + "연결 끊김");
	}
}
