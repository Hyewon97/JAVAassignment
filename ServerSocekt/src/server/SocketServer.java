package server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress; // port 번호 받기 위한 라이브러리

public class SocketServer extends WebSocketServer {

	// 소켓이 시작되었을 때, 실행되는 부분인것 같은데 흠
	public SocketServer(int port) {
		super(new InetSocketAddress(port));
	}

	// 서버가 실행 되었을 때
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		System.out.println("WebSocket connection opened");

	}

	// 서버가 닫혔을때
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("WebSocket connection closed");

	}

	// 메세지
	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("Received message: " + message);

		// 클라이언트에게 "Hello" 메시지를 보내기
		conn.send("Hello");

	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.err.println("WebSocket error: " + ex.getMessage());

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}

	

}
