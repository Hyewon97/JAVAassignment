package test02;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class CPUMonitorThread extends Thread {
	private volatile boolean running = true;

	public void stopMonitoring() {
		running = false;
	}

	@Override
	public void run() {
		OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
		double load;
		while (running) { // 5초 동안 실행
			load = ((com.sun.management.OperatingSystemMXBean) osBean).getSystemCpuLoad();// cpu 사용률 가져오기
			System.out.println("CPU Usage: " + load * 100.0 + "%"); 
			
			 // CPU 정보를 WebSocket 서버로 전송
	        MonitorWebSocketServer.broadcastMessage("CPU Usage: " + load * 100.0 + "%");

			try {
				Thread.sleep(1000); // 1초마다 CPU 사용률 확인하기
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}
