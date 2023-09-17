package test01;

public class CpuMain01 {

	public static void main(String[] args) {
		CPUMonitorThread cpuMonitorThread = new CPUMonitorThread(); // cpu
		RAMMonitorThread ramMonitorThread = new RAMMonitorThread(); // ram(메모리)
		HDDInfoThread diskInfoThread = new HDDInfoThread(); // HDD
		
		

		cpuMonitorThread.start();
		ramMonitorThread.start();
		diskInfoThread.start();


		// 설정한 시간동안 모니터링 후 스레드 종료
		try {
			Thread.sleep(5000); // 5초 동안 모니터링
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		cpuMonitorThread.stopMonitoring();
		ramMonitorThread.stopMonitoring();
		diskInfoThread.stopMonitoring();

	}

}
