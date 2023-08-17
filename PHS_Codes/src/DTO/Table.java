package DTO;

import java.util.HashMap;

public class Table {
	private HashMap<Menu,Integer> orderList; //테이블이 주문한 메뉴 리스트
	private boolean tableStat; // 테이블 상태
	
	public Table() {
		this.orderList = new HashMap<>(); //메뉴 리스트 초기화
		this.tableStat = true; //자리 이용 가능
	}
	
	
	public HashMap<Menu,Integer> getOrderList() {
		return this.orderList;
	}

	public void setOrderList(HashMap<Menu,Integer> orderList) {
		this.orderList = orderList;
	}

	public boolean isTableStat() {
		return this.tableStat;
	}

	public void setTableStat(boolean tableStat) {
		this.tableStat = tableStat;
	}
	
	
}

	
