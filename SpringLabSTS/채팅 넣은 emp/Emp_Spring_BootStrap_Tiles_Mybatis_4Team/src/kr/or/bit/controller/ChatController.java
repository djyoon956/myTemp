package kr.or.bit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

	@RequestMapping("/NewFile.do")
	public String show() {
		return "/socket/chat-ws.jsp";
	}
	
	
}
