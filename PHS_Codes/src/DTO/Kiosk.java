package DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

import Kiosk.KioskService;

public class Kiosk {
	
	private ArrayList<Menu> menuList; //메뉴 목록
	private Table[] tableList; //테이블 목록
	private HashMap<MenuType,Integer> menuTypeCount; //메뉴 타입에 따른 재고
	private LinkedHashMap<Menu,Integer> tempOrder; //주문 목록

	
	public LinkedHashMap<Menu, Integer> getTempOrder() {
		return tempOrder;
	}

	public void setTempOrder(LinkedHashMap<Menu, Integer> tempOrder) {
		this.tempOrder = tempOrder;
	}

	public Kiosk(ArrayList<Menu> menuList,int tableSize) { // 키오스크 초기화
		this.menuList = menuList; // 메뉴 리스트 초기화
		this.tableList = new Table[tableSize]; //테이블 리스트 생성
		tempOrder = new LinkedHashMap<>(); //주문 목록 생성
		initTable(); //테이블 초기화
		initMenuCount(); // 메뉴 타입 재고 초기화
		
	}

	public Kiosk() {
		// TODO Auto-generated constructor stub
	}

	public void initTable() {
		for(int i=0;i<this.tableList.length;i++) {
			tableList[i] = new Table();
		}
		
	}
	public void initMenuCount() {
		this.menuTypeCount = new HashMap<>();
		this.menuTypeCount.put(MenuType.PIG, 5);
		this.menuTypeCount.put(MenuType.CHICKEN, 5);
		this.menuTypeCount.put(MenuType.DRINK, 999);
	}



	public ArrayList<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<Menu> menuList) {
		this.menuList = menuList;
	}

	public Table[] getTableList() {
		return tableList;
	}

	public void setTableList(Table[] tableList) {
		this.tableList = tableList;
	}

	public HashMap<MenuType, Integer> getMenuTypeCount() {
		return menuTypeCount;
	}

	public void setMenuTypeCount(HashMap<MenuType, Integer> menuTypeCount) {
		this.menuTypeCount = menuTypeCount;
	}


}	
