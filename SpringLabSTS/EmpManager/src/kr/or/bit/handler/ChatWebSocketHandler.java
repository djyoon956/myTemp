package kr.or.bit.handler;

import java.io.IOError;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log(session.getId() + " 연결 됨");
		users.put(session.getId(), session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log(session.getId() + " 연결 종료됨");
		users.remove(session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message);
		log(session.getId() + "로부터 메시지 수신: " + message.getPayload());
		JSONObject data = new JSONObject(message.getPayload());
		String cmd = data.getString("cmd");
		for (WebSocketSession s : users.values()) {

			if (cmd.equals("message")) {

				String jsonString = "{" + "\"message\" : \"" + data.getString("message")+"\",\"sender\" : \""+data.getString("sender")+"\"";

				if (session.getId().equals(s.getId())) {
					jsonString += ", \"auth\" : \"my\"";
				} else {
					jsonString += ", \"auth\" : \"other\"";
				}
				jsonString += "}";
				s.sendMessage(new TextMessage(jsonString));
				log(s.getId() + "에 메시지 발송: " + message.getPayload());
			} else if (cmd.equals("system")) {
				systemMessage(data.getString("message"));
			}
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId() + " 익셉션 발생: " + exception.getMessage());
	}

	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}

	private void systemMessage(String message) throws Exception {
		String jsonString = "{\"message\" : \"" + message + "\", \"sender\" : \"system\"}";
		for (WebSocketSession s : users.values()) {
			s.sendMessage(new TextMessage(jsonString));
		}
	}
}
