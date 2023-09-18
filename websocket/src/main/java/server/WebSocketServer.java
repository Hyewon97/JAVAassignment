package server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

// locallhost:8090/websocket

@ServerEndpoint("/websocket")
public class WebSocketServer {

	@OnOpen
	public void onCreateSession(Session session) {
		System.out.println("WebSocket opened: " + session.getId());

		// 클라이언트에게 "HelloWorld" 문자열 보내기
		try {
			session.getBasicRemote().sendText("HelloWorld");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Received message from client: " + message);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("WebSocket closed: " + session.getId() + " Reason: " + closeReason);
	}

}
