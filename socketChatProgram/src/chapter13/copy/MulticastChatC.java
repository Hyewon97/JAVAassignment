package chapter13.copy;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.StringTokenizer;

// 클라이언트

public class MulticastChatC extends Frame implements ActionListener, KeyListener{

	   TextArea display;
	   TextField wtext, ltext;
	   Label mlbl, wlbl, loglbl;
	   BufferedWriter output;
	   BufferedReader input;
	   Socket client, client1;
	   StringBuffer clientdata;
	   String serverdata;
	   String ID;
	   Button log_b,logout;
	   Panel plabel,ptotal,pword;
	   Boolean check;
	   
		int port = 5265;
		InetAddress group;
		String m ="주소요청";
		DatagramPacket packet, packetA;
		DatagramSocket dsocket;
		byte[] data;
		
	  public ClientThread CThread;
	  DatagramPacket outgoing,incoming;
		
	   private static final String SEPARATOR = "|";
	   private static final int REQ_LOGON = 1001;
	   private static final int REQ_SENDWORDS = 1021;
	   private static final int REQ_LOGOUT = 1004;
		
	   public MulticastChatC() {
		      super("클라이언트");
		      mlbl = new Label("멀티캐스트 채팅 서버에 가입을 요청합니다!");
		      add(mlbl, BorderLayout.NORTH);

		      display = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		      display.setEditable(false);
		      add(display, BorderLayout.CENTER);

		       ptotal = new Panel(new BorderLayout());
		 
		      pword = new Panel(new BorderLayout());
		      wlbl = new Label("대화말");
		      wtext = new TextField(30); //전송할 데이터를 입력하는 필드
		      wtext.addKeyListener(this); //입력된 데이터를 송신하기 위한 이벤트 연결
		      pword.add(wlbl, BorderLayout.WEST);
		      pword.add(wtext, BorderLayout.EAST);
		      ptotal.add(pword, BorderLayout.CENTER);

		      plabel = new Panel(new BorderLayout());
		      loglbl = new Label("로그온");
		      ltext = new TextField(30); //전송할 데이터를 입력하는 필드
		      //ltext.addActionListener(this); //입력된 데이터를 송신하기 위한 이벤트 연결
		      log_b = new Button("확인");
		      log_b.addActionListener(this);    
		      plabel.add(log_b,BorderLayout.EAST);
		      plabel.add(loglbl, BorderLayout.WEST);
		      plabel.add(ltext, BorderLayout.CENTER);
		      
		      ptotal.add(plabel, BorderLayout.SOUTH);

		      add(ptotal, BorderLayout.SOUTH);

		      addWindowListener(new WinListener());
		      setSize(300,250);
		      setVisible(true);
	   }
	   //액션 리스너에서 처리해줘야할것들
	   public void runClient() {
	      try {
	        clientdata = new StringBuffer(2048);
	        dsocket = new DatagramSocket();
		    outgoing = new DatagramPacket(new byte[1],1,InetAddress.getLocalHost(),5000);
		    incoming = new DatagramPacket(new byte[60000],60000);
	     } catch(IOException e) {
	         e.printStackTrace();
	      }
	   }
			
