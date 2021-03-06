package kr.or.bit.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;


public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor{
	
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
    	System.out.println("Before Handshake");
    	ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;
    	HttpServletRequest req= ssreq.getServletRequest();

    	String cmd  = req.getParameter("cmd");
    	String user  = (String) req.getSession().getAttribute("userid");
    	System.out.println(cmd+user);
        // 파라미터로 입력된 attributes에 put을 하면 WebSocketSession에서 접근가능
    	attributes.put("cmd", cmd);
    	attributes.put("user", user);
    	if(cmd.equals("join")) {
    		attributes.put("room", req.getParameter("room"));
    	}
    	
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
  
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
