package LMS_01;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("학생 수를 입력하세요");

		// 학생수를 받는 코드
		int sNum = scn.nextInt();

		// 학생 아이디를 저장하는 배열 (크기는 sNum)
		int[] id = new int[sNum];

		// 학생 이름을 저장할 스트링 배열
		String[] name = new String[sNum];

		// 학생 학과를 저장할 스트링 배열
		String[] dept = new String[sNum];

		// 학생의 핸드폰 번호를 저장할 스트링 배열
		String[] phN = new String[sNum];

		// 나중에 추가 -> 학생의 성별을 저장할 char 배열

		while (true) {
			// 화면 출력
			System.out.println("**** 학생 관리 프로그램 ****");
			System.out.println("1.학생 등록");
			System.out.println("2.전체 출력");
			System.out.println("3.학생 조회");
			System.out.println("4.정보 수정");
			System.out.println("5.프로그램 종료");
			// 해당하는 번호 저장하기
			System.out.print("관리 번호를 입력하세요. : ");
			int num = scn.nextInt();
			System.out.println("----------------------");

			// 스위치 문으로 해당하는 번호/ 스레드를 실행하도록 함
			if (num > 5 || num < 0) {
				System.out.println("잘못된 관리 번호입니다. 다시 입력하세요\n");
			}else {
				switch(num) {
				case 1 : // 학생 등록
					System.out.println("학생을 등록합니다.");
					add.addStudent(scn, name, id, dept, phN);
					break;
				case 2 : // 전체 출력
					System.out.println("전체 학생을 출력합니다.");
					print.printStudent(name,id, dept, phN);
					break;
				case 3 : // 학생 조회
					System.out.println("학생을 조회합니다.");
					System.out.println("학번을 입력하세요.");
					find.findStudent(scn.nextInt(),name, id, dept,phN);
					break;
				case 4 :
					System.out.println("정보를 수정합니다.");
					System.out.println("학번을 입력해주세요.");
					modify.modifyStudent(scn, scn.nextInt(), name, id, dept, phN);
					break;
				case 5 :
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					scn.close();
					break;
				}
			}

		}
	}

}
