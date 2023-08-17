package java021_network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Java221_ChatServer {

	public static void main(String[] args) {
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(7777);
			while(true) {
				// 현재 접속한 클라이언트 정보 저장
				Socket client  = server.accept(); // 서버를 통해서 받아야 함
				System.out.printf("client가 %s로 접속\n", client.getInetAddress().getHostAddress()); // 클라이언트의 ip 주소를 받겠다?
				
				Java221_ChatHandler handle = new Java221_ChatHandler(client);
				handle.initStart();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // end main

} // end class
