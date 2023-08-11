package LMS_01;

import java.util.Scanner;

public class modify {

	public static void modifyStudent(Scanner scn, int nextInt, String[] name, int[] id, String[] dept, String[] phN) {
		for(int i =0; i< id.length; i++) {
			if(id[i] == nextInt) {
				System.out.println("학번 : " + id[i]);
				System.out.println("이름 : " + name[i]);
				System.out.print("학과 입력 : ");
				dept[i] = scn.next();
				System.out.print("전화번호 입력 : ");
				phN[i] = scn.next();
				System.out.println("----------------------");
				break;
			} else if(i == (id.length)-1) {
				System.out.println("일치하는 학번이 없습니다.");
			}
		}
		
	}

}
