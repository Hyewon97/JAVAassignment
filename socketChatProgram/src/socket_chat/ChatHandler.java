package socket_chat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Vector;

public class ChatHandler implements Runnable {
	Socket socket;
	Thread th;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private static Vector<ChatHandler> userVect = new Vector<ChatHandler>();

	public ChatHandler() {

	}

	// 현재 저장되어 있는 클라이언트 정보를 socket으로 보냄
	public ChatHandler(Socket socket) {
		this.socket = socket;
	}

	public void initStart() {
		if (th == null) {
			try {
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				dataIn = new DataInputStream(new BufferedInputStream(is));
				dataOut = new DataOutputStream(new BufferedOutputStream(os));

				th = new Thread(this); // 스레드로 돌아갈 수 있도록 함
				th.start();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	} // end initStart()

	@Override
	public void run() {
		// 현재 자신을 벡터에 넣어둠
		userVect.addElement(this);

		// 스레드가 연결이 끊어지지 않으면 계속 실행되도록 함
		while (!Thread.interrupted()) {
			try {
				String message = dataIn.readUTF(); // 인코딩 utf-8로 데이터를 받아옴

				if (message.equals("파일 전송")) { // 파일 전송을 하게 메세지를 "파일 전송으로 변환함"
					// 파일 전송 문자열을 받아오는지
					
					String fileName = dataIn.readUTF(); // 파일 이름을 가지고 옴		
									
					broadcast("파일 " + fileName + "이(가) 전송되었습니다."); 		
				} else if (message.equals("파일 전송 완료")) {
					dataOut.writeUTF("다음 메시지를 입력하세요.");
					dataOut.flush();
				} else {
					broadcast(message);
				}

			} catch (IOException e) { // 스레드 연결이 끊긴 경우
				
				System.out.println(socket.getInetAddress().getHostAddress() + " 나갔습니다."); // 파일 수신하고 나갔습니다 메세지 뜨는데 왜일까..
																							// 근데 여기선 또 안뜸
				userVect.remove(this);
				break;
			}
		}

	} // end run() ///////////////////////////////

	// 브로드캐스트
	public void broadcast(String message) {
		Enumeration<ChatHandler> enu = userVect.elements();
		
		// 서버에서 메세지 출력
		System.out.println(message);
		
		while (enu.hasMoreElements()) {
			ChatHandler handler = enu.nextElement();
			try {
				byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8); // 인코딩 utf-8로 바이트를 받아서 byte[]에 저장
								
				int messageLength = messageBytes.length;

				handler.dataOut.writeInt(messageLength); // 채팅 텍스트의 바이트 길이 전송
				handler.dataOut.write(messageBytes); // 실제 텍스트 데이터 전송
				

				handler.dataOut.flush();
			} catch (IOException e) {
				handler.stop();
			}
		}
	}

	private void stop() {
		if (th != null) {
			if (th != Thread.currentThread()) {
				th.interrupt();
				th = null;

				try {
					// 데이터를 반납하기 위한 과정
					dataIn.close();
					dataOut.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}

	} ////////////////////////////////////

	// receiveFile 메소드 선언
	public void receiveFile(DataInputStream dis, String fileName) {
		try {
			File directory = new File("C:\\fileDown"); // 아래 경로로 지정
			if (!directory.exists()) { // 경로가 없으면
				directory.mkdirs(); // 아래 경로대로 폴더를 만들어라
			}

			File file = new File(directory, fileName); // 파일 선언. 경로와 파일 이름을 던지기
			FileOutputStream fos = new FileOutputStream(file);  // 경로에 파일을 받기

			byte[] buffer = new byte[4096]; // 버퍼 선언
			int bytesRead; 
			while ((bytesRead = dis.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
			}

			fos.close(); // 파일 받기 종료
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

} // end class
