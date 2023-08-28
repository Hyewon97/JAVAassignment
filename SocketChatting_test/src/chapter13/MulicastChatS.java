package chapter13;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class MulicastChatS extends Frame {
    private static final String SEPARATOR = "|";
    private static final int REQ_LOGON = 1001;
    private static final int REQ_SENDWORDS = 1021;
    private static final int LOGOUT = 1004;

    private TextArea display;
    private Label info;
    private DatagramSocket theSocket;
    private MulticastSocket mssocket;
    private String group_s = "239.10.1.1";
    private InetAddress group;
    private int port = 5265;
    private byte[] data = new byte[500];
    private DatagramPacket incoming;
    private String clientdata;

    public MulicastChatS() {
        super("서버");
        info = new Label();
        add(info, BorderLayout.CENTER);
        display = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        display.setEditable(false);
        add(display, BorderLayout.SOUTH);
        addWindowListener(new WinListener());
        setSize(300, 250);
        setVisible(true);
    }

    public void runServer() {
        try {
            group = InetAddress.getByName("239.10.1.1");
            theSocket = new DatagramSocket(5000);
            incoming = new DatagramPacket(new byte[60000], 60000);
            info.setText("멀티캐스트 채팅 그룹 주소 : " + group_s);

            mssocket = new MulticastSocket();

            while (true) {
                receiveAndProcessData();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            theSocket.close();
        }
    }

    private void receiveAndProcessData() throws IOException {
        incoming.setLength(incoming.getData().length);
        theSocket.receive(incoming);
        clientdata = new String(incoming.getData(), 0, incoming.getLength());
        System.out.println(clientdata);

        StringTokenizer st = new StringTokenizer(clientdata, SEPARATOR);
        int command = Integer.parseInt(st.nextToken());

        switch (command) {
            case REQ_LOGON:
                handleLogonRequest(st);
                break;
            case REQ_SENDWORDS:
                handleSendWordsRequest(st);
                break;
            case LOGOUT:
                handleLogout(st);
                break;
        }
    }

    private void handleLogonRequest(StringTokenizer st) throws IOException {
        String ID = st.nextToken();
        String text = st.nextToken();
        String send_A = String.format("%s%s%d", group, SEPARATOR, port);
        data = send_A.getBytes();
        DatagramPacket outgoing = new DatagramPacket(data, data.length, incoming.getAddress(), incoming.getPort());
        theSocket.send(outgoing);
        display.append(String.format("클라이언트가 %s%s", ID, text));
    }

    private void handleSendWordsRequest(StringTokenizer st) throws IOException {
        String ID = st.nextToken();
        String message = st.nextToken();
        String sendm = String.format("%s:%s", ID, message);
        data = sendm.getBytes();
        DatagramPacket outgoing = new DatagramPacket(data, data.length, group, port);
        mssocket.send(outgoing);
        display.append(sendm);
    }

    private void handleLogout(StringTokenizer st) {
        String ID = st.nextToken();
        String logms = st.nextToken();
        display.append(String.format("클라이언트가 %s%s\r\n", ID, logms));
    }

    public static void main(String args[]) {
        new MulicastChatS().runServer();
    }

    class WinListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
