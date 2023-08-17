package Kiosk;

import java.util.HashMap;
import java.util.LinkedHashMap;

import DTO.Menu;
import DTO.MenuType;

public interface KioskService { //키오스크 기능 정의
	public void showMenu(String shopName); //메뉴 보여주기
	public int showOrderList(LinkedHashMap<Menu,Integer> orderList);//주문내역과 총 금액을 보여주고 총 금액을 리턴
	public int getTableNum(); // 비어있는 테이블 번호 리턴
	public void doOrder(int tableNum);//구매
	public boolean chkOrderByMenuType(int qty, MenuType menuType);//메뉴 타입(돼지/닭)으로 주문목록 체크
}
