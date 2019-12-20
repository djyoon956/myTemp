package kr.or.bit.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.bit.dto.ChatRoom;

public class ChatWebSocketHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	// sessionid, userid
	private Map<String, String> userNames = new HashMap<>();
	private Map<String, ChatRoom> roomInfos = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log(session.getId() + " 연결 됨");
		users.put(session.getId(), session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log(session.getId() + " 연결 종료됨");
		users.remove(session.getId());
		String out = userNames.get(session.getId());
		userNames.remove(session.getId());
		sendMemberInfoMessage(out + "님이 나가셨습니다.");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message);
		log(session.getId() + "로부터 메시지 수신: " + message.getPayload());
		System.out.println(session.getAttributes().get("userid"));
		JSONObject data = new JSONObject(message.getPayload());
		String cmd = data.getString("cmd");
		if (cmd.equals("on")) {
			sendChatRoomInfoMessage();
		} else if (cmd.equals("message")) {
			JSONObject json = new JSONObject()
										.put("message", data.getString("message"))
										.put("sender", data.getString("sender"));
			for (WebSocketSession s : users.values()) {
				String auth = session.getId().equals(s.getId()) ? "my" : "other";
				json.put("auth", auth);

				s.sendMessage(new TextMessage(json.toString()));
				log(s.getId() + "에 메시지 발송: " + message.getPayload());
			}
		} else if (cmd.equals("join")) {
			userNames.put(session.getId(), data.getString("sender"));
			sendMemberInfoMessage(data.getString("sender") + "님이 들어오셨습니다.");
		}else if(cmd.equals("createChatRoom")) {
			ChatRoom room = new ChatRoom(data.getString("sender")
																, data.getString("name")
																, Integer.parseInt(data.getString("max")));
			
			roomInfos.put(room.getName(), room);
			sendChatRoomInfoMessage();
		}else if (cmd.equals("joinChatRoom")) {
			ChatRoom room =roomInfos.get(data.get(data.getString("name")));
			room.addUsers(data.getString("sender"));
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId() + " 익셉션 발생: " + exception.getMessage());
	}

	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}

	private void sendMemberInfoMessage(String message) throws Exception {
		String jsonString = new JSONObject().put("message", message)
																.put("type", "memberInfo")
																.put("members", userNames.values())
																.toString();

		for (WebSocketSession s : users.values()) 
			s.sendMessage(new TextMessage(jsonString));
	}
	
	private void sendChatRoomInfoMessage() throws Exception {
		String jsonString = new JSONObject().put("type", "chatRoomInfo")
																.put("rooms", new ObjectMapper().writeValueAsString(roomInfos.values()))
																.toString();
	
		for (WebSocketSession s : users.values()) 
			s.sendMessage(new TextMessage(jsonString));
	}
}
