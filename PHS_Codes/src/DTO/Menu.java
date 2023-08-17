package DTO;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
	private String menuName; //이름
	private int menuPrice; // 가격
	private MenuType menuType; //메뉴 타입

	
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	public Menu(String menuName, int menuPrice, MenuType menuType) {
		super();
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuType = menuType;
	}
	
	public ArrayList<Menu> initMenu() {
		ArrayList<Menu> menuList = new ArrayList<>();
		menuList.add(new Menu("돈까스", 6000, MenuType.PIG));
		menuList.add(new Menu("치즈돈까스", 7000, MenuType.PIG));
		menuList.add(new Menu("치킨까스", 7000, MenuType.CHICKEN));
		menuList.add(new Menu("콜라", 2000, MenuType.DRINK));
		return menuList;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public MenuType getMenuType() {
		return menuType;
	}
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	
	
}
