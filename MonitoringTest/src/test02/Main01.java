package test02;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class Main01 {

	static double kb = 1024.0;
	static double mb = 1024.0 * 1024.0;
	static double gb = 1024.0 * 1024.0 * 1024;


	public void showOSBean( ){

		//OS 설정 확인하는 코드
		OperatingSystemMXBean osbean = ( OperatingSystemMXBean ) ManagementFactory.getOperatingSystemMXBean( );
		System.out.println( "OS Name: " + osbean.getName( ) );
		System.out.println( "OS Arch: " + osbean.getArch( ) );
		System.out.println( "Available Processors: " + osbean.getAvailableProcessors( ) );
	}

	public void showMemory(){

		//자바 힙메모리 크기 확인하는 코드 
		MemoryMXBean membean = (MemoryMXBean) ManagementFactory.getMemoryMXBean( );
		MemoryUsage heap = membean.getHeapMemoryUsage();
		System.out.println( "Heap Memory: " + heap.getUsed()/mb+"MB");
		MemoryUsage nonheap = membean.getNonHeapMemoryUsage();
		System.out.println( "NonHeap Memory: " + nonheap.getUsed()/mb+"MB");
	}

	public static void showDisk( ){

		//현재 가지고 있는 디스크의 크기 확인하는 코드 
		File root = null;
		try
		{
			root = new File( "C:/" );
			System.out.println( "Total  Space: " + (int)(root.getTotalSpace()/gb) + "GB" );
			System.out.println( "Usable Space: " + (int)(root.getUsableSpace()/gb) + "GB" );

		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
	}

	public void showCPU() {

		//현재 cpu 사용량 확인하는 코드 이것만은 실시간으로 출력되게 만들었습니다 

		final OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
		double load;
		while(true){
			load = ((com.sun.management.OperatingSystemMXBean) osBean).getSystemCpuLoad();
			System.out.println("CPU Usage : "+load*100.0+"%");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void systemMemory () {
		//시스템 메모리 
		Runtime runtime = Runtime.getRuntime();
		int total = (int)(runtime.totalMemory()/mb);
		int free = (int)(runtime.freeMemory()/mb);
		int used = total - free;
		
		System.out.println("전체 메모리" + total+"MB");
		System.out.println("사용중인 메모리" + free +"MB");
		System.out.println("사용가능한 메모리"+used +"MB");
		
	}

	public static void main(String[] args) {

		Main01 test = new Main01();
		test.systemMemory();


	}
}