package chat1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;




public class Client {
	
	private DatagramSocket datagramSocket;
	private InetAddress inetAddress; // ip 패킷 선언
	private byte[] buffer;
	
	public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
	}
	
	public void sendThenReceive() {
		
		// 동시에 실행하기 위해 블로킹 메세지를 선언한다?
		
		// 입력받기 위한 스캐너
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
				String messageToSend = scanner.nextLine();
				buffer = messageToSend.getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
				datagramSocket.send(datagramPacket); // 패킷 봬기
				datagramSocket.receive(datagramPacket);
				String messageFromServer = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
				System.out.println("서버 측에서 당신이 한 말: " +  messageFromServer);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void main(String[] args) throws SocketException, UnknownHostException {
		
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress inetAddrestt = InetAddress.getByName("localhost");
		Client client = new Client(datagramSocket, inetAddrestt);
		System.out.println("서버로 데이터그램 패킷 전송");
		client.sendThenReceive(); // 에러 발생 전까지 실행
	}
	
	

}








