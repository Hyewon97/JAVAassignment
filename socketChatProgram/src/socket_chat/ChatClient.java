package socket_chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient implements ActionListener, Runnable {
	String host;
	int port;
	String userName;
	private JFrame frm;
	private JPanel pan;
	private JTextArea taOutput;
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


		JButton btnSelectFile = new JButton("파일 선택"); // 파일 선택하는 버튼 생성

		btnSelectFile.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(); // 파일 선택하는 기능
				int result = fileChooser.showOpenDialog(frm); // 채팅방 프레임 안에 띄우기
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile(); // 선택한 파일 가져오기

					int result2 = JOptionPane.showConfirmDialog(frm, // frm 안에 알람창이 나오도록 함
							selectedFile.getName() + "파일을 전송하시겠습니까?", // 파일 전송 여부 체크
							"알림", JOptionPane.YES_NO_OPTION); // 예/아니오 옵션 사용

					if (result2 == JOptionPane.YES_OPTION) { // 예를 선택했으면

						// sendFile 실행하면 연결 끊어짐. 처음 파일 전송은 작동함.
						sendFile(selectedFile.getPath()); // 선택한 파일 전송, 파일의 경로를 넘겨줌
												
						JOptionPane.showMessageDialog(frm, selectedFile.getName() + "파일이 전송되었습니다.", "알림", 
								JOptionPane.INFORMATION_MESSAGE); //파일 전송되었다고 알람창 띄우기
						
						// 아니오를 선택한 경우 연결 끊어지는 문제는 발생되지 않음
					} else if (result2 == JOptionPane.NO_OPTION) { // 아니오를 선택했으면
						JOptionPane.showMessageDialog(frm, "전송이 취소 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE); // 전송취소 알람창 띄우기
					}

				}
			}
		});

		JScrollPane scroll = new JScrollPane(taOutput); // 스크롤 선언

		frm.add(BorderLayout.CENTER, scroll);
		frm.add(BorderLayout.SOUTH, pan);

		pan.setLayout(new BorderLayout());
		pan.add(tfInput, BorderLayout.CENTER);
		pan.add(btnSelectFile, BorderLayout.WEST);

		tfInput.addActionListener(this);

		frm.setSize(400, 400);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) { // 윈도우창
				stop();
			}
		});
	}

	// 종료를 하게 되면 자원 반납을 함
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
	}

	private void initStart() {
		Socket socket = null;
		try {
			socket = new Socket(host, port); // 서버와 연결
			InputStream is = socket.getInputStream(); // 데이터 읽기
			OutputStream os = socket.getOutputStream(); // 데이터 쓰기
			dataIn = new DataInputStream(new BufferedInputStream(is)); //  byte 단위로 파일 읽기
			dataOut = new DataOutputStream(new BufferedOutputStream(os)); // byte 단위로 파일 쓰기
		} catch (IOException e) {
			try { // 예외가 발생하면 소켓 닫기
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		th = new Thread(this); // 새로운 스레드 생성
		th.start();
	}

	public static void main(String[] args) {
		ChatClient client = new ChatClient("127.0.0.1", 7777); // 로컬 ip, 포트 번호 7777
		client.initStart();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message = userName + " : " + tfInput.getText();
		if (message.isEmpty()) {
			return;
		}

		try {
			byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
			int messageLength = messageBytes.length;

			dataOut.writeInt(messageLength); // 채팅 텍스트의 바이트 길이 전송

			dataOut.write(messageBytes); // 실제 텍스트 데이터 전송

			dataOut.flush(); // 버퍼 비우기

			tfInput.setText(""); // 입력창 초기화
			tfInput.requestFocus(); // 입력창에 포커스 맞추기
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void run() {
		// 수신 대기 중
		System.out.println("메시지 수신 대기 중");
		while (!Thread.interrupted()) { // 스레드가 연결이 끊어지지 않았으면 계속 실행
			try {
				int messageLength = dataIn.readInt(); // 채팅 길이 읽기
				byte[] messageBytes = new byte[messageLength];
				dataIn.readFully(messageBytes); // 실제 텍스트 데이터 읽기
				
				String line = new String(messageBytes, StandardCharsets.UTF_8); // 서버에서 데이터 보낼 때 까지 대기 중, utf-8로 인코딩
				taOutput.append(line + "\r\n"); // 문자열 처리. 줄바꿈
			} catch (IOException e) {
				
				break;
			}
		}
	}


	public void sendFile(String filePath) {

		System.out.println("센드 파일 메소드 실행, 전송 파일 존재 : " + filePath);
		
		// 파일 인풋 스트림 해서 null로 초기화를 하고
	
		// try-catch 문 실행되면 연결이 끊어짐
		// 존재하지 않는 파일이면
		try {
			File file = new File(filePath);
			
			// 파일이 없는 경우의 예외처리
			if (!file.exists()) {
				System.out.println("파일이 존재하지 않습니다.");
				return; // 끝내기
			}

			// 파일 전송 시작을 알리는 메시지 전송
			dataOut.writeUTF("파일 전송"); // "파일 전송" 메세지를 날려서 파일 전송을 함
			dataOut.writeUTF(file.getName());

			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = fis.read(buffer)) != -1) {
				dataOut.write(buffer, 0, bytesRead);
			}
			

			// 파일 전송 완료를 알리는 메시지 전송
			dataOut.writeUTF("파일 전송 완료");
			
			fis.close(); //파일 전송 닫기

			dataOut.flush(); // 버퍼 비우기

		} catch (IOException e) {
			
			e.printStackTrace();
		} // 파이널로 파일 close를 하면 되지 않을까
		
		
		
	}
}
