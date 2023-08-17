package Kiosk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import DTO.Kiosk;
import DTO.Menu;
import DTO.MenuType;
import DTO.Table;

public class KioskServiceImpl implements KioskService{
	
	private Kiosk kiosk = new Kiosk();

	public KioskServiceImpl(Kiosk kiosk) {
		this.kiosk = kiosk;
	}

	
	public Kiosk getKiosk() {
		return kiosk;
	}


	public void setKiosk(Kiosk kiosk) {
		this.kiosk = kiosk;
	}


	/*메뉴를 보여주는 기능*/
	public void showMenu(String shopName) { //입력받은 가게 이름으로 멘트를 출력
		ArrayList<Menu> menuList = kiosk.getMenuList();
		HashMap<MenuType,Integer> menuTypeCount = kiosk.getMenuTypeCount();
		
		System.out.println("\n!! 어서오세요 " + shopName + "입니다 !!");
		System.out.println("----------------MENU---------------");
		System.out.println("----  메뉴에 해당하는 번호를 눌러주세요  ----");
		for(int i = 0; i<menuList.size(); i++) {
			Menu menu = menuList.get(i);
			String menuStr = menu.getMenuName() 
					+ "-----------"
					+ menu.getMenuPrice()+"원" ;
			if(menuTypeCount.get(menu.getMenuType())<=0) { //메뉴 타입에 따른 재고를 조회하고 품절 유무를 보여줌
				menuStr += " | 품절"; 
			}else {
				menuStr += " | 구매가능";
			}
			System.out.println(i+1 + ". "+menuStr);
		}
		System.out.println("----------주문 : 0------------------");
		System.out.print("메뉴를 입력해 주세요 : ");
	}
	
	/*테이블 리스트를 조회하고 테이블 번호를 리턴*/
	@Override
	public int getTableNum() {
		Table[] tableList = kiosk.getTableList();
		
		for(int i=0;i<tableList.length;i++) {
			System.out.println(tableList[i].isTableStat());
			if(tableList[i].isTableStat()==true) {
				tableList[i].setTableStat(false);
				return i+1;
			}
		}
		return -1;
	}

	/*주문 목록을 보여주고 총 가겨을 리턴*/
	@Override
	public int showOrderList(LinkedHashMap<Menu, Integer> orderList) {
		int totalPrice = 0;
		System.out.println("-------------현재 주문 목록------------");
		for(Menu menu : orderList.keySet()) {
			System.out.println(
					menu.getMenuName()+"			"+orderList.get(menu));
			totalPrice += menu.getMenuPrice() * orderList.get(menu);
			
		}
		System.out.println("-----------------------------------");
		System.out.println("총 금액 : "+totalPrice+"원");
		return totalPrice;
	}

	/*주문을 하면 메뉴 타입에 따른 재고를 감소*/
	@Override
	public void doOrder(int tableNum) {
		Table[] tableList = kiosk.getTableList();
		LinkedHashMap<Menu,Integer> tempOrder =kiosk.getTempOrder();
		HashMap<MenuType,Integer> menuTypeCount = kiosk.getMenuTypeCount();
		
		tableList[tableNum].setOrderList(tempOrder);
		Set<Menu> keySet = tempOrder.keySet();
		for(Menu menu : keySet) {
			menuTypeCount.put(menu.getMenuType(),
					menuTypeCount.get(menu.getMenuType()) - tempOrder.get(menu));
		}

		
	}

	/*메뉴 타입에 따른 재고를 조회하고 사용자가 입력한 수량의 유효성을 검사*/
	@Override
	public boolean chkOrderByMenuType(int qty, MenuType menuType) { //사용자가 입력한 수량과 선택한 메뉴의 타입을 받는다.
		HashMap<MenuType,Integer> menuTypeCount = kiosk.getMenuTypeCount();
		LinkedHashMap<Menu,Integer> tempOrder =kiosk.getTempOrder();
		
		if (menuTypeCount.get(menuType) < qty) { // 입력한 수량이 현재 재고보다 많을 때
			System.out.println("현재 재고가 부족합니다 ! : 남은 재고 " + menuTypeCount + "개");
			return false;
		}
		else { //입력한 수량이 재고보다 적을 때, 
			int sumPig = 0, sumChicken = 0;
			Set<Menu> keySet = tempOrder.keySet(); //주문 목록을 조회한다.
			for (Menu menu : keySet) {
				int orderQty = tempOrder.get(menu);
				if (menuType.equals(MenuType.PIG)) { //메뉴 타입에 따라 주문 개수를 정리
					sumPig += orderQty;
				} else if (menuType.equals(MenuType.CHICKEN)) {
					sumChicken += orderQty;
				}
			}
			
			/*
			 * 메뉴 타입애 따라 주문 목록을 검사한다.
			 * 1. 같은 타입의 메뉴를 5개 이상 주문했을 때
			 * 2. 같은 타입의 메뉴를 재고보다 많이 주문 했을 때
			 */
			switch (menuType) {
			case PIG: {
				if (sumPig + qty > 5 || sumPig + qty > menuTypeCount.get(menuType)) 
					System.out.println("주문 개수가 재고 개수보다 많습니다. 수량을 다시 입력해 주세요!");
				else return true;
				break;
			}
			case CHICKEN: {
				if (sumChicken + qty > 5 || sumChicken + qty >  menuTypeCount.get(menuType))
					System.out.println("주문 개수가 재고 개수보다 많습니다. 수량을 다시 입력해 주세요!");
				else return true;	
				break;
			}
			case DRINK:
				break;
			default:
				break;
			}
				
		}
		
		return false;
	}


}
