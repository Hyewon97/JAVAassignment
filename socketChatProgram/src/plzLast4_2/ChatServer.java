package plzLast4_2;

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
    } // end main
} // end class

/* 이거 주석 제대로 달려 있는지 확인하고 필요없는 프린트문 확인하고 한번 더 작동 되는지 확인하고 이 파일로 작업하기...^^ */