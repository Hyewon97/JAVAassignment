package socketUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
	
	public void start() throws IOException{
		
		// 포트 9999번을 사용하는 소켓 생성
		DatagramSocket socket = new DatagramSocket(9999);
		DatagramPacket inPacket, outPacket;
		
		byte[] inMessage = new byte[10];
	}

}
