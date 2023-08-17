package LMS_01;

import java.util.Scanner;

public class add {

	public static void addStudent(Scanner scn, String[] name, int[] id, String[] dept, String[] phN) {
		int count = 0;
		boolean over = true;
		while (count < id.length) {
			System.out.println("학번을 입력해주세요.");
			id[count] = scn.nextInt(); // 인덱스 카운트에 학번이 들어감

			if (count == 0) {
				System.out.println("이름을 입력해주세요.");
				name[count] = scn.next();
				System.out.println("학과를 입력해주세요.");
				dept[count] = scn.next();
				System.out.println("전화번호를 입력해주세요.");
				phN[count] = scn.next();
				System.out.println("---------------------------");
				count++;
			} else {
				for (int j = (count - 1); j >= 0; j--) {
					if (id[count] == id[j]) {
						System.out.println("[동일한 학번입니다. 다시 입력하세요.]");
						over = false;
						break;
					}
					over = true;

				}
				if(over) {
					System.out.println("이름을 입력해주세요.");
					name[count] = scn.next();
					System.out.println("학과를 입력해주세요.");
					dept[count] = scn.next();
					System.out.println("전화번호를 입력해주세요.");
					phN[count] = scn.next();
					System.out.println("---------------------------");
					count++;					
				}
			}

		}

	}
}
