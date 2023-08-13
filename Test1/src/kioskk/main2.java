package kioskk;

import java.util.Scanner;


public class main2 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("혜원이네 가게 오신것을 환영합니다.");

		while (true) {
			// 화면 출력
			System.out.println("**** 맛있는 가게 ****");
			System.out.println("1.돈까스");
			System.out.println("2.치즈 돈까스");
			System.out.println("3.치킨까스");
			System.out.println("4.콜라");
			System.out.println("5.결제");
			System.out.println("6.프로그램 종료");
			// 해당하는 번호 저장하기
			System.out.print("음식 번호를 입력하세요. : ");
			int num = scn.nextInt();
			System.out.println("----------------------");

			// 스위치 문으로 해당하는 번호/ 스레드를 실행하도록 함
			if (num > 5 || num < 0) {
				System.out.println("잘못된 번호입니다. 다시 입력하세요\n");
			}else {
				switch(num) {
				case 1 : // 학생 등록
					System.out.println("돈까스를 선택하셨습니다.");
//					add.addStudent(scn, name, id, dept, phN);
					break;
				case 2 : // 전체 출력
					System.out.println("전체 학생을 출력합니다.");
//					print.printStudent(name,id, dept, phN);
					break;
				case 3 : // 학생 조회
					System.out.println("학생을 조회합니다.");
					System.out.println("학번을 입력하세요.");
//					find.findStudent(scn.nextInt(),name, id, dept,phN);
					break;
				case 4 :
					System.out.println("정보를 수정합니다.");
					System.out.println("학번을 입력해주세요.");
//					modify.modifyStudent(scn, scn.nextInt(), name, id, dept, phN);
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
