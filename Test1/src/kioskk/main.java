package kioskk;

import java.util.Scanner;

/*
객체지향 프로그램

절차
1. 메뉴 입력 
2. 총금액 알람
3. 번호 배정

조건
메뉴 : 돈까스, 치즈 돈까스, 치킨까스, 콜라
주문 가능 개수는 돼지, 닭 각각 5개. > 돈까스, 치즈돈까스는 총 5개
메뉴가 다 팔리면 메뉴는 품절표시가 되어야 함
*/

public class main {

	static int global_Max = 5; // 전역변수, 최대 수량 표시

	public static void foodCheck(String[] name, int[] food, int[] nowOrder, int index1, int index2) {

		// 사용자 값 입력 받기 위한 스캐너 선언
		Scanner inputTest = new Scanner(System.in);

		// 현재 주문중인 값 임시 저장 변수 선언
		int orderTmp = 0; // 0으로 초기화

		// 음식 이름 출력
		System.out.printf("\n====== %s ======\n", name[index1]);

		// 남은 수량 보여주기
		System.out.printf("\n%s 현재 %d개 남았습니다.\n", name[index1], global_Max - (food[index1] + food[index2]));

		// 음식이 품절인지 아닌지 확인하기
		while (true) {
			if (global_Max - (food[index1] + food[index2]) == 0) {
				System.out.println("품절입니다.\n초기화면으로 돌아갑니다.");
				break;
			}

			System.out.printf("\n갯수를 입력해주세요.\n가능한 최대 갯수 %d개\n", global_Max - (food[index1] + food[index2]));

			// 수량 입력 받아 임시 변수에 저장함
			orderTmp = inputTest.nextInt();

			if (food[index1] + food[index2] + orderTmp > global_Max) { // 주문 불가능한 경우... 희망 주문 수량과 기존 수량의 합이 5 초과인 경우
				// 다시 선택하라는 문구와 초기 화면으로 돌아감
				System.out.printf("\n최대 주문 갯수는 %d개 입니다.\n" + "다시 선택해주세요\n" + "초기화면으로 돌아갑니다.\n",
						global_Max - (food[index1] + food[index2]));
				break;
			} else if (food[index1] + food[index2] + orderTmp <= global_Max) { // 주문 가능한 경우... 희망 주문 수량과 기존 수량의 합이 5 이하는
																				// 경우
				food[index1] += orderTmp;

				// 임시 변수에 있던 값을 현재 주문 배열로 값을 할당
				nowOrder[index1] = nowOrder[index1] + orderTmp;

			}

			break;
		}
		return;

	}

	public static void main(String[] args) {

		// 사용자의 값을 입력 받기 위한 스캐너 선언
		Scanner inputData = new Scanner(System.in);

		// 음식 이름 선언
		String[] foodname = { "돈까스", "치즈 돈까스", "치킨까스", "콜라" };

		// 현재 주문중인 수랑 담을 int 배열 선언
		// 인덱스 4번은 돼지가 아닌 경우 사용하기 위한 가짜 값
		int[] nowOrder = { 0, 0, 0, 0, 0 };

		// 기존에 있는 수량을 담을 int 배열 선언
		// 인덱스 4번은 돼지가 아닌 경우 사용하기 위한 가짜 값
		int[] oldOrder = { 0, 0, 0, 0, 0 };

		// 음식 가격 담을 int 배열 선언
		int[] foodCost = { 8000, 10000, 7000, 2000 };

		int totalCost = 0; // 가격 변수. 0으로 초기화

		System.out.println("혜원식당에 오신것을 환영합니다!" + "\n");

		while (true) {
			System.out.println("\n**** 메뉴를 선택하세요 ****");
			System.out.println("1. 돈까스 - 8,000원");
			System.out.println("2.치즈 돈까스 - 10,000원");
			System.out.println("3.치킨까스 - 7,000원");
			System.out.println("4.콜라 - 2,000원");
			System.out.println("5.주문확인");
			System.out.println("0.프로그램 종료");

			System.out.print("\n번호를 선택하세요. : ");

			int num = inputData.nextInt(); // 번호 입력 받는 소스

			// 해당 선택지에 없으면 다시 선택 하도록 처리
			if (num > 5 || num < 0) {
				System.out.printf("\n[잘못된 번호 입니다. 다시 입력 하십시오.]\n");
			} else { // 선택 번호에 있으면 스위치 조건문으로 들어감
				switch (num) {
				case 1: // 돈까스를 선택한 경우
					foodCheck(foodname, oldOrder, nowOrder, 0, 1);
					break;

				case 2: // 치즈 돈까스를 선택한 경우
					foodCheck(foodname, oldOrder, nowOrder, 1, 0);
					break;

				case 3: // 치킨까스를 선택한 경우
					foodCheck(foodname, oldOrder, nowOrder, 2, 4);
					break;

				case 4: // 콜라를 선택한 경우
					foodCheck(foodname, oldOrder, nowOrder, 3, 4);
					break;

				case 5:
					System.out.println("============ 주문확인 ============");

					for (int j = 0; j < 4; j++) {
						System.out.printf("%s %d개, ", foodname[j], nowOrder[j]);
						totalCost = totalCost + (nowOrder[j] * foodCost[j]);
					}
					System.out.printf("선택 되었습니다.\n");
					System.out.printf("총 금액은 %d입니다.\n", totalCost);

					break;

				default:
					System.out.println("[프로그램을 종료합니다.]");
					System.exit(0);
					inputData.close();
					break;
				}
			}

		}

	}

}
