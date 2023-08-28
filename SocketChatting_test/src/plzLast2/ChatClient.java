package plzLast2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient implements ActionListener, Runnable {
	String host;
	int port;
	String userName; // 유저 이름 + 메세지를 출력할거여서 이름 입력받을 변수 선언
	private JFrame frm;
	private JPanel pan;
	private JTextArea taOutput;
	private JLabel lbName;
	private JTextField tfInput;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	Thread th;

	public ChatClient(String host, int port) {
		this.host = host;
		this.port = port;

		System.out.printf("사용자 이름을 입력하세요 : ");
		Scanner sc = new Scanner(System.in);
		userName = sc.nextLine(); // 이름 입력받기
		if (userName.equals("")) { // 입력이 없으면 guest로 처리
			userName = "guest";

		}
		initComponent(); // 채팅 화면 출력하기
	}

	private void initComponent() {
		frm = new JFrame(userName + "(이)의 채팅방"); // 어떤 클라이언트인지 확인하기 위해 프레임에 이름 출력
		taOutput = new JTextArea();
		taOutput.setEditable(false);

		tfInput = new JTextField(10);
		pan = new JPanel();
		lbName = new JLabel("입력: ");

		JScrollPane scroll = new JScrollPane(taOutput);

		frm.add(BorderLayout.CENTER, scroll);
		frm.add(BorderLayout.SOUTH, pan);

		pan.setLayout(new BorderLayout());
		pan.add(lbName, BorderLayout.WEST);
		pan.add(tfInput, BorderLayout.CENTER);

		tfInput.addActionListener(this);

		frm.setSize(400, 400);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stop();
			}
		});
	}// end initComponent()////////////////////////////

	private void stop() {
		if (th != null) {
			th.interrupt();
			th = null;
			try {
				dataIn.close();
				dataOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		frm.setVisible(false);
		frm.dispose();
		System.exit(0);
	}// end stop()/////////////////////////////////////

	// 서버와 연결
	private void initStart() {
		Socket socket = null;
		try {
			// 서버와 연결
			socket = new Socket(host, port);
			InputStream is = socket.getInputStream(); // 데이터 읽기
			OutputStream os = socket.getOutputStream(); // 데이터 쓰기
			dataIn = new DataInputStream(new BufferedInputStream(is)); // byte 단위로 파일 읽기
			dataOut = new DataOutputStream(new BufferedOutputStream(os)); // byte 단위로 파일 쓰기

		} catch (IOException e) {
			try {// 예외가 발생하면 소켓 닫기
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		th = new Thread(this); // 새로운 스레드 생성
		th.start();

	} // end initStart

	public static void main(String[] args) {

		// 화면 만들기
		ChatClient client = new ChatClient("127.0.0.1", 7777); // 로컬 ip, 포트 번호 7777
		client.initStart();

	} // end main

/*	@Override
	public void actionPerformed2(ActionEvent e) { // action이 추가 됨
		String message = userName + " : " + e.getActionCommand(); // 입력한 메세지를 받아와서 유저 이름이랑 합치기

		try {
			// 서버로 메세지 보냄

			// 입력창의 메세지를 서버로 보내기
			dataOut.writeUTF(message); // 한글깨짐 방지, UTF-8로 변환해서 작성하기
			dataOut.flush(); // 버퍼 비우기

			// 엔터 하고 나면 입력창을 클리어 해주기
			// 입력창 초기화
			tfInput.setText("");
			// 포커스가 인풋에 맞춰지도록
			tfInput.requestFocus();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	} // end actionPerformed()
	*/
	//// 새로 추가
	
	@Override
    public void actionPerformed(ActionEvent e) {
        String message =  userName + " : " + tfInput.getText();
        if (message.isEmpty()) {
            return;
        }

        try {
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            int messageLength = messageBytes.length;
            
            dataOut.writeInt(messageLength); // 채팅 텍스트의 바이트 길이 전송.. 이 친구 때문에 공백이 생기는거 같은데, 주석 처리 하면 출력이 안됨.. 파싱 처리가 필요하다고
           
            // 채팅 길이 출력
            System.out.println("채팅 길이 : " + messageLength); // 잘 출력이 되는 것 같음
            
            dataOut.write(messageBytes); // 실제 텍스트 데이터 전송

            dataOut.flush(); // 버퍼 비우기

            tfInput.setText(""); // 입력창 초기화
            tfInput.requestFocus(); // 입력창에 포커스 맞추기
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
	
	///

	@Override
	public void run() {
		// 수신 대기 중
		System.out.println("메시지 수신 대기 중");
		while (!Thread.interrupted()) { // 스레드가 연결이 끊어지지 않았으면 계속 실행
			try {
				String line = dataIn.readUTF(); // 서버에서 데이터 보낼 때 까지 대기 중, utf-8로 인코딩
				taOutput.append(line + "\r\n"); // 문자열 처리. 줄바꿈

			} catch (IOException e) {
				break; // 종료
			}
		}

	} // end run /////////////////////////////////////////////
} // end class
