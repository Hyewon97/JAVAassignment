package kioskk;

import java.util.Scanner;

public class order {
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
			} else if (food[index1] + food[index2] + orderTmp <= global_Max) {// 주문 가능한 경우... 희망 주문 수량과 기존 수량의 합이 5 이하는
																				// 경우
				food[index1] += orderTmp;

				// 임시 변수에 있던 값을 현재 주문 배열로 값을 할당
				nowOrder[index1] = nowOrder[index1] + orderTmp;

			}

			break;
		}
		return;

	}

}
