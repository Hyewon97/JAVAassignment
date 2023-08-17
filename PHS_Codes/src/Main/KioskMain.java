package Main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import DTO.Kiosk;
import DTO.Menu;
import DTO.MenuType;
import Kiosk.KioskServiceImpl;

public class KioskMain {
	

	public static void main(String args[]) {
		// 메뉴 입력
		ArrayList<Menu> menuList = new Menu().initMenu();// 메뉴 초기화

		int tableSize = 5;// 매장 내 테이블 수

		Kiosk kiosk = new Kiosk(menuList, tableSize);// 키오스크 생성
		KioskServiceImpl kioskServiceImpl = new KioskServiceImpl(kiosk);

		LinkedHashMap<Menu, Integer> tempOrder = kiosk.getTempOrder();

		int totalPrice = 0;

		Scanner sc = new Scanner(System.in);

		while (true) {
			kioskServiceImpl.showMenu("에이블컴");
			int menuNumber = sc.nextInt();
			
			switch (menuNumber) {
			case 0: { //주문
				if (totalPrice == 0) { //아무것도 주문하지 않았을 때
					System.out.println("메뉴를 눌러 주문을 해주세요 !");
				} else {
					int tableNum = kioskServiceImpl.getTableNum();
					if (tableNum == -1) { //모든 테이블이 꽉 차 있을 때
						System.out.println("---------- 빈 테이블이 없습니다 ---------");
					} else { //테이블 번호를 배정하고 주문 목록과 총 가격을 초기화
						System.out.println("배정받은 테이블 번호는 " + tableNum + "입니다.");
						kioskServiceImpl.doOrder(tableNum);
						totalPrice = 0;
						tempOrder.clear();
					}
				}
				break;
			}
			case 1, 2, 3, 4: { //음식 메뉴
				Menu selectedMenu = menuList.get(menuNumber - 1); //선택한 메뉴
				MenuType selectedMenuType = selectedMenu.getMenuType(); //선택한 메뉴의 타입
				int menuTypeCount = kiosk.getMenuTypeCount().get(selectedMenu.getMenuType()); //선택한 메뉴 타입의 재고
				int qty = 0;
				
				if (menuTypeCount <= 0) { //메뉴 타입에 따른 재고가 없을 때
					System.out.println("해당 메뉴는 품절입니다 ! 다른 메뉴를 선택해주세요 !");
				} else {
					do {
						System.out.print("수량을 입력해 주세요 : ");
						qty = sc.nextInt();
					}while(!kioskServiceImpl.chkOrderByMenuType(qty, selectedMenuType)); //입력한 수량을 검사

				}
					tempOrder.put(selectedMenu, qty + tempOrder.getOrDefault(selectedMenu, 0)); //주문 목록에 메뉴를 추가
					totalPrice = kioskServiceImpl.showOrderList(tempOrder); // 주문 목록을 보여주고 현재 총 가격을 보여줌
					break;
				}
				
			
			default: {//메뉴판에 있는 번호가 아닌 다른 번호를 입력했을 때
				System.out.println("메뉴에 보이는 번호를 입력해 주세요 !");
				break;
			}

			}
		}
	}

}
