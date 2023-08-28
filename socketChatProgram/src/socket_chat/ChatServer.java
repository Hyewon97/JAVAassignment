package socket_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) {
		System.out.println("서버 접속 중..."); 

		ServerSocket server = null;

		try {
			server = new ServerSocket(7777); // 포트 번호 7777로 생성
			while (true) {
				// 현재 접속한 클라이언트 정보 저장
				Socket client = server.accept();
				if (client != null) { // 클라이언트가 있으면 클라이언트 ip 주소 출력
					System.out.printf("client가 %s로 접속\n", client.getInetAddress().getHostAddress());
					ChatHandler handle = new ChatHandler(client);
					handle.initStart();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  // end main
} // end class


