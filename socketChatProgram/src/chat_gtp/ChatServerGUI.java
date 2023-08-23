package chat_gtp;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerGUI {
    private static final int DEFAULT_PORT = 12345;

    private ServerSocket serverSocket;
    private Set<PrintWriter> clientWriters = new HashSet<>();

    private JFrame frame;
    private JTextArea logArea;

    public ChatServerGUI() {
        initUI();
        startServer(DEFAULT_PORT);
    }

    private void initUI() {
        frame = new JFrame("Chat Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        logArea = new JTextArea();
        logArea.setEditable(false);
        frame.add(new JScrollPane(logArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            log("Server started on port " + port);

            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                writer = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientWriters) {
                    clientWriters.add(writer);
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = reader.readLine()) != null) {
                    log("Received: " + message);
                    broadcast(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    synchronized (clientWriters) {
                        clientWriters.remove(writer);
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter clientWriter : clientWriters) {
                    clientWriter.println(message);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatServerGUI::new);
    }
}
