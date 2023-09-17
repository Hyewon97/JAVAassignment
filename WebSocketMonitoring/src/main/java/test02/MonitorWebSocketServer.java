package test02;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@ServerEndpoint("/ws")
public class MonitorWebSocketServer {

    private static final CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();
    private static AtomicBoolean monitoringEnabled = new AtomicBoolean(false);

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    public static void broadcastMessage(String message) {
        for (Session session : sessions) {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void startMonitoringThreads() {
        if (!monitoringEnabled.getAndSet(true)) {
            // 스레드를 시작하고 클라이언트에게 정보를 보내는 논리를 추가
            // CPU 모니터링 스레드 시작
            CPUMonitorThread cpuThread = new CPUMonitorThread();
            cpuThread.start();

            // 디스크 정보 모니터링 스레드 시작
            HDDInfoThread diskThread = new HDDInfoThread();
            diskThread.start();

            // RAM 정보 모니터링 스레드 시작
            RAMMonitorThread ramThread = new RAMMonitorThread();
            ramThread.start();
        }
    }
}

