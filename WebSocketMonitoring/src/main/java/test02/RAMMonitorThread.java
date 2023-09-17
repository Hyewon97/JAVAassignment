package test02;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class RAMMonitorThread extends Thread {
	private volatile boolean running = true;

	static double mb = 1024.0 * 1024.0; // byte를 Megabyte로 변환
	
	public void stopMonitoring() {
		running = false;
	}

	@Override
	public void run() {
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
		while (running) {

			MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage(); // heap 메모리 확인
			MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage(); // nonHeap 메모리 확인

			System.out.println("======================================");
			System.out.println("Heap Memory:");

			// heapMemoryUsage가 byte로 값을 반환하기 때문에 MB로 계산
			// Non-Heap Memory: max가 음수로 출력되서 양수로 변환하고 음수로 출력되는 기존코드는 주석 처리함
			System.out.println("  Used: " + (heapMemoryUsage.getUsed() / mb) + " MB");
			System.out.println("  Max: " + (heapMemoryUsage.getMax() / mb) + " MB"); 

			System.out.println("Non-Heap Memory:");
			System.out.println("  Used: " + (nonHeapMemoryUsage.getUsed() / mb) + " MB");
//			System.out.println("  Max: " + (nonHeapMemoryUsage.getMax() / mb) + " MB"); // 값이 음수로 나옴
			System.out.println("  Max: " + (Math.round(nonHeapMemoryUsage.getMax() / mb)) + " MB"); // 양수로 변환
			
			
			// RAM 정보를 WebSocket 서버로 전송
	        String ramInfo = "Heap Memory:\n" +
	                "  Used: " + (heapMemoryUsage.getUsed() / mb) + " MB\n" +
	                "  Max: " + (heapMemoryUsage.getMax() / mb) + " MB\n" +
	                "Non-Heap Memory:\n" +
	                "  Used: " + (nonHeapMemoryUsage.getUsed() / mb) + " MB\n" +
	                "  Max: " + (Math.round(nonHeapMemoryUsage.getMax() / mb)) + " MB";
	        MonitorWebSocketServer.broadcastMessage(ramInfo);
			
			System.out.println("======================================");
			try {
				Thread.sleep(1000); // 1초마다 RAM 정보 확인
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
}