	   public void actionPerformed(ActionEvent ae){
		    
		   if(ae.getSource()==log_b){	
				  if(ID == null) {
					  ID = ltext.getText();
					  try {
						clientdata.setLength(0);   //로그인 요청을 보냄
					 	clientdata.append(REQ_LOGON);
					 	clientdata.append(SEPARATOR);
					 	clientdata.append(ID);
					 	clientdata.append(SEPARATOR);
					 	clientdata.append("로그인 하였습니다.\r\n");
					 	data = new String(clientdata).getBytes(); 
					 	outgoing.setData(data);
						outgoing.setLength(data.length);
			            dsocket.send(outgoing);
						
						//주소를 받음
						dsocket.receive(incoming);
						String address = new String(incoming.getData(),0,incoming.getLength());
						StringTokenizer st = new StringTokenizer(address, SEPARATOR);
				        String address_g = st.nextToken();
				        address_g = address_g.replace("/", "");	
				        group = InetAddress.getByName(address_g);
				        port = Integer.parseInt(st.nextToken());
				        
				        //멀티캐스트 그룹에 가입함.
				        MulticastSocket mssocket = new MulticastSocket(port);
						mssocket.joinGroup(group);
						
						//쓰레드 시작
						CThread = new ClientThread(mssocket);
						CThread.start();
						
						ltext.setVisible(false);
						plabel.remove(log_b);
						logout= new Button("로그아웃");
						plabel.add(logout,BorderLayout.CENTER);
						logout.addActionListener(this);
						mlbl.setText(ID + "(으)로 로그인 하였습니다.");
						plabel.validate();

					  }catch(Exception e) {
						  e.printStackTrace();
					  }
				  }
				  else {
					  mlbl.setText("다시 로그인 하세요!!!");
				  }
			  
			  }else if(ae.getSource()==logout) {
				  mlbl = new Label("멀티캐스트 채팅 서버에 가입을 요청합니다!");
				  logout.setVisible(false);
				  try {
					  ltext.setText("");
					  clientdata.setLength(0);
					  clientdata.append(REQ_LOGOUT);
					  clientdata.append(SEPARATOR);
					  clientdata.append(ID);
					  clientdata.append(SEPARATOR);
					  clientdata.append("이(가) 로그아웃하였습니다.");
					  
		              data = new String(clientdata).getBytes(); 
		              
		              outgoing.setData(data);
					  outgoing.setLength(data.length);
			          dsocket.send(outgoing);
			           
					  plabel.remove(logout);
					  ltext.setVisible(true);
					  
					  log_b = new Button("확인");
				      log_b.addActionListener(this);    
				      plabel.add(log_b,BorderLayout.EAST);		
				      plabel.validate();
				      ID=null;
				  }catch(Exception e) {
	    		  e.printStackTrace();
				  }
			  }
	   }
		
	   public static void main(String args[]) {
		   MulticastChatC c = new MulticastChatC();
	      c.runClient();
	   }
			
	   class WinListener extends WindowAdapter {        //창은 닫히는데 로그아웃을 한 상태를 만들어줘야함.
	      public void windowClosing(WindowEvent e){
	    	  try {
	    		  ltext.setText("");
	        	  clientdata.setLength(0);
	              clientdata.append(REQ_LOGOUT);
	              clientdata.append(SEPARATOR);
	              clientdata.append(ID);
	              clientdata.append(SEPARATOR);
	              clientdata.append("이(가) 로그아웃하였습니다.");
	              data = new String(clientdata).getBytes(); 
	              outgoing.setData(data);
				  outgoing.setLength(data.length);
		          dsocket.send(outgoing);
	        	  ltext.setVisible(true);
	        	  }catch(Exception e2) {
	        		  e2.printStackTrace();
	        	  }
	         System.exit(0);
	      }
	   }

	   public void keyPressed(KeyEvent ke) {
	      if(ke.getKeyChar() == KeyEvent.VK_ENTER) {
	         String message = new String();
	         message = wtext.getText();
	         if (ID == null) {
	            mlbl.setText("다시 로그인 하세요!!!");
	            wtext.setText("");
	         } else {
	            try {
	               clientdata.setLength(0);
	               clientdata.append(REQ_SENDWORDS);
	               clientdata.append(SEPARATOR);
	               clientdata.append(ID);
	               clientdata.append(SEPARATOR);
	               clientdata.append(message);
	               clientdata.append("\r\n");
	               data = new String(clientdata).getBytes(); 
	               outgoing.setData(data);
				   outgoing.setLength(data.length);
		           dsocket.send(outgoing);
	               wtext.setText("");
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	         }
	      }
	      
	   }
	   //멀티쓰레드로(다른 클라이언트가 보내는) 들어오는 메세지들을 받는 쓰레드
	   class ClientThread extends Thread{
		   MulticastSocket mssocket;
		   public ClientThread(MulticastSocket ms) {
			   mssocket = ms;
		   }
		   public void run() {
			   try {
				   while(!Thread.interrupted()) {
					   incoming.setLength(incoming.getData().length);
					   mssocket.receive(incoming);
					   String message = new String(incoming.getData(),0,incoming.getLength());
					   display.append(message);
				   }
			   }catch(IOException e) {
				   e.printStackTrace();
			   }
		   }
	   }

	   public void keyReleased(KeyEvent ke) {
	   }

	   public void keyTyped(KeyEvent ke) {
	   }
	}