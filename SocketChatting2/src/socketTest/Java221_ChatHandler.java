package socketTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class Java221_ChatHandler implements Runnable {
	Socket socket;
	Thread th;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	// static = 공유가 되어서 사용됨
	private static Vector<Java221_ChatHandler> userVect = new Vector<Java221_ChatHandler>();

	public Java221_ChatHandler() {

	}

	// 현재 저장되어 있는 클라이언트 정보를 socket으로 보냄
	public Java221_ChatHandler(Socket socket) {
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
				String message = dataIn.readUTF(); // readUTF : 보내주기를 대기 하는 중
				broadcast(message); // 메세지 받은걸 넘겨줌
			} catch (IOException e) { // 연결이 끊어진 경우
				System.out.println(socket.getInetAddress().getHostAddress() + "나갔습니다.");
				userVect.remove(this); // 유저 벡터에서 현재 소켓을 지움
				break;
			}
		}

	} // end run() ///////////////////////////////

	public void broadcast(String message) {
		Enumeration<Java221_ChatHandler> enu = userVect.elements();
		while (enu.hasMoreElements()) {
			Java221_ChatHandler handler = enu.nextElement();
			try {
				handler.dataOut.writeUTF(message); // 클라이언트 쪽으로 메세지를 보냄
				handler.dataOut.flush();
			} catch (IOException e) {
				handler.stop();
			}
		}

	} // end message ///////////////////////

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
