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

	public static void main(String[] args) {

		// 사용자의 값을 입력 받기 위한 스캐너 선언
		Scanner inputData = new Scanner(System.in);

		// 변수 선언
		int max = 5; // 주문 가능한 최대 수량
		int don = 0; // 돈까스
		int kzdon = 3; // 치즈 돈까스
		int chdon = 0; // 치킨 까스
		int soda = 0; // 콜라
		
		// 임시 변수 선언
		int dontmp = 0; 
		int kzdontmp = 0;
		int chdontmp = 0;
		int sodatmp = 0; 
		
		// 현재 주문중인 수량 담을 변수 선언
		int donOrder = 0;
		int kzdonOrder = 0;
		int chdonOrder = 0;
		int sodaOrder = 0; 

		// 음식 가격 변수 선언, 초기화
		int donCost = 8000;
		int kzdonCost = 10000;
		int chdonCost = 7000;
		int sodaCost = 2000;

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
			dontmp = 0; // 입력 받을 값을 임시로 저장할 int 변수

			// 해당 선택지에 없으면 다시 선택 하도록 처리
			if (num > 5 || num < 0) {
				System.out.printf("\n[잘못된 번호 입니다. 다시 입력 하십시오.]\n");
			} else { // 선택 번호에 있으면 스위치 조건문으로 들어감
				switch (num) {
				case 1: // 돈까스를 선택한 경우

					dontmp = 0; // 임시 변수 초기화

					System.out.println("====== 돈까스 ======");
					// 남은 수량 보여주기
					System.out.printf("\n돈까스 현재 %d개 남았습니다.\n", max - (don + kzdon)); // 테스트로는 2개가 남은게 맞음

					// 품절이면 초기화면으로 돌아감
					if (max - (don + kzdon) == 0) {
						System.out.printf("\n품절입니다. 초기화면으로 돌아갑니다.\n");
						break;
					}

					System.out.printf("\n변경 전 돈까스 갯수 %d\n", don); // 확인용
					System.out.printf("\n갯수를 입력해주세요.\n가능한 최대 갯수 %d개\n", max - (don + kzdon));
//					
					// 수량을 임시 변수로 입력 받음
					dontmp = inputData.nextInt();

					if (dontmp + kzdon + don > max) { // 주문하려는 수량 + (기존의 돈까스의 수량 + 치돈의 수량)의 합의 예상값이 5보다 크면
						// 다시 선택하라는 문구와 초기 화면으로 돌아감
						System.out.printf("\n최대 주문 갯수는 %d개 입니다.\n" + "다시 선택해주세요\n" + "초기화면으로 돌아갑니다.\n", (max - (kzdon+don)));
						break;
					} else if (dontmp + (kzdon + don) <= max) { // 입력받은 수량 + (기존의 돈까스 + 치돈의 수량)이 5 이하면 
						// 갯수 입력 받아서 저장하고 확인
						don += dontmp; // 기존의 돈까스에 수량을 늘리고
						
						// 현재 주문중인 돈까스의 수
						donOrder += dontmp; // 현재 주문중인 값을 저장한다.
						
						System.out.println("=============================================="+donOrder); // 현재 주문중인 값 확인
						
						//totalCost += totalCost + (don * donCost); // 총 금액 계산
						System.out.println("변경된 돈까스 갯수 " + don); 

						// 가격 확인... 나중에 지우기
//						System.out.printf("돈까스 금액 확인 : %d원입니다.\n", totalCost);

					}
					System.out.println("=============================최종 돈까스 갯수 " + don);

					break;

				case 2: // 치즈 돈까스를 선택한 경우
					System.out.println("====== 치즈 돈까스 ======");

					kzdontmp = 0; // 임시 변수 초기화

				
					// 남은 수량 보여주기
					System.out.printf("\n치즈 돈까스 현재 %d개 남았습니다.\n", max - (don + kzdon)); 

					// 품절이면 초기화면으로 돌아감
					if (max - (don + kzdon) == 0) { // 돈까스와 치즈 돈까스의 합이 0이면 품절 표시
						System.out.printf("\n품절입니다. 초기화면으로 돌아갑니다.\n");
						break;
					}

					System.out.printf("\n변경 전 치즈 돈까스 갯수 %d\n", kzdon); // 확인용. 기존 치즈 돈까스 갯수 확인
					System.out.printf("\n갯수를 입력해주세요.\n가능한 최대 갯수 %d개\n", max - (don + kzdon)); // 주문 가능한 수량 보여주기
//					
					// 수량을 임시 변수로 입력 받음
					kzdontmp = inputData.nextInt();

					// 여기 조건문이 안들어가는 느낌.. 그러고 보니까 치즈 돈까스인데 안들어가네
					if (kzdontmp + (don+kzdon) > max) { // 현재 주문중인 치즈 돈까스 + (돈까스의 치즈 돈까의 합)이 최대값 보다 큰 경우
						// 다시 선택하라는 문구와 초기 화면으로 돌아감
						System.out.printf("\n최대 주문 갯수는 %d개 입니다.\n" + "다시 선택해주세요\n" + "초기화면으로 돌아갑니다.\n", max - (don + kzdon)); // 주문 가능한 최대 수량 보여주기
						break;
					} else if (kzdontmp + (don+kzdon) <= max) { // 현재 주문중인 치즈 돈까스 + (기존의 돈까스 + 치즈 돈까의)합이 5 이하면 값 저장
						// 갯수 입력 받아서 저장하고 확인
						kzdon += kzdontmp; // 기존의 치즈 돈까스의 수량을 늘리고
						kzdonOrder += kzdontmp; // 현재 주문중인 치즈 돈까스의 수량을 저장함
						
						System.out.println("=============================================="+kzdonOrder); // 현재 주문중인 값 확인
//						totalCost += totalCost + (kzdontmp * kzdonCost); // 총 금액 계산
						System.out.println("현재 주문중인 치즈 돈까스 갯수 " + kzdontmp);

						// 가격 확인... 나중에 지우기
//						System.out.printf("치즈 돈까스 금액 확인 : %d원입니다.\n", totalCost);

					}
					System.out.println("=============================================최종 치크 돈까스 갯수 " + kzdon);

					break;

				case 3: // 치킨까스를 선택한 경우
					chdontmp = 0; // 임시 변수 초기화

					System.out.println("====== 치킨까스 ======");
					// 남은 수량 보여주기
					System.out.printf("\n치킨 까스 현재 %d개 남았습니다.\n", max - chdon); 

					// 품절이면 초기화면으로 돌아감
					if (max - chdon == 0) {
						System.out.printf("\n품절입니다. 초기화면으로 돌아갑니다.\n");
						break;
					}

					System.out.printf("\n변경 전 치킨까스 갯수 %d\n", chdon); // 확인용
					System.out.printf("\n갯수를 입력해주세요.\n가능한 최대 갯수 %d개\n", max -chdon);
//					
					// 수량을 임시 변수로 입력 받음
					chdontmp = inputData.nextInt();

					if (chdon + chdontmp > max) { // 현재 주문중인 치킨까스의 수량 + 기존의 치킨까스의 수량이 5보다 크면
						// 다시 선택하라는 문구와 초기 화면으로 돌아감
						System.out.printf("\n최대 주문 갯수는 %d개 입니다.\n" + "다시 선택해주세요\n" + "초기화면으로 돌아갑니다.\n", max - chdon);
						break;
					} else if (chdon + chdontmp <= max) { // 현재 주문중인 치킨까스의 수량과 기존의 수량이 최대수량보다 작으면 값 저장하기
						// 갯수 입력 받아서 저장하고 확인
						chdon += chdontmp;
						
						// 현재 주문중인 돈까스의 수
						chdonOrder += chdontmp;
						
						System.out.println("=============================================="+chdonOrder);
						
						//totalCost += totalCost + (don * donCost); // 총 금액 계산
						System.out.println("변경된 치킨까스 갯수 " + chdon);

						// 가격 확인... 나중에 지우기
//						System.out.printf("돈까스 금액 확인 : %d원입니다.\n", totalCost);

					}
					System.out.println("=============================최종 치킨까스 갯수 " + chdon);
					
					
					break;

				case 4: // 콜라를 선택한 경우
					sodatmp = 0; // 임시 변수 초기화

					System.out.println("====== 콜라 ======");
					// 남은 수량 보여주기
					System.out.printf("\n콜라 현재 %d개 남았습니다.\n", max - soda); 

					// 품절이면 초기화면으로 돌아감
					if (max - soda == 0) {
						System.out.printf("\n품절입니다. 초기화면으로 돌아갑니다.\n");
						break;
					}

					System.out.printf("\n변경 전 콜라 갯수 %d\n", soda); // 확인용
					System.out.printf("\n갯수를 입력해주세요.\n가능한 최대 갯수 %d개\n", max -soda);
//					
					// 수량을 임시 변수로 입력 받음
					sodatmp = inputData.nextInt();

					if (soda + sodatmp > max) { // 현재 주문중인 콜라의 수량 + 기존의 콜라의 수량이 5보다 크면
						// 다시 선택하라는 문구와 초기 화면으로 돌아감
						System.out.printf("\n최대 주문 갯수는 %d개 입니다.\n" + "다시 선택해주세요\n" + "초기화면으로 돌아갑니다.\n", max - soda);
						break;
					} else if (soda + sodatmp <= max) { // 현재 주문중인 콜라의 수량과 기존의 수량이 최대수량보다 작으면 값 저장하기
						// 갯수 입력 받아서 저장하고 확인
						soda += sodatmp;
						
						// 현재 주문중인 콜라의 수
						sodaOrder += sodatmp;
						
						System.out.println("==============================================" + sodaOrder);
						
						//totalCost += totalCost + (don * donCost); // 총 금액 계산
						System.out.println("변경된 콜라 갯수 " + soda);

						// 가격 확인... 나중에 지우기
//						System.out.printf("돈까스 금액 확인 : %d원입니다.\n", totalCost);

					}
					System.out.println("=============================최종 콜라 갯수 " + soda);
					
					break;

				case 5:
					// 값 확인
					System.out.printf("돈까스 %d, 치돈 %d, 치킨돈%d, 콜라%d", donOrder, kzdonOrder, chdonOrder, sodaOrder);
					
					totalCost = (donOrder * donCost) + (kzdonOrder * kzdonCost) + (chdonOrder * chdonCost) + (sodaOrder * sodaCost);
					System.out.printf("\n결제를 진행합니다. 총 금액은 %d입니다.\n", totalCost);

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
