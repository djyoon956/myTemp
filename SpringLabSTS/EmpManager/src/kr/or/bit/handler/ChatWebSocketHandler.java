package kr.or.bit.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.bit.dto.ChatRoom;

public class ChatWebSocketHandler extends TextWebSocketHandler {

	// 접속한 전체 유저 관리
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	// 채팅방 관리
	private Map<String, ChatRoom> roomInfos = new HashMap<>();
	// sessionid, userid
	private Map<String, String> userNames = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log(session.getId() + " 연결 됨");
		users.put(session.getId(), session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message);
		log(session.getId() + "로부터 메시지 수신: " + message.getPayload());
		JSONObject data = new JSONObject(message.getPayload());
		String cmd = data.getString("cmd");
		System.out.println("cmd");
		System.out.println(cmd);
		if (cmd.equals("on")) {
			userNames.put(session.getId(), data.getString("sender"));
			sendChatRoomInfoMessage();
		} else if (cmd.equals("message")) {
			sendMessage(session, data);
		} else if (cmd.equals("joinChatRoom")) {
			joinChatRoom(session, data);
		} else if (cmd.equals("createChatRoom"))
			createChatRoom(session, data);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 접속 유저 종료
		if(users.containsKey(session.getId())) { 
			System.out.println("창 종료");
			users.remove(session.getId());
		}
		// 채팅창 종료
		else { 
			System.out.println("채팅 종료");
			String out = userNames.get(session.getId());
			userNames.remove(session.getId());
			
			for (ChatRoom room : roomInfos.values()) {
				if (room.getUsers().containsKey(out)) {
					room.removeUser(out);
					sendMemberInfoMessage(room, out + "님이 나가셨습니다.");
				}
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

	private void sendMessage(WebSocketSession session, JSONObject data) throws Exception {
		JSONObject json = new JSONObject().put("message", data.getString("message"))
																.put("sender", data.getString("sender"));

		ChatRoom room = roomInfos.get(data.get("room"));
		for (WebSocketSession s : room.getUsers().values()) {
			String type = session.getId().equals(s.getId()) ? "my" : "other";
			json.put("type", type);

			s.sendMessage(new TextMessage(json.toString()));
		}
	}

	private void createChatRoom(WebSocketSession session, JSONObject data) throws Exception {
		ChatRoom room = new ChatRoom(data.getString("sender")
														, data.getString("name")
														, Integer.parseInt(data.getString("max")));

		roomInfos.put(room.getName(), room);
		sendChatRoomInfoMessage();
	}

	private void joinChatRoom(WebSocketSession session, JSONObject data) throws Exception {
		users.remove(session.getId());
		ChatRoom room = roomInfos.get(data.get("room"));
		room.addUser(data.getString("sender"), session);
		sendMemberInfoMessage(room, data.getString("sender") + "님이 들어오셨습니다.");

		sendChatRoomInfoMessage();
	}

	private void sendMemberInfoMessage(ChatRoom room, String message) throws Exception {
		String jsonString = new JSONObject().put("message", message)
																.put("type", "memberInfo")
																.put("owner", room.getOwner())
																.put("users", new JSONArray(room.getUserName())).toString();

		for (WebSocketSession s : room.getUsers().values())
			s.sendMessage(new TextMessage(jsonString));
	}

	private void sendChatRoomInfoMessage() throws Exception {
		JSONArray array = new JSONArray();
		for(ChatRoom room : roomInfos.values()) {
			array.put(new JSONObject().put("owner", room.getOwner())
													.put("max", room.getMax())
													.put("name", room.getName())
													.put("users", new JSONArray(room.getUserName())));
		}
		
		String jsonString = new JSONObject().put("type", "chatRoomInfo")
																.put("rooms", array).toString();
		for (WebSocketSession s : users.values())
			s.sendMessage(new TextMessage(jsonString));
	}
}
