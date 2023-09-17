package test01;

import java.io.File;

public class HDDInfoThread extends Thread {

    private volatile boolean running = true;
    static double gb = 1024.0 * 1024.0 * 1024;

    public void stopMonitoring() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            // 사용 가능한 디스크 루트 디렉토리 목록 가져오기
            File[] roots = File.listRoots();

            System.out.println("==========================================");
            // 디스크 단위를 GB로 변환하는 작업 수행
            for (File root : roots) {
            	
                System.out.println("디스크 경로: " + root.getAbsolutePath()); // 디스크 경로 가지고 오기
                System.out.println("전체 공간: " + (int)(root.getTotalSpace()/gb) + " GB"); // 전체 디스크 용량
                System.out.println("사용 가능한 공간: " + (int)(root.getUsableSpace()/gb) + " GB");
                System.out.println("사용 중인 공간: " + (int)((root.getTotalSpace() - root.getUsableSpace())/gb) + " GB");
                System.out.println();
               
            }
            System.out.println("==========================================");

            try {
                Thread.sleep(1000); // 5초마다 디스크 정보 확인
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   
}
