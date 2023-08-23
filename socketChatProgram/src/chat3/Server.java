package chat3;

import java.net.*;
import java.util.*;

public class Server {
	
	// 클라이언트의 채팅을 다른 클라이언트에게 보여주기 위해 클라이언트 ip 주소 목록 저장
    private List<InetAddress> clients; // 클라이언트 IP 주소 목록 저장
    
	// 데이터그램 소켓 선언
	private DatagramSocket datagramSocket;
	
	// 버퍼 선언 
	private byte[] buffer = new byte[256]; // 256크기의 버퍼 선언
	
	// const 선언
	public Server(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
		this.clients = new ArrayList<>();
	}
	
	public void reciveThenSend()
	{
		// 서버 연결 메세지 출력
		System.out.println("서버 연결 완료");
		
		// 클라이언트에서 값을 받아와서 에코백 하기
		while(true) { 
			try {
				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
				datagramSocket.receive(datagramPacket); // 클라이언트에서 데이터가 올때까지 대기
				InetAddress inetAddress= datagramPacket.getAddress(); // ip 주소 가져오기
				int port = datagramPacket.getPort(); // 포트 가지고오기
				String messageFromClinet = new String(datagramPacket.getData(),0, datagramPacket.getLength()); // 데이터패킷에 있는 데이터 가지고 오기, 0 = 모든 데이터 가지고 오기
				System.out.println(messageFromClinet); // 메세제 출력
				datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port); // 다른 데이터패킷 만들기
				
				// 패킷 보내기
				datagramSocket.send(datagramPacket);
				
				// 브로드캐스팅: 다른 클라이언트들에게도 메시지 보내기
              //  broadcast(messageFromClinet);
			}catch (Exception e) {
				e.printStackTrace();
				break; // 프로그램 끝내기
			}
		}
	}
	
	public static void main(String[] args) throws SocketException {
		DatagramSocket datagramSocekt = new DatagramSocket(1234); // 포트 번호 1234로 데이터그램 소켓 선언
		Server server = new Server(datagramSocekt);
		server.reciveThenSend(); // 에러 발생할때까지 돎
		
	}
	
	// 다른 클라이언트에게 패킷 보내기
   /* private void broadcast(String message) {
        for (InetAddress clientAddress : clients) {
            try {
                DatagramPacket broadcastPacket = new DatagramPacket(
                        message.getBytes(), message.length(), clientAddress, 1234);
                datagramSocket.send(broadcastPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
}












