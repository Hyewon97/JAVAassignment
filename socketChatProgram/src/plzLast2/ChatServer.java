package plzLast2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) {
		ServerSocket server = null;

		try {
			server = new ServerSocket(7777); // 포트 번호 7777로 생성
			while (true) {
				// 현재 접속한 클라이언트 정보 저장
				Socket client = server.accept(); // 서버를 통해서 받음
				if (client != null) { // 클라이언트가 있으면
					System.out.printf("client가 %s로 접속\n", client.getInetAddress().getHostAddress()); // 클라이언트의 주소를 출력. 확인
					ChatHandler handle = new ChatHandler(client);
					handle.initStart();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // end main

} // end class
