package socketUDP2;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//STEP 3
//로그온 메시지와 대화말 메시지를 전송함
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class MulicastChatS extends Frame {
TextArea display;
Label info;
Socket sock;
BufferedWriter output;
BufferedReader input;

TextField text;
String clientdata;
 String serverdata = "";
 //MulicastChinS mssocket;
 MulticastSocket mssocket;
 DatagramPacket outgoing, incoming;
 String group_s = "239.10.1.1";
 InetAddress group;
 int port = 5265;
 byte[] data = new byte[500];
 DatagramSocket theSocket;
 
private static final String SEPARATOR = "|";
private static final int REQ_LOGON = 1001;
private static final int REQ_SENDWORDS = 1021;
private static final int LOGOUT = 1004;
	
public MulicastChatS() {
   super("서버");
   info = new Label();
   add(info, BorderLayout.CENTER);
   display = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
   display.setEditable(false);
   add(display, BorderLayout.SOUTH);
   addWindowListener(new WinListener());
   setSize(300,250);
   setVisible(true);
}
	
public void runServer() {
	     try {
	    	 group = InetAddress.getByName("239.10.1.1");
	    	 theSocket = new DatagramSocket(5000);
	    	 outgoing = new DatagramPacket(new byte[1],1); 
			 incoming = new DatagramPacket(new byte[60000],60000);
	    	 info.setText("multicast chat server address: " + group_s);
	    	 
	    	 mssocket = new MulticastSocket();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      }
	     
	      try {
	         while(true) {
	        	incoming.setLength(incoming.getData().length);
	     		theSocket.receive(incoming);
	     		clientdata = new String(incoming.getData(),0,incoming.getLength());
	     		System.out.println(clientdata);
	            StringTokenizer st = new StringTokenizer(clientdata, SEPARATOR);
	            int command = Integer.parseInt(st.nextToken());
	            
	            
	            switch(command) {     //-->로그온 한 애들만 메세지를 보냄
	               case REQ_LOGON : { // “1001|아이디”를 수신한 경우  //** 멀티캐스트 그룹 가입 요청 받음!**//       	   
	                  String ID = st.nextToken();
	                  String text = st.nextToken();
	                  display.append("client " + ID + text);
	                  
	                  //그룹 주소와 포트번호 보내줌.
	                  String send_A = group + SEPARATOR + Integer.toString(port);
	                  data = send_A.toString().getBytes();
	     			  outgoing.setData(data);
	     			  outgoing.setLength(data.length);
	     			  outgoing.setAddress(incoming.getAddress());
	     			  outgoing.setPort(incoming.getPort());
	     			  theSocket.send(outgoing);
	                  break;
	               }
	               case REQ_SENDWORDS : { // “1021|아이디|대화말”를 수신
	                  String ID = st.nextToken();
	                  String message = st.nextToken();
	                  
	                  //멀티 캐스트를 이용해서 클라이언트에게 뿌려줌	                  
	                  String sendm = ID + ":" + message;
	                  data = new String(sendm).getBytes(); 
		              outgoing.setData(data);
					  outgoing.setLength(data.length);
					  outgoing.setAddress(group);
	     			  outgoing.setPort(port);
			          mssocket.send(outgoing);
	                  display.append(sendm);
	                  break;
	               }
	               case LOGOUT : {  // 1004|아이디 로그아웃
	            	   String ID = st.nextToken();
	                   String logms = st.nextToken();
	                   display.append("client " + ID);
	                   display.append(logms+"\r\n");
	                   break; 	   
	               }
	            }
	         }
	      } catch(IOException e) {
	         e.printStackTrace();
	      }

	      try{
	         sock.close();
	      }catch(IOException ea){
	         ea.printStackTrace();
	      }

}
	
public static void main(String args[]) {
	   MulicastChatS s = new MulicastChatS();
   s.runServer();
}
		
class WinListener extends WindowAdapter {
   public void windowClosing(WindowEvent e) {
 	  
      System.exit(0);
   }
}
}