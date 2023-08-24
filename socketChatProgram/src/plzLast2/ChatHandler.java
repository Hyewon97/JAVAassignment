package plzLast2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
				// 스레드로 돌아갈 수 있도록 함
				th = new Thread(this);
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

		// 스레드가 연결이 끊어지지 않았으면 계속 실행
		while (!Thread.interrupted()) {
			try {
				String message = dataIn.readUTF(); // 보내주기를 대기 하는 중
				broadcast(message); // 메세지 받은걸 넘겨줌
			} catch (IOException e) { // 연결이 끊어진 경우
				System.out.println(socket.getInetAddress().getHostAddress() + "나갔습니다.");
				userVect.remove(this); // 유저 벡터에서 현재 소켓을 지움
				break;
			}
		}

	} // end run() ///////////////////////////////

	/*
	// 기존 메소드 2로 수정
	public void broadcast2(String message) {
		Enumeration<ChatHandler> enu = userVect.elements();
		while (enu.hasMoreElements()) {
			ChatHandler handler = enu.nextElement();
			try {
				handler.dataOut.writeUTF(message); // 클라이언트 쪽으로 메세지를 보냄
				handler.dataOut.flush();
			} catch (IOException e) {
				handler.stop();
			}
		}

	} // end message ///////////////////////
	*/
	
	//// 새로 추가
	public void broadcast(String message) {
        Enumeration<ChatHandler> enu = userVect.elements();
        while (enu.hasMoreElements()) {
            ChatHandler handler = enu.nextElement();
            try {
                byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8); // 인코딩 utf-8로 바이트를 받아서 byte[]에 저장
                int messageLength = messageBytes.length;

                handler.dataOut.writeInt(messageLength); // 채팅 텍스트의 바이트 길이 전송
                handler.dataOut.write(messageBytes); // 실제 텍스트 데이터 전송.. 근데 출력하면 출력할때 공백이 엄청 커지는데? 이게 맞나

                handler.dataOut.flush();
            } catch (IOException e) {
                handler.stop();
            }
        }
    }
	
	///

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

} // end class
