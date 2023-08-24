package plzLast3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args) {
    	System.out.println("서버 접속 중...");
    	
        ServerSocket server = null;

        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket client = server.accept();
                if (client != null) {
                    System.out.printf("client가 %s로 접속\n", client.getInetAddress().getHostAddress());
                    ChatHandler handle = new ChatHandler(client);
                    handle.initStart();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

