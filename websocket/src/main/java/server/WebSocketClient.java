package server;

import javax.websocket.*;
import java.net.URI;

public class WebSocketClient {

    public static void main(String[] args) throws Exception {
        URI uri = new URI("ws://localhost:8090/WebSocketServer/websocket"); // WebSocket 서버 주소를 여기에 입력하세요.

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        Session session = container.connectToServer(new Endpoint() {
            @Override
            public void onOpen(Session session, EndpointConfig config) {
                System.out.println("Connected to WebSocket Server");
            }
        }, uri);

        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                System.out.println("Received message from server: " + message);
            }
        });

        // WebSocket 연결 유지를 위해 아무 키 입력 대기
        System.in.read();
        session.close();
    }
}
