package kioskk;

import java.util.Scanner;

public class main {
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

		// 번호 배정을 위한 변수
		int orderNum = 0; // 0으로 번호 초기화

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
					order.foodCheck(foodname, oldOrder, nowOrder, 0, 1);
					break;

				case 2: // 치즈 돈까스를 선택한 경우
					order.foodCheck(foodname, oldOrder, nowOrder, 1, 0);
					break;

				case 3: // 치킨까스를 선택한 경우
					order.foodCheck(foodname, oldOrder, nowOrder, 2, 4);
					break;

				case 4: // 콜라를 선택한 경우
					order.foodCheck(foodname, oldOrder, nowOrder, 3, 4);
					break;

				case 5:
					System.out.println("============ 주문확인 ============");

					// 가격 0으로 초기화
					totalCost = 0;

					// 사용자의 주문 여부를 입력받기 위한 스캐너 선언
					Scanner StrInput = new Scanner(System.in);

					// 주문한 음식 확인하는 코드
					for (int j = 0; j < 4; j++) {
						System.out.printf("%s %d개, ", foodname[j], nowOrder[j]);
						totalCost = totalCost + (nowOrder[j] * foodCost[j]);
					}

					System.out.printf("선택 되었습니다.\n");
					System.out.printf("총 금액은 %d입니다.\n", totalCost);

					// 주문 하시겠습니까? y -> 번호 배정, n -> 주문초기화? (y-> nowOrder 0으로 초기화, n->선택 화면으로 출력)
					System.out.println("주문 하시겠습니까? y/n");

					char lastCheck = StrInput.next().charAt(0);

					// 입력 받은 값이 y 또는 n이 아니면
					if (!(lastCheck == 'y' || lastCheck == 'n'))
						System.out.println("잘못된 문자입니다. 다시 입력해주세요\n" + "초기 화면으로 돌아갑니다.");

					if (lastCheck == 'y') {
						orderNum++;
						System.out.printf("주문 되었습니다. 주문번호는 %d 번입니다.", orderNum);

						// 현재 주문중인 갯수 초기화
						for (int j = 0; j < 4; j++) {
							nowOrder[j] = 0;
						}
					} else if (lastCheck == 'n') {
						// 주문 초기화 여부 묻기
						System.out.println("주문을 취소하시겠습니까? y/n\n" + "y 선택시 모든 주문은 초기화됩니다.");

						// 입력 값 받기
						lastCheck = StrInput.next().charAt(0);

						// 현재 주문중인 모든 주문을 0으로 초기화
						if (lastCheck == 'y') {
							for (int j = 0; j < 4; j++) {
								oldOrder[j] = oldOrder[j] - nowOrder[j];
							}
							// 현재 주문중인 수량을 0으로 초기화
							for (int j = 0; j < 4; j++) {
								nowOrder[j] = 0;
							}

						} else if (lastCheck == 'n') {
							System.out.println("초기 화면으로 돌아갑니다.");

						}

					}
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
