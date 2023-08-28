package chat3;

/* 다른 사용자의 채팅도 콘솔에 찍히도록 구현  */
import java.net.*;
import java.util.*;

public class Client {

	private DatagramSocket datagramSocket;
	private InetAddress inetAddress; // ip 패킷 선언
	private byte[] buffer;

	public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
	}

	public void sendThenReceive() {

		// 사용자 이름 저장할 스트링 변수 선언
		String userName;

		// 사용자 이름 받기
		System.out.println("사용자 이름을 입력하세요: ");

		// 입력받기 위한 스캐너
		Scanner scanner = new Scanner(System.in);

		userName = scanner.nextLine();
		if (userName.equals("")) {
			userName = "guest"; // 아무 입력이 없으면 게스트라 저장하기
		}

//		System.out.println(userName); // 입력 확인
		System.out.println(userName + "님 환영합니다.\n대화를 시작해주세요.");

		while (true) {
			try {
				String messageToSend = userName + " : " + scanner.nextLine(); // 이름 붙여서 문자열 저장
				buffer = messageToSend.getBytes(); //
				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
				
				
				// 서버에서 받은 패킷 (다른 사용자들 패킷 가져오기)
				DatagramPacket incomingPacket = new DatagramPacket(new byte[60000], 60000);
				datagramSocket.send(datagramPacket); // 패킷 보내기

				
				// 서버에서 받은 메시지 출력
				datagramSocket.receive(datagramPacket);
				String messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
				System.out.println(messageFromServer);
				//

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String[] args) throws SocketException, UnknownHostException {

		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress inetAddress = InetAddress.getByName("localhost");
		Client client = new Client(datagramSocket, inetAddress);
		System.out.println("클라이언트 접속 완료");
		client.sendThenReceive(); // 에러 발생 전까지 실행
	}

}