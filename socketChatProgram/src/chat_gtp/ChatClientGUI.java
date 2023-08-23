package chat_gtp;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClientGUI {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    private JFrame frame;
    private JTextArea chatArea;
    private JTextField messageField;

    public ChatClientGUI() {
        initUI();
        connectToServer();

        Thread receivingThread = new Thread(() -> {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    chatArea.append("Received from server: " + message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        receivingThread.start();
    }

    private void initUI() {
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        messageField = new JTextField();
        messageField.addActionListener(e -> sendMessage());
        frame.add(messageField, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to the server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String messageToSend = messageField.getText();
        writer.println(messageToSend);
        messageField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatClientGUI::new);
    }
}
